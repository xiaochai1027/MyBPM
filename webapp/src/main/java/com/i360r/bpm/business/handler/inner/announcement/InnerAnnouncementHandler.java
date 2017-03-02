package com.i360r.bpm.business.handler.inner.announcement;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.i360r.bpm.business.exception.GetCdsResponseException;
import com.i360r.bpm.business.exception.GetOasResponseException;
import com.i360r.bpm.business.handler.AbstractBusinessHandler;
import com.i360r.bpm.business.handler.inner.announcement.api.IInnerAnnouncementHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.business.util.ServiceClientUtility;
import com.i360r.bpm.service.rs.process.inner.announcement.api.InnerAnnouncementDetailVO;
import com.i360r.cds.api.internal.rs.employee.EmployeeResponse;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.log.Log;
import com.i360r.framework.util.DateTimeUtility;
import com.i360r.oas.api.internal.rs.innner.announcement.CreateInnerAnnouncementRequest;

public class InnerAnnouncementHandler  extends AbstractBusinessHandler implements IInnerAnnouncementHandler {

	private static final Log LOG = Log.getLog(InnerAnnouncementHandler.class);
	
	@Override
	public void createInnerAnnouncement(Map<String, Object> processVariables) throws Exception {
		
		InnerAnnouncementDetailVO detail = ProcessUtility.getProcessObject(processVariables, InnerAnnouncementDetailVO.class);
		
		LOG.info("InnerAnnouncementHandler.createInnerAnnouncement  InnerAnnouncementDetailVO:{}, processVariables:{}", detail, processVariables);
		
		CreateInnerAnnouncementRequest request = new CreateInnerAnnouncementRequest();
		Date createTime = (Date)processVariables.get(ProcessConstants.KEY_CREATE_TIME);
		request.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMMSS(createTime));
		
		try{
			String createByCode = (String)processVariables.get(ProcessConstants.KEY_CREATED_BY_CODE);
			EmployeeResponse employee = ServiceClientUtility.getEmployeeInternalService().getEmployeeByEmployeePosition(createByCode);
			request.setCreateById(employee.getEmployee().getId());
		} catch (Exception e){
			throw new GetCdsResponseException("Create innerAnnouncement failed!", e);
		}
		
		String createByName = (String)processVariables.get(ProcessConstants.KEY_CREATED_BY_NAME);
		request.setCreateByName(createByName);
		
		request.setContent(detail.getContent());
		request.setPublishDate(detail.getPublishDate());
		request.setTitle(detail.getTitle());
		
		Set<String> locationIds = new HashSet<>();
		for (String string : detail.getLocationIds()) {
			locationIds.add(string);
		}
		request.setLocationIds(locationIds);
		
		Set<Integer> positionIds = new HashSet<>();
	    for (Integer positionId : detail.getPositionIds()) {
	    	positionIds.add(positionId);
		}
		request.setPositionIds(positionIds);
		
		ServiceResponse response = null;
		try{
			response = ServiceClientUtility.getInnerAnnouncementInternalService().createInnerAnnouncement(request);
		} catch (Exception e){
			throw new GetOasResponseException("Create innerAnnouncement failed!", e);
		}
		
		checkResponseSuccess(response);
	}

}
