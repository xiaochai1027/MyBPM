package com.i360r.bpm.business.handler.businessarea.fund.result;

import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;

import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.service.rs.process.reservedCashRefund.ReservedCashRefundConstants;
import com.i360r.bpm.service.rs.process.reservedCashRefund.api.ReservedCashRefundDetailVO;
import com.i360r.framework.log.Log;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateReservedCashRefundRequest;

public class ReservedCashRefundResultHandler extends AbstractBusinessAreaFundResultHandler implements IProcessResultHandler {
	private static final Log LOG = Log.getLog(AbstractBusinessAreaFundResultHandler.class);
	
	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		ReservedCashRefundDetailVO detail = ProcessUtility.getProcessObject(processVariables, ReservedCashRefundDetailVO.class);
		
		LOG.info("--------execution : {}", execution.getId());
		CreateReservedCashRefundRequest request = new CreateReservedCashRefundRequest();
		request = setCreateOrPay(request, execution, processVariables);
		request.setBusinessAreaCode(detail.getBusinessAreaCode());
		request.setBusinessAreaName(detail.getBusinessAreaName());
		request.setChangeAmount(detail.getChangeAmount());
		request.setNote(detail.getNote());
		request.setActualPaymentTime(detail.getActualPaymentTime());
		
		getBusinessAreaFundManageHandler().createReservedCashRefund(request);
		getMessageHandler().notifyApplierPass(execution);
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		getMessageHandler().notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		return ReservedCashRefundConstants.PROCESS_DEFINITION_KEY;
	}

}
