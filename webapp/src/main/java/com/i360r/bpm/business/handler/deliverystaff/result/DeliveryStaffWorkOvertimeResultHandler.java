package com.i360r.bpm.business.handler.deliverystaff.result;

import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.deliverystaff.api.IDeliveryStaffHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.deliverystaff.workovertime.DeliveryStaffWorkOvertimeConstants;
import com.i360r.oas.api.internal.rs.deliverystaff.leave.UpdateWorkOvertimeRequest;

public class DeliveryStaffWorkOvertimeResultHandler extends AbstractEngineHandler implements IProcessResultHandler {

	@Autowired
	private IDeliveryStaffHandler deliveryStaffHandler;

	@Autowired
	private IMessageNotifyHandler messageHandler;

	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {

		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		Integer workOvertimeId = (Integer) processVariables
				.get(DeliveryStaffWorkOvertimeConstants.KEY_DELIVERY_STAFF_WORK_OVETTIME_ID);

		UpdateWorkOvertimeRequest request = new UpdateWorkOvertimeRequest();
		request.setApproved(true);
		request.setWorkOvertimeId(workOvertimeId);
		deliveryStaffHandler.updateWorkOvertime(request);

		messageHandler.notifyApplierPass(execution);
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {

		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		Integer workOvertimeId = (Integer) processVariables
				.get(DeliveryStaffWorkOvertimeConstants.KEY_DELIVERY_STAFF_WORK_OVETTIME_ID);

		UpdateWorkOvertimeRequest request = new UpdateWorkOvertimeRequest();
		request.setApproved(false);
		request.setWorkOvertimeId(workOvertimeId);
		deliveryStaffHandler.updateWorkOvertime(request);

		messageHandler.notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		return DeliveryStaffWorkOvertimeConstants.PROCESS_DEFINITION_KEY;
	}

}
