package com.i360r.bpm.business.handler.businessarea.fund.result;

import java.util.Date;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.service.rs.process.revenueApply.RevenueApplyConstants;
import com.i360r.bpm.service.rs.process.revenueApply.api.RevenueApplyDetailVO;
import com.i360r.cds.api.internal.rs.employee.EmployeeSO;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateRevenueApplyRequest;

public class RevenueApplyResultHandler extends AbstractBusinessAreaFundResultHandler implements IProcessResultHandler {
	
	private static final String TRUN_OVER = "TRUN_OVER";
	
	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		
		RevenueApplyDetailVO detail = ProcessUtility.getProcessObject(processVariables, RevenueApplyDetailVO.class);
		
		CreateRevenueApplyRequest request = new CreateRevenueApplyRequest();
		
		request.setBusinessAreaCode(detail.getBusinessAreaCode());
		request.setAmount(detail.getAmount());
		request.setRevenueTypeCode(detail.getRevenueTypeCode());
		request.setRevenueId(detail.getRevenueId());
		
		String createdByCode = (String)processVariables.get(ProcessConstants.KEY_CREATED_BY_CODE);
		String createedByName = (String)processVariables.get(ProcessConstants.KEY_CREATED_BY_NAME);
		request.setSubmittedByCode(createdByCode);
		request.setSubmittedByName(createedByName);

		String revenueTypeCode = detail.getRevenueTypeCode();
		Date payTime = (Date)processVariables.get(ProcessConstants.KEY_PAY_TIME);
		if (TRUN_OVER.equals(revenueTypeCode)) {
			request.setTurnoverTime(payTime);
			request.setTurnoveredByCode(createdByCode);
			request.setTurnoveredByName(createedByName);
			
		} else {
			String paidByCode = (String)processVariables.get(ProcessConstants.KEY_PAID_BY_CODE);
			request.setPayTime(payTime);
			request.setPaidByCode(paidByCode);
			
			EmployeeSO employee = employeeHandler.getEmployeeByCode(paidByCode);
			request.setPaidByName(employee.getFullName());
		}
		
		getBusinessAreaFundManageHandler().createRevenueApply(request);
		getMessageHandler().notifyApplierNotPass(execution);
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		getMessageHandler().notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		return RevenueApplyConstants.PROCESS_DEFINITION_KEY;
	}

}
