package com.i360r.bpm.business.handler.businessarea.fund.result;

import java.util.Date;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.businessarea.fund.api.IBusinessAreaFundHandler;
import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.cds.api.internal.rs.employee.EmployeeSO;
import com.i360r.framework.log.Log;
import com.i360r.oas.api.internal.rs.businessarea.fund.AbstractBusinessAreaFundRequest;

public abstract class AbstractBusinessAreaFundResultHandler extends AbstractEngineHandler {
	private static final Log LOG = Log.getLog(AbstractBusinessAreaFundResultHandler.class);
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@Autowired
	protected IEmployeeHandler employeeHandler;
	
	@Autowired
	private IBusinessAreaFundHandler businessAreaFundManageHandler;

	public <T extends AbstractBusinessAreaFundRequest> T setCreateOrPay(T request, DelegateExecution execution, Map<String, Object> processVariables) {
		
		String createdByCode = (String)processVariables.get(ProcessConstants.KEY_CREATED_BY_CODE);
		Date createTime = (Date)processVariables.get(ProcessConstants.KEY_CREATE_TIME);
		String createdByName = (String)processVariables.get(ProcessConstants.KEY_CREATED_BY_NAME);
		String paidByCode = (String)processVariables.get(ProcessConstants.KEY_PAID_BY_CODE);
		Date payTime = (Date)processVariables.get(ProcessConstants.KEY_PAY_TIME);
		
		request.setCreateTime(createTime);
		request.setCreatedByCode(createdByCode);
		request.setPayTime(payTime);
		request.setPaidByCode(paidByCode);
		request.setCreatedByName(createdByName);
		
		LOG.info("---------------paidByCode: {}", paidByCode);
		EmployeeSO employee = employeeHandler.getEmployeeByCode(paidByCode);

		request.setPaidByName(employee.getFullName());
		
		return request;
	}

	protected IMessageNotifyHandler getMessageHandler() {
		return messageHandler;
	}

	protected IBusinessAreaFundHandler getBusinessAreaFundManageHandler() {
		return businessAreaFundManageHandler;
	}
}
