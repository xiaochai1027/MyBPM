package com.i360r.bpm.business.handler.businessarea.fund.result;

import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;

import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.service.rs.process.loanApply.LoanApplyConstants;
import com.i360r.bpm.service.rs.process.loanApply.api.LoanApplyDetailVO;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateLoanApplyRequest;

public class LoanApplyResultHandler extends AbstractBusinessAreaFundResultHandler implements IProcessResultHandler {

	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		LoanApplyDetailVO detail = ProcessUtility.getProcessObject(processVariables, LoanApplyDetailVO.class);
		
		CreateLoanApplyRequest request = new CreateLoanApplyRequest();
		request = setCreateOrPay(request, execution, processVariables);
		request.setAmount(detail.getAmount());
		request.setBusinessAreaName(detail.getBusinessAreaName());
		request.setBusinessAreaCode(detail.getBusinessAreaCode());
		request.setLoanTypeCode(detail.getLoanTypeCode());
		request.setNote(detail.getNote());
		request.setActualPaymentTime(detail.getActualPaymentTime());
		
		getBusinessAreaFundManageHandler().createLoanApply(request);
		getMessageHandler().notifyApplierPass(execution);
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		getMessageHandler().notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		return LoanApplyConstants.PROCESS_DEFINITION_KEY;
	}

}
