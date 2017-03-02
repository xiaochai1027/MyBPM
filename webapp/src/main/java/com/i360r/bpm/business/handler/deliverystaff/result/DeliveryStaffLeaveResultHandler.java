package com.i360r.bpm.business.handler.deliverystaff.result;

import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.deliverystaff.api.IDeliveryStaffHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.deliverystaffleave.DeliveryStaffLeaveConstants;
import com.i360r.oas.api.internal.rs.deliverystaff.leave.LeaveSuccessRequest;

public class DeliveryStaffLeaveResultHandler extends AbstractEngineHandler implements IProcessResultHandler {

	@Autowired
	private IDeliveryStaffHandler deliveryStaffHandler;
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		
		String createdByCode = (String)processVariables.get(ProcessConstants.KEY_CREATED_BY_CODE);
		String reason = (String)processVariables.get(DeliveryStaffLeaveConstants.KEY_REASON);
		String leaveTypeCode = (String)processVariables.get(DeliveryStaffLeaveConstants.KEY_LEAVE_TYPE_CODE);
		String fromDateHour = (String)processVariables.get(DeliveryStaffLeaveConstants.KEY_FROM_DATE_HOUR);
		String toDateHour = (String)processVariables.get(DeliveryStaffLeaveConstants.KEY_TO_DATE_HOUR);
		
		LeaveSuccessRequest request = new LeaveSuccessRequest();
		request.setEmployeePositionCode(createdByCode);
		request.setReason(reason);
		request.setLeaveTypeCode(leaveTypeCode);
		request.setFromDateHour(fromDateHour);
		request.setToDateHour(toDateHour);
		
		deliveryStaffHandler.leaveSuccess(request);
		
		messageHandler.notifyApplierPass(execution);
	}
	
	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		
		messageHandler.notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		return DeliveryStaffLeaveConstants.PROCESS_DEFINITION_KEY;
	}

}
