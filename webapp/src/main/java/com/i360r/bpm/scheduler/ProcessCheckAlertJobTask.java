package com.i360r.bpm.scheduler;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstanceQuery;
import org.apache.cxf.common.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.util.BeanUtility;
import com.i360r.bpm.business.util.DateTimeUtility;
import com.i360r.bpm.data.dao.model.RoleType;
import com.i360r.bpm.service.rs.process.deliverystaffentry.DeliveryStaffEntryConstants;
import com.i360r.framework.log.Log;
import com.i360r.framework.scheduler.AbstractStatefulJobTask;

public class ProcessCheckAlertJobTask extends AbstractStatefulJobTask {
	private static final long serialVersionUID = 6697533888949383874L;
	private static final Log LOG = Log.getLog(ProcessCheckAlertJobTask.class);
	
	@Autowired
	private IEmployeeHandler employeeHandler;
	
	@Override
	public void run() {
		LOG.info("ProcessCheckAlertJobTask Start ....");
		HistoryService historyService = BeanUtility.getHistoryService();
		IMessageNotifyHandler messageHandler = BeanUtility.getMessageNotifyHandler();
		HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
		query = query.processDefinitionKey(DeliveryStaffEntryConstants.PROCESS_DEFINITION_KEY);
		query = query.variableValueEquals(DeliveryStaffEntryConstants.KEY_JOB_TRANSFER,Boolean.FALSE);
		query.unfinished();
		List<HistoricProcessInstance>  hiPs = query.list();
		if (!CollectionUtils.isEmpty(hiPs)) {
			HashMap<String, String> stationManagerMobileMap = new HashMap<String, String>();
			for (HistoricProcessInstance hiP : hiPs) {
				int interval = DateTimeUtility.daysBetween(hiP.getStartTime(), new Date());
				List<Integer>  numbers = Arrays.asList(15,23,27,30);
			    if (numbers.contains(interval)) {
			    	HistoricVariableInstanceQuery hv = historyService.createHistoricVariableInstanceQuery().processInstanceId(hiP.getId());
			    	String businessAreaCode =  (String)hv.variableName(ProcessConstants.KEY_BUSINESS_AREA_CODE).singleResult().getValue();
			    	if (!stationManagerMobileMap.containsKey(businessAreaCode)) {
			    		String stationManagerMobile = null;
				    	try {
								if ((stationManagerMobile = employeeHandler.getStationManagerMobile(businessAreaCode)) != null ) {
									stationManagerMobileMap.put(businessAreaCode,stationManagerMobile);
								}else {
									LOG.warn("StationManager Mobile of BusinessAreaCode({}) is null", businessAreaCode);
									throw new Exception();
								}
						} catch (Exception e) {
							LOG.error("StationManager Mobile Obtain Failed", e);
						}
			    	}
			    	String endDate = DateTimeUtility.formatYYYYMMDD(DateTimeUtility.addDays(DateTimeUtility.getMinTimeOfDay(hiP.getStartTime()),30));
					messageHandler.sendAlertMessage((String)hv.variableName(ProcessConstants.KEY_FULL_NAME).singleResult().getValue(),(String)hv.variableName(ProcessConstants.KEY_MOBILE).singleResult().getValue(),endDate,RoleType.DELIVERY_STAFF);
					messageHandler.sendAlertMessage((String)hv.variableName(ProcessConstants.KEY_FULL_NAME).singleResult().getValue(), stationManagerMobileMap.get(businessAreaCode) ,endDate,RoleType.STATION_MANAGER);
				}
			}
		}
		LOG.info("ProcessCheckAlertJobTask Completed !");
	}
	
}
