package com.i360r.bpm.business.handler.businessarea.fund.result;

import java.util.Date;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.service.rs.process.loanRefund.LoanRefundConstants;
import com.i360r.bpm.service.rs.process.loanRefund.api.LoanRefundDetailVO;
import com.i360r.framework.util.NumberFormatUtility;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateLoanRefundRequest;

public class LoanRefundResultHandler extends AbstractBusinessAreaFundResultHandler implements IProcessResultHandler {

	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		LoanRefundDetailVO detail = ProcessUtility.getProcessObject(processVariables, LoanRefundDetailVO.class);
		
		CreateLoanRefundRequest request = new CreateLoanRefundRequest();
		request.setAmount(NumberFormatUtility.parse2DecimalPlacesThousandth(detail.getAmount()));
		request.setLoanApplyId(detail.getLoanApplyId());
		String turnoveredByCode = (String)processVariables.get(ProcessConstants.KEY_CREATED_BY_CODE);
		Date turnoverTime = (Date)processVariables.get(ProcessConstants.KEY_CREATE_TIME);
		String turnoveredByName = (String)processVariables.get(ProcessConstants.KEY_CREATED_BY_NAME);
		request.setTurnoverTime(turnoverTime);
		request.setTurnoveredByName(turnoveredByName);
		request.setTurnoveredByCode(turnoveredByCode);
		request.setActualTurnoverTime(detail.getActualTurnoverTime());
		
		getBusinessAreaFundManageHandler().createLoanRefund(request);
		getMessageHandler().notifyApplierPass(execution);
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		getMessageHandler().notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		return LoanRefundConstants.PROCESS_DEFINITION_KEY;
	}

}
