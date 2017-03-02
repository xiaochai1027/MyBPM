package com.i360r.bpm.service.rs.process.revenueApply;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.exception.BusinessAreaSettlementExistException;
import com.i360r.bpm.business.exception.RemoteServerException;
import com.i360r.bpm.business.exception.UniqueVariableRequestException;
import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.business.model.ProcessUniqueScope;
import com.i360r.bpm.business.model.ProcessVariableEntity;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.api.ProcessUniqueVariableRequest;
import com.i360r.bpm.service.rs.process.revenueApply.api.CashierConfirmRequest;
import com.i360r.bpm.service.rs.process.revenueApply.api.IRevenueApplyService;
import com.i360r.bpm.service.rs.process.revenueApply.api.RevenueApplyDetailVO;
import com.i360r.bpm.service.rs.process.revenueApply.api.RevenueApplyModifyRequest;
import com.i360r.bpm.service.rs.process.revenueApply.api.RevenueApplyRequest;
import com.i360r.bpm.service.rs.process.validator.ProcessValidatorManager;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.util.DateTimeUtility;

public class RevenueApplyService implements IRevenueApplyService {

	@Autowired
	private IProcessHandler processHandler;
	
	@Autowired
	private TaskService taskService;

	@Autowired
	private IEmployeeHandler employeeHandler;
	
	@Autowired
	protected ProcessValidatorManager processValidatorManager;
	
	@Autowired
	private HistoryService historyService;
	
	@Override
	public ProcessDetailResponse<RevenueApplyDetailVO> getDetail(
			String processInstanceId) {
		return processHandler.getProcessDetail(processInstanceId, RevenueApplyDetailVO.class);
	}

	@Override
	public ServiceResponse startProcess(RevenueApplyRequest request)
			throws Exception {
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();

		String mobile = employeeHandler.getEmployeeMobileById(employee.getCode());
		
		Map<String, Object> customVariables = new HashMap<String, Object>();
		customVariables.put(RevenueApplyConstants.TASK_EMPLOYEE_POSITION, employee.getPositionName());
		customVariables.put(RevenueApplyConstants.TASK_EMPLOYEE_MOBILE, mobile);
		//校验 流程变量的唯一性
		ProcessUniqueVariableRequest uniqueVariableRequest = ProcessUtility.getProcessUniqueVariableRequest(request);
		if (uniqueVariableRequest != null) {
			validate(employee, RevenueApplyConstants.PROCESS_DEFINITION_KEY, null, uniqueVariableRequest); 	
		} 
		processHandler.startProcess(employee, request, RevenueApplyConstants.PROCESS_DEFINITION_KEY, customVariables, AccountType.EMPLOYEE);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse cashierConfirm(CashierConfirmRequest request)
			throws Exception {
		Date payDate = null;
		if (request.getApproved()) {
			String payTime = request.getPayTime();
			if (StringUtils.isBlank(payTime)) {
				throw new RemoteServerException("实际收款时间不能空");
			} else {
				try {
					payDate = DateTimeUtility.parseYYYYMMDD(payTime); 
				} catch (Exception e) {
					throw new RemoteServerException("实际收款时间格式为yyyy-MM-dd");
				}
			}
		}
		Task task = taskService.createTaskQuery().taskId(request.getTaskId()).singleResult();
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(ProcessConstants.KEY_PAID_BY_CODE, task.getAssignee());
		variables.put(ProcessConstants.KEY_PAY_TIME, payDate);
		processHandler.completeTask(request, variables, RevenueApplyConstants.TASK_CASHIER_CONFIRMD);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse modifyApplicationReport(RevenueApplyModifyRequest request)
			throws Exception {
		processHandler.completeTask(request, RevenueApplyConstants.TASK_APPROVE_REAPPLY);
		return new ServiceResponse();
	}

	public void validate(EmployeeCO employee, String processDefinitionKey, String processInstanceId,
			ProcessUniqueVariableRequest request) {
		
		List<ProcessVariableEntity> variables = request.getUniqueVariables();
		if (variables != null && variables.size() > 0) {
			for (ProcessVariableEntity variable : variables) {
				if (variable.getValue() == null) {
					throw new UniqueVariableRequestException(variable.getShowName());
				}
				HistoricProcessInstanceQuery query = 
						historyService.createHistoricProcessInstanceQuery()
						.processDefinitionKey(processDefinitionKey)
						.variableValueEquals(variable.getKey(), variable.getValue());
				if (variable.getUniqueScope() == ProcessUniqueScope.CREATOR_UNFINISHED) {
					query = query
							.variableValueEquals(ProcessConstants.KEY_CREATED_BY_CODE, employee.getEmployeePositionCode())
							.unfinished();
				} else if (variable.getUniqueScope() == ProcessUniqueScope.UNFINISHED) {
					query = query.unfinished();
				} else if (variable.getUniqueScope() == ProcessUniqueScope.UNFINISHED_PASS) {
					query = query
							.or()
							.unfinished()
							.variableValueEquals(ProcessConstants.KEY_PASS, true);
				}
				
				List<HistoricProcessInstance> list = query.list();
				if (list != null) {
					if (processInstanceId != null) {
						Iterator<HistoricProcessInstance> ite = list.iterator();
						while (ite.hasNext()) {
							HistoricProcessInstance hpi = ite.next();
							if (hpi.getId().equals(processInstanceId)) {
								ite.remove();
							}
						}
					}
					if (list.size() > 0) {
						throw new BusinessAreaSettlementExistException();
					}
				}
			}
		}	
	}
}
