package com.i360r.bpm.business.handler.businessarea.fund.result;

import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;

import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.service.rs.process.reservedCashApply.ReservedCashApplyConstants;
import com.i360r.bpm.service.rs.process.reservedCashApply.api.ReservedCashApplyDetailVO;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateReservedCashApplyRequest;

public class ReservedCashApplyResultHandler extends AbstractBusinessAreaFundResultHandler implements IProcessResultHandler {
	
	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		ReservedCashApplyDetailVO detail = ProcessUtility.getProcessObject(processVariables, ReservedCashApplyDetailVO.class);
		
		CreateReservedCashApplyRequest request = new CreateReservedCashApplyRequest();
		request = setCreateOrPay(request, execution, processVariables);
		request.setBusinessAreaName(detail.getBusinessAreaName());
		request.setBusinessAreaCode(detail.getBusinessAreaCode());
		request.setChangeAmount(detail.getChangeAmount());
		request.setNote(detail.getNote());
		request.setActualPaymentTime(detail.getActualPaymentTime());
		
		getBusinessAreaFundManageHandler().createReservedCashApply(request);
		getMessageHandler().notifyApplierPass(execution);
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		getMessageHandler().notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		return ReservedCashApplyConstants.PROCESS_DEFINITION_KEY;
	}

}
