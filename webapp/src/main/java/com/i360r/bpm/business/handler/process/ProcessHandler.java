package com.i360r.bpm.business.handler.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.i360r.bpm.business.exception.EntityNotExistException;
import com.i360r.bpm.business.exception.UniqueVariableRequestException;
import com.i360r.bpm.business.handler.businessarea.fund.api.IBusinessAreaFundHandler;
import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.business.util.BeanUtility;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.IContinueTaskRequest;
import com.i360r.bpm.service.rs.process.api.IReapplyTaskRequest;
import com.i360r.bpm.service.rs.process.api.ITaskRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.api.TaskResultVO;
import com.i360r.bpm.service.rs.process.fixedasset.citydistribute.FixedAssetCityDistributeConstants;
import com.i360r.bpm.service.rs.process.validator.ProcessValidatorManager;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.i18n.MessageSourceManager;
import com.i360r.framework.service.api.exception.IllegalRequestException;
import com.i360r.framework.util.DateTimeUtility;

public class ProcessHandler extends AbstractEngineHandler implements IProcessHandler {

	@Autowired
	protected IEmployeeHandler employeeHandler;
	
	@Autowired
	protected ProcessValidatorManager processValidatorManager;
	
	@Autowired
	private IBusinessAreaFundHandler businessAreaFundHandler;
	
	@Override
	@Transactional
	public <START_REQUEST> ProcessInstance startProcess(EmployeeCO employee, START_REQUEST request, String processDefinitionKey, AccountType accountType) throws Exception {
		return startProcess(employee, request, processDefinitionKey, null, accountType);
	}
	
	@Override
	@Transactional
	public <START_REQUEST> ProcessInstance startProcess(EmployeeCO employee, START_REQUEST request, String processDefinitionKey, Map<String, Object> customVariables, AccountType accountType) throws Exception {
		
		//校验 流程变量的唯一性、时间重复性
		if (!processValidatorManager.validate(employee, processDefinitionKey, null, request)) {
			throw new IllegalRequestException("employee or delivery staff code :[" + employee.getCode() + "]");
		}
		
		String mobile = employeeHandler.getEmployeeMobileById(employee.getCode());
		
		Map<String, Object> variables = ProcessUtility.getProcessVariables(null, request);
		//所有流程公用的流程变量： 流程发起人 code、name、accountType、positionNames、mobile
		variables.put(ProcessConstants.KEY_CREATE_TIME, new Date());
		variables.put(ProcessConstants.KEY_CREATED_BY_CODE, employee.getEmployeePositionCode());
		variables.put(ProcessConstants.KEY_CREATED_BY_NAME, employee.getName());
		variables.put(ProcessConstants.KEY_CREATED_BY_ACCOUNT_TYPE, accountType.name());
		variables.put(ProcessConstants.KEY_CREATOR_POSITION_NAMES, employee.getPositionName());
		variables.put(ProcessConstants.KEY_CREATOR_MOBILE, mobile);
		if (accountType == AccountType.EMPLOYEE) {
			variables.put(ProcessConstants.KEY_LAST_PROCESSOR_CODE, employee.getEmployeePositionCode());
		} else {
			variables.put(ProcessConstants.KEY_LAST_PROCESSOR_CODE, customVariables.get("stationManagerCode"));
		}

		if (customVariables != null) {
			variables.putAll(customVariables);
		}
		
		//如果有businessAreaCode作为流程变量,locationCode的流程变量值与businessAreaCode相同
		//如果businessAreaCode为空，并且流程变量里不存在KEY_LOCATION_CODE这个KEY,把KEY_LOCATION_CODE添加到流程变量里
		if (variables.get(ProcessConstants.KEY_BUSINESS_AREA_CODE) != null) {
			variables.put(ProcessConstants.KEY_LOCATION_CODE, variables.get(ProcessConstants.KEY_BUSINESS_AREA_CODE));
		} else if (!variables.containsKey(ProcessConstants.KEY_LOCATION_CODE)) {
			variables.put(ProcessConstants.KEY_LOCATION_CODE, "");
		}
		
		ProcessInstance processInstance = null;
		
		try {

			getIdentityService().setAuthenticatedUserId(employee.getEmployeePositionCode());		
			processInstance = getRuntimeService().startProcessInstanceByKey(processDefinitionKey, variables);	

		} finally {
			getIdentityService().setAuthenticatedUserId(null);
		}
		
		return processInstance;
	}
	
	@Override
	@Transactional
	public void completeTask(ApproveRequest request, Map<String, Object> processVariables, String processResultKey) {
		
		String resultDesc = request.getApproved() ? 
				MessageSourceManager.getMessage("label.pass") : MessageSourceManager.getMessage("label.notpass");
		
		completeTask(request, request.getApproved(), resultDesc, request.getSuggestion(), processResultKey, processVariables, true);
				
	}
	
	@Override
	@Transactional
	public void completeTask(ApproveRequest request, String processResultKey) {
		
		String resultDesc = request.getApproved() ? 
				MessageSourceManager.getMessage("label.pass") : MessageSourceManager.getMessage("label.notpass");
		
		completeTask(request, request.getApproved(), resultDesc, request.getSuggestion(), processResultKey, null, true);
				
	}
	
	@Override
	@Transactional
	public void completeTask(IReapplyTaskRequest request, String processResultKey) {
		
		String resultDesc = request.getReapply() ? 
				MessageSourceManager.getMessage("label.reapply") : MessageSourceManager.getMessage("label.cancel");
				
		completeTask(request, request.getReapply(), resultDesc, null, processResultKey, null, request.getReapply());				
	}
	
	@Override
	@Transactional
	public void completeTask(IContinueTaskRequest request, String processResultKey) {

		String resultDesc = request.getContinuation() ? MessageSourceManager.getMessage("label.pass") : MessageSourceManager.getMessage("label.cancel");

		completeTask(request, request.getContinuation(), resultDesc, null, processResultKey, null, request.getContinuation());				
	}
	
	@Override
	@Transactional
	public void completeTask(ITaskRequest request, 
			boolean result, String resultDesc, String suggestion, String processResultKey, Map<String, Object> processVariables, boolean checkRequired) {
		
		// 任务变量
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put(ProcessConstants.KEY_LOCAL_RESULT, resultDesc);
		taskVariables.put(ProcessConstants.KEY_LOCAL_SUGGESTION, suggestion);
		// 流程变量
		if (processVariables == null) {
			processVariables = new HashMap<String, Object>();
		}
		processVariables.put(processResultKey, result);
		
		completeTask(request, taskVariables, processVariables, checkRequired);
	}
	
	@Override
	@Transactional
	public void completeTask(ITaskRequest request) {
		completeTask(request, null, null, true);
	}
	
	@Override
	@Transactional
	public void completeTask(ITaskRequest request, 
			Map<String, Object> taskVariables, Map<String, Object> processVariables, boolean checkRequired) {
		Task task = getTaskService().createTaskQuery().taskId(request.getTaskId()).singleResult();
		if (task == null) {
			throw new EntityNotExistException("taskId=" + request.getTaskId(), "任务");
		}
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		
//      因为增加了责任人 可以代替当前处理人审批，故注释当前登陆的人岗code和当前处理人是否一致的判断
//		if (!employee.getEmployeePositionCode().equals(task.getAssignee())) {
//			throw new InvalidOperationException("taskId=" + request.getTaskId() 
//					+ ",employeeCode=" + employee.getCode() + ",assignee=" + task.getAssignee());
//		}
		
		ProcessDefinition pd = getRepositoryService().createProcessDefinitionQuery()
				.processDefinitionId(task.getProcessDefinitionId()).singleResult();
		
		if (!processValidatorManager.validate(employee, pd.getKey(), task.getProcessInstanceId(), request)) {
			throw new IllegalRequestException();
		}
		
		Map<String, Object> variables = getRuntimeService().getVariables(task.getExecutionId());
		variables = ProcessUtility.getProcessVariables(variables, request, checkRequired);
		
		// 任务变量
		if (taskVariables != null && !taskVariables.isEmpty()) {
			getTaskService().setVariablesLocal(task.getId(), taskVariables);
		}
		
		// 流程变量
		if (processVariables != null && !processVariables.isEmpty()) {
			variables.putAll(processVariables);	
		}	
		
		getTaskService().complete(task.getId(), variables);
	}
	
	@Override
	public <T> ProcessDetailResponse<T> getProcessDetail(String processInstanceId, Class<T> detailClazz) {
		
		ProcessDetailResponse<T> response = new ProcessDetailResponse<T>();
		
		HistoricProcessInstance hpi = getHistoryService()
				.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		if (hpi == null) {
			return response;
		}
		
		// 流程详情
		List<HistoricVariableInstance> variables = getHistoryService()
				.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
		
		Map<String, Map<String, Object>> variableMap = ProcessUtility.getVariableMap(variables);
		
		if (variableMap == null || variableMap.size() == 0) {
			return response;
		}
		response.setDetail(ProcessUtility.getProcessObject(
				variableMap.get(ProcessConstants.KEY_NON_TASK), detailClazz));
		
		// 处理列表
		List<HistoricActivityInstance> finishedTasks = getHistoryService()
				.createHistoricActivityInstanceQuery().finished().processInstanceId(processInstanceId)
				.orderByHistoricActivityInstanceEndTime().desc().list();
		
		List<TaskResultVO> taskVOs = new ArrayList<TaskResultVO>();
		
		for (HistoricActivityInstance hai: finishedTasks) {			
			if (hai.getActivityType().equals(ProcessConstants.ACTIVITY_TYPE_START_EVENT)) {				
				taskVOs.add(toTaskResultVO(hai, 
						variableMap.get(hai.getTaskId()), employeeHandler.getAssigneeName(hpi.getStartUserId()), employeeHandler.getPositionByEmployeePositionCode(hpi.getStartUserId())));			
			} else if (hai.getActivityType().equals(ProcessConstants.ACTIVITY_TYPE_USER_TASK)) {				
				taskVOs.add(toTaskResultVO(hai, 
						variableMap.get(hai.getTaskId()), employeeHandler.getAssigneeName(hai.getAssignee()), employeeHandler.getPositionByEmployeePositionCode(hai.getAssignee())));				
			}			
		}
		
		response.setProcessInstanceId(processInstanceId);
		response.setTaskList(taskVOs);
		
		return response;
	}
	
	private TaskResultVO toTaskResultVO(HistoricActivityInstance hai, 
			Map<String, Object> taskVariables, String assignedName, String positionName) {
		
		TaskResultVO vo = new TaskResultVO();
		
		vo.setTaskDefinitionKey(hai.getActivityId());
		vo.setAssigneeName(assignedName);
		vo.setTaskName(hai.getActivityName());
		vo.setEndTime(DateTimeUtility.formatYYYYMMDDHHMMSS(hai.getEndTime()));
		vo.setPositionName(positionName);
		if (taskVariables != null) {
			vo.setResult((String)taskVariables.get(ProcessConstants.KEY_LOCAL_RESULT));
			vo.setSuggestion((String)taskVariables.get(ProcessConstants.KEY_LOCAL_SUGGESTION));
		}
		
		return vo;
	}
	
	@Override
	public <T> T getClassDetail(String taskId, Class<T> clazz) {
		Task task = getTaskService().createTaskQuery().taskId(taskId).singleResult();
		if (task == null) {
			throw new EntityNotExistException("taskId=" + taskId, "任务");
		}
		Map<String, Object> processVariables = getRuntimeService().getVariables(task.getExecutionId());
		return ProcessUtility.getProcessObject(processVariables, clazz);
	}
	
	@Override
	public BigDecimal getEmployeeApproveLimit(String taskId) {
		Task task = getTaskService().createTaskQuery().taskId(taskId).singleResult();
		String assigneeCode = task.getAssignee();
		return businessAreaFundHandler.getApproveLimitByEmployeePositionCode(assigneeCode);
	}
	
	/**
	 * 判断申请的资金额是否超过员工的审批额度
	 * 申请的额度必须小于员工审批额度的上限（不包含额度上限）
	 * 
	 * @param applyAmount 申请的金额
	 * @param taskId
	 * @return
	 */
	public boolean exceedEmployeeApproveLimit(BigDecimal applyAmount, String taskId) {
		
		if (applyAmount == null || applyAmount.compareTo(BigDecimal.ZERO) == 0) {
			return false;
		}

		BigDecimal employeeApproveLimit = getEmployeeApproveLimit(taskId);
		if (applyAmount.compareTo(employeeApproveLimit) < 0) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 判断固定资产流程编号不能重复，若不重复则将number添加到流程变量中
	 * （因为若在请求参数中加上注解ProcessVariable的uniqueScope， 则number为空也会检查唯一性；而本意是只检查编号不为空的number）
	 * 
	 * @param number编号
	 * @param taskId
	 * @return
	 */
	public void validateNumber(String number, String taskId) {
		Task task = getTaskService().createTaskQuery().taskId(taskId).singleResult();
		if (task == null) {
			throw new EntityNotExistException("taskId=" + taskId, "任务");
		}
		
		ProcessDefinition pd = getRepositoryService().createProcessDefinitionQuery()
				.processDefinitionId(task.getProcessDefinitionId()).singleResult();
		
		HistoricProcessInstanceQuery query = 
				getHistoryService().createHistoricProcessInstanceQuery()
				.processDefinitionKey(pd.getKey())
				.variableValueEquals(FixedAssetCityDistributeConstants.KEY_NUMBER, number)						
				.or()
				.unfinished()
				.variableValueEquals(ProcessConstants.KEY_PASS, true);
		
		List<HistoricProcessInstance> list = query.list();
		if (list.size() > 0) {
			throw new UniqueVariableRequestException("编号number：" + number);
		}
		
		//添加流程变量
		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put(FixedAssetCityDistributeConstants.KEY_NUMBER, number);
		RuntimeService runtimeService = BeanUtility.getRuntimeService();
		runtimeService.setVariables(task.getExecutionId(), variable);
		
	} 
}
