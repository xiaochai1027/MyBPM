package com.i360r.bpm.service.rs.process.inner.announcement;

import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.inner.announcement.api.CreateInnerAnnouncementRequest;
import com.i360r.bpm.service.rs.process.inner.announcement.api.IInnerAnnouncementService;
import com.i360r.bpm.service.rs.process.inner.announcement.api.InnerAnnouncementDetailVO;
import com.i360r.bpm.service.rs.process.inner.announcement.api.ModifyInnerAnnouncementRequest;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;

public class InnerAnnouncementService implements IInnerAnnouncementService{
	
	@Autowired
	private IProcessHandler processHandler;

	@Override
	public ProcessDetailResponse<InnerAnnouncementDetailVO> getDetail(String processInstanceId) {
	
		return processHandler.getProcessDetail(processInstanceId, InnerAnnouncementDetailVO.class);
	}

	@Override
	public ServiceResponse startProcess(CreateInnerAnnouncementRequest request) throws Exception {
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		processHandler.startProcess(employee, request, InnerAnnouncementConstants.PROCESS_DEFINITION_KEY, AccountType.EMPLOYEE);		
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse directManageApprove(ApproveRequest request) {
		processHandler.completeTask(request, InnerAnnouncementConstants.KEY_DIRECT_MANAGER_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse applicantModifyInnerMessage(ModifyInnerAnnouncementRequest request) throws Exception {
		processHandler.completeTask(request, InnerAnnouncementConstants.TASK_APPLICANT_REAPPLY);
		return new ServiceResponse();
	}

	

}
