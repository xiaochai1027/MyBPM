package com.i360r.bpm.business.handler.deliverystaff.result;

import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.deliverystaff.api.IDeliveryStaffHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.deliverystaff.grade.DeliveryStaffGradeConstants;
import com.i360r.bpm.service.rs.process.deliverystaff.grade.api.DeliveryStaffGradeDetailVO;

public class DeliveryStaffGradeResultHandler extends AbstractEngineHandler implements IProcessResultHandler {

	@Autowired
	private IMessageNotifyHandler messageHandler;

	@Autowired
	private IDeliveryStaffHandler deliveryStaffHandler;

	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {

		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		DeliveryStaffGradeDetailVO detail = ProcessUtility.getProcessObject(processVariables, DeliveryStaffGradeDetailVO.class);

		deliveryStaffHandler.changeDeliveryStaffGrade(detail.getDeliveryStaffId(), detail.getDeliveryStaffGradeTypeCode(), detail.getChangeGradeDate());

		messageHandler.notifyApplierPass(execution);
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {

		messageHandler.notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		return DeliveryStaffGradeConstants.PROCESS_DEFINITION_KEY;
	}

}
