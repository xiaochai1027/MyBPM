package com.i360r.bpm.service.rs.process.reservedCashApply;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.NativeHistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.exception.RemoteServerException;
import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.reservedCashApply.api.IReservedCashApplyService;
import com.i360r.bpm.service.rs.process.reservedCashApply.api.ReservedCashApplyDetailVO;
import com.i360r.bpm.service.rs.process.reservedCashApply.api.ReservedCashApplyHistoryRequest;
import com.i360r.bpm.service.rs.process.reservedCashApply.api.ReservedCashApplyHistoryResponse;
import com.i360r.bpm.service.rs.process.reservedCashApply.api.ReservedCashApplyHistoryVO;
import com.i360r.bpm.service.rs.process.reservedCashApply.api.ReservedCashApplyModifyRequest;
import com.i360r.bpm.service.rs.process.reservedCashApply.api.ReservedCashApplyRequest;
import com.i360r.bpm.service.rs.process.reservedCashApply.api.ReservedCashCashierConfirmRequest;
import com.i360r.framework.common.model.PagingResult;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.i18n.MessageSourceManager;
import com.i360r.framework.log.Log;
import com.i360r.framework.service.api.exception.IllegalRequestException;

public class ReservedCashApplyService implements IReservedCashApplyService {
	private static final Log LOG = Log.getLog(ReservedCashApplyService.class);
	
	@Autowired
	private IProcessHandler processHandler;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	protected ManagementService managementService;
	
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private IEmployeeHandler employeeHandler;

	@Override
	public ProcessDetailResponse<ReservedCashApplyDetailVO> getDetail(
			String processInstanceId) {
		return processHandler.getProcessDetail(processInstanceId, ReservedCashApplyDetailVO.class);
	}

	@Override
	public ServiceResponse startProcess(ReservedCashApplyRequest request)
			throws Exception {
		if ((request.getChangeAmount().compareTo(new BigDecimal(0)) <= 0)
				|| (request.getChangeAmount().compareTo(ReservedCashApplyConstants.APPROVE_TOP_LIMIT) > 0)) {
			throw new IllegalRequestException();
		}
	
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();

		String mobile = employeeHandler.getEmployeeMobileById(employee.getCode());
		
		Map<String, Object> customVariables = new HashMap<String, Object>();
		customVariables.put(ReservedCashApplyConstants.TASK_EMPLOYEE_POSITION, employee.getPositionName());
		customVariables.put(ReservedCashApplyConstants.TASK_EMPLOYEE_MOBILE, mobile);
		
		processHandler.startProcess(employee, request, ReservedCashApplyConstants.PROCESS_DEFINITION_KEY, customVariables, AccountType.EMPLOYEE);
		
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse logisticsDirectorApprove(ApproveRequest request)
			throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request.getTaskId(), ReservedCashApplyConstants.TASK_EXCEED_LOGISTICS_DIRECTOR_APPROVED_LIMIT);
		processHandler.completeTask(request, variables, ReservedCashApplyConstants.TASK_LOGISTICS_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}


	@Override
	public ServiceResponse directManagementCityManagerApprove(
			ApproveRequest request) throws Exception {
		processHandler.completeTask(request, ReservedCashApplyConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse operationManagementViceDirectorApprove(
			ApproveRequest request) throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request.getTaskId(), ReservedCashApplyConstants.TASK_EXCEED_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVED_LIMIT);
		processHandler.completeTask(request, variables, ReservedCashApplyConstants.TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse operationManagementDirectorApprove(ApproveRequest request)
			throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request.getTaskId(), ReservedCashApplyConstants.TASK_EXCEED_OPERATION_MANAGEMENT_DIRECTOR_APPROVED_LIMIT);
		processHandler.completeTask(request, variables, ReservedCashApplyConstants.TASK_OPERATION_MANAGEMENT_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse COOApprove(ApproveRequest request)
			throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request.getTaskId(), ReservedCashApplyConstants.TASK_EXCEED_COO_APPROVED_LIMIT);
		processHandler.completeTask(request, variables, ReservedCashApplyConstants.TASK_COO_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse CEOApprove(ApproveRequest request)
			throws Exception {
		processHandler.completeTask(request, ReservedCashApplyConstants.TASK_CEO_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse modifyApplicationReport(
			ReservedCashApplyModifyRequest request) throws Exception {
		if (request.getChangeAmount().compareTo(new BigDecimal(0)) <= 0) {
			throw new IllegalRequestException();
		}
		processHandler.completeTask(request, ReservedCashApplyConstants.TASK_APPROVE_REAPPLY);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse cashierConfirm(ReservedCashCashierConfirmRequest request)
			throws Exception {
		if(request.getApproved()
				&& StringUtils.isBlank(request.getActualPaymentTime())) {
			throw new RemoteServerException(MessageSourceManager.getMessage("msg.after.reservedCash"));
		}
		
		LOG.info("---------getTaskId(): {}", request.getTaskId());
		Task task = taskService.createTaskQuery().taskId(request.getTaskId()).singleResult();
		
		Map<String, Object> variables = new HashMap<String, Object>();
		LOG.info("---------getAssignee: {}", task.getAssignee());
		variables.put(ProcessConstants.KEY_PAID_BY_CODE, task.getAssignee());
		variables.put(ProcessConstants.KEY_PAY_TIME, task.getCreateTime());
		processHandler.completeTask(request, variables, ReservedCashApplyConstants.TASK_CASHIER_CONFIRMED);
		return new ServiceResponse();
	}

	private Map<String, Object> exceedApproveLimit(String taskId, String keyword) {
		ReservedCashApplyDetailVO detail = processHandler.getClassDetail(taskId, ReservedCashApplyDetailVO.class);
		BigDecimal changeAmount = detail.getChangeAmount();

		Boolean exceedApproveLimit = processHandler.exceedEmployeeApproveLimit(changeAmount, taskId);
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(keyword, exceedApproveLimit);
		return variables;
	}

	@Override
	public ReservedCashApplyHistoryResponse getHistory(
			ReservedCashApplyHistoryRequest request) throws Exception {
		ReservedCashApplyHistoryResponse response = new ReservedCashApplyHistoryResponse();
		if(StringUtils.isBlank(request.getBusinessAreaCode())
				|| StringUtils.isBlank(request.getProcessInstanceId())) {
			throw new IllegalRequestException();
		}
		/*HistoricProcessInstanceQuery query = getHistoryService().createHistoricProcessInstanceQuery();
		query = query.processDefinitionKey(ReservedCashApplyConstants.PROCESS_DEFINITION_KEY);
		query = query.variableValueEquals("businessAreaCode", request.getBusinessAreaCode());
		query = query.orderByProcessInstanceStartTime().desc();*/
		
		NativeHistoricProcessInstanceQuery query = historyService.createNativeHistoricProcessInstanceQuery();
		StringBuilder baseSql = new StringBuilder("select distinct T.* from " + managementService.getTableName(HistoricProcessInstance.class) + " T "
				+ " inner join " + managementService.getTableName(ProcessDefinition.class) + " rep on rep.`ID_` = T.`PROC_DEF_ID_` "
				+ " inner join " + managementService.getTableName(HistoricVariableInstance.class) + " hiv on hiv.`PROC_INST_ID_` = T.`PROC_INST_ID_` "
				);
		
		StringBuilder countSql =new StringBuilder("select count(distinct T.ID_) from " + managementService.getTableName(HistoricProcessInstance.class) + " T "
				+ " inner join " + managementService.getTableName(ProcessDefinition.class) + " rep on rep.`ID_` = T.`PROC_DEF_ID_` "
				+ " inner join " + managementService.getTableName(HistoricVariableInstance.class) + " hiv on hiv.`PROC_INST_ID_` = T.`PROC_INST_ID_` "
				);
		
		StringBuilder whereSql = new StringBuilder("where rep.`KEY_` = '" + ReservedCashApplyConstants.PROCESS_DEFINITION_KEY + "'"
				+ " and hiv.`NAME_` = 'businessAreaCode' and hiv.`TEXT_` = " + request.getBusinessAreaCode()
				+ " and T.`PROC_INST_ID_` <> " + request.getProcessInstanceId()
				+ " ORDER BY T.START_TIME_ desc");
		
		baseSql.append(whereSql.toString());
		countSql.append(whereSql.toString());
		
		LOG.debug(baseSql.toString());
		LOG.debug(countSql.toString());
		
		PagingResult result = new PagingResult();
		result.setPageNumber(request.getPageNumber());
		result.setPageSize(request.getPageSize());
		result.setRecordNumber((int)query.sql(countSql.toString()).count());
		response.setPagingResult(result);
		
		List<ReservedCashApplyHistoryVO> vos = new ArrayList<ReservedCashApplyHistoryVO>();
		response.setHistories(vos);
		
		query.sql(baseSql.toString());
		List<HistoricProcessInstance> hpis = query.listPage((request.getPageNumber()-1) * request.getPageSize(), request.getPageSize());
		if (hpis == null) {
			return response;
		}
		
		for(HistoricProcessInstance hpi : hpis) {
			ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
					.processDefinitionId(hpi.getProcessDefinitionId()).singleResult();
			
			List<HistoricVariableInstance> variables = historyService
					.createHistoricVariableInstanceQuery().processInstanceId(hpi.getId()).list();
			Map<String, Object> variableMaps = ProcessUtility.getVariableMap(variables).get(ProcessConstants.KEY_NON_TASK);
			ProcessInstance pi = runtimeService.createProcessInstanceQuery()
					.processInstanceId(hpi.getId()).singleResult();
			
			String currentTask = "";
			if(pi != null) {
				Task task =  taskService.createTaskQuery().processInstanceId(
						hpi.getId()).taskDefinitionKey(pi.getActivityId()).singleResult();
				
				if(task != null) {
					currentTask = task.getName();
				}
			}
			vos.add(ReservedCashApplyHistoryVO.toVO(pd, hpi, variableMaps, currentTask));
		}
		
		return response;
	}

	//以下为兼容老流程，员工账号统一1.2
	@Override
	public ServiceResponse directManagementLargeAreaDirectorApprove(
			ApproveRequest request) throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request.getTaskId(), ReservedCashApplyConstants.TASK_EXCEED_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVED_LIMIT);
		processHandler.completeTask(request, variables, ReservedCashApplyConstants.TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse logisticsChiefManagerApprove(ApproveRequest request)
			throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request.getTaskId(), ReservedCashApplyConstants.TASK_EXCEED_LOGISTICS_CHIEF_MANAGER_APPROVED_LIMIT);
		processHandler.completeTask(request, variables, ReservedCashApplyConstants.TASK_LOGISTICS_CHIEF_MANAGER_APPROVED);
		return new ServiceResponse();
	}

}
