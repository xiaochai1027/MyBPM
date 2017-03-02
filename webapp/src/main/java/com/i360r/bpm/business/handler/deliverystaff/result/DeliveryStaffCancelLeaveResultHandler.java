package com.i360r.bpm.business.handler.deliverystaff.result;

import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.deliverystaff.api.IDeliveryStaffHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.deliverystaffcancelleave.DeliveryStaffCancelLeaveConstants;
import com.i360r.bpm.service.rs.process.deliverystaffcancelleave.api.HalfDate;
import com.i360r.oas.api.internal.rs.deliverystaff.leave.CancelLeaveRequest;

public class DeliveryStaffCancelLeaveResultHandler extends AbstractEngineHandler implements IProcessResultHandler {

	@Autowired
	private IDeliveryStaffHandler deliveryStaffHandler;
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@SuppressWarnings("unchecked")
	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		
		String createdByCode = (String)processVariables.get(ProcessConstants.KEY_CREATED_BY_CODE);
		List<HalfDate> cancelLeaves = (List<HalfDate>) processVariables.get(DeliveryStaffCancelLeaveConstants.KEY_CANCEL_LEAVES);
		int leaveId = (Integer) processVariables.get(DeliveryStaffCancelLeaveConstants.KEY_DELIVERY_STAFF_LEAVE_ID);
		
		CancelLeaveRequest request = new CancelLeaveRequest();
		request.setEmployeePositionCode(createdByCode);
		request.setCancelLeaves(HalfDate.toTimeRangeVOList(cancelLeaves));
		request.setLeaveId(leaveId);
		
		deliveryStaffHandler.cancelLeaveSuccess(request);
		
		messageHandler.notifyApplierPass(execution);
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		
		messageHandler.notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		return DeliveryStaffCancelLeaveConstants.PROCESS_DEFINITION_KEY;
	}

}
