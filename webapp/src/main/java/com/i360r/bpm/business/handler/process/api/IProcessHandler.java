package com.i360r.bpm.business.handler.process.api;

import java.math.BigDecimal;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.IContinueTaskRequest;
import com.i360r.bpm.service.rs.process.api.IReapplyTaskRequest;
import com.i360r.bpm.service.rs.process.api.ITaskRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.framework.extension.context.model.EmployeeCO;

public interface IProcessHandler {

	public <START_REQUEST> ProcessInstance startProcess(EmployeeCO employee, START_REQUEST request, 
			String processDefinitionKey, AccountType accountType) throws Exception;
	
	public <START_REQUEST> ProcessInstance startProcess(EmployeeCO employee, START_REQUEST request, 
			String processDefinitionKey, Map<String, Object> customVariables, AccountType accountType) throws Exception;
	
	public void completeTask(ApproveRequest request, Map<String, Object> processVariables, String processResultKey);
	
	public void completeTask(ApproveRequest request, String processResultKey);
	
	public void completeTask(IReapplyTaskRequest request, String processResultKey);
	
	public void completeTask(IContinueTaskRequest request, String processResultKey);
	
	public void completeTask(ITaskRequest request, boolean result, String resultDesc, 
			String suggestion, String processResultKey, Map<String, Object> processVariables, boolean checkRequired);
	
	public void completeTask(ITaskRequest request);
	
	public void completeTask(ITaskRequest request, Map<String, Object> taskVariables, 
			Map<String, Object> processVariables, boolean checkRequired);
	
	public <T> ProcessDetailResponse<T> getProcessDetail(String processInstanceId, Class<T> detailClazz);
	
	public <T> T getClassDetail(String taskId, Class<T> clazz);
	
	public BigDecimal getEmployeeApproveLimit(String taskId);
	
	/**
	 * 判断申请的资金额是否超过员工的审批额度
	 * 申请的额度必须小于员工审批额度的上限（不包含额度上限）
	 * 
	 * @param applyAmount 申请的金额
	 * @param taskId
	 * @return
	 */
	public boolean exceedEmployeeApproveLimit(BigDecimal applyAmount, String taskId);
	
	public void validateNumber(String number, String taskId);
	
}
