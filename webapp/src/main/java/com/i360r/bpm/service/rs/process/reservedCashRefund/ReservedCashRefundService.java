package com.i360r.bpm.service.rs.process.reservedCashRefund;

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
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.reservedCashRefund.api.IReservedCashRefundService;
import com.i360r.bpm.service.rs.process.reservedCashRefund.api.ReservedCashCashierConfirmRequest;
import com.i360r.bpm.service.rs.process.reservedCashRefund.api.ReservedCashRefundDetailVO;
import com.i360r.bpm.service.rs.process.reservedCashRefund.api.ReservedCashRefundHistoryReponse;
import com.i360r.bpm.service.rs.process.reservedCashRefund.api.ReservedCashRefundHistoryVO;
import com.i360r.bpm.service.rs.process.reservedCashRefund.api.ReservedCashRefundHitoryRequest;
import com.i360r.bpm.service.rs.process.reservedCashRefund.api.ReservedCashRefundModifyRequest;
import com.i360r.bpm.service.rs.process.reservedCashRefund.api.ReservedCashRefundRequest;
import com.i360r.framework.common.model.PagingResult;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.i18n.MessageSourceManager;
import com.i360r.framework.log.Log;
import com.i360r.framework.service.api.exception.IllegalRequestException;

public class ReservedCashRefundService implements IReservedCashRefundService {
	private static final Log LOG = Log.getLog(ReservedCashRefundService.class);

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
	
	@Override
	public ProcessDetailResponse<ReservedCashRefundDetailVO> getDetail(
			String processInstanceId) {
		return processHandler.getProcessDetail(processInstanceId, ReservedCashRefundDetailVO.class);
	}

	@Override
	public ServiceResponse startProcess(ReservedCashRefundRequest request)
			throws Exception {
		if (request.getChangeAmount().compareTo(new BigDecimal(0)) <= 0) {
			throw new IllegalRequestException();
		}
	
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		processHandler.startProcess(employee, request, ReservedCashRefundConstants.PROCESS_DEFINITION_KEY, AccountType.EMPLOYEE);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse logisticsDirectorApprove(ApproveRequest request)
			throws Exception {
		processHandler.completeTask(request, ReservedCashRefundConstants.TASK_LOGISTICS_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse directManagementCityManagerApprove(ApproveRequest request) throws Exception {;
		processHandler.completeTask(request, ReservedCashRefundConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse operationManagementViceDirectorApprove(
			ApproveRequest request) throws Exception {
		processHandler.completeTask(request, ReservedCashRefundConstants.TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse modifyApplicationReport(
			ReservedCashRefundModifyRequest request) throws Exception {
		if (request.getChangeAmount().compareTo(new BigDecimal(0)) <= 0) {
			throw new IllegalRequestException();
		}
		processHandler.completeTask(request, ReservedCashRefundConstants.TASK_APPROVE_REAPPLY);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse stationManagerConfirm(ApproveRequest request)
			throws Exception {
		Task task = taskService.createTaskQuery().taskId(request.getTaskId()).singleResult();
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(ProcessConstants.KEY_PAID_BY_CODE, task.getAssignee());
		variables.put(ProcessConstants.KEY_PAY_TIME, task.getCreateTime());
		processHandler.completeTask(request, variables, ReservedCashRefundConstants.TASK_STATION_MANAGER_CONFIRMD);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse cashierConfirm(ReservedCashCashierConfirmRequest request)
			throws Exception {
		if(request.getApproved()
				&& StringUtils.isBlank(request.getActualPaymentTime())) {
			throw new RemoteServerException(MessageSourceManager.getMessage("msg.refund.reservedCash"));
		}
		processHandler.completeTask(request, ReservedCashRefundConstants.TASK_CASHIER_CONFIRMD);
		return new ServiceResponse();
	}

	@Override
	public ReservedCashRefundHistoryReponse getHistory(
			ReservedCashRefundHitoryRequest request) throws Exception {
		ReservedCashRefundHistoryReponse response = new ReservedCashRefundHistoryReponse();
		if(StringUtils.isBlank(request.getBusinessAreaCode())
				|| StringUtils.isBlank(request.getProcessInstanceId())) {
			throw new IllegalRequestException();
		}
		
		/*HistoricProcessInstanceQuery query = getHistoryService().createHistoricProcessInstanceQuery();
		query = query.processDefinitionKey(ReservedCashRefundConstants.PROCESS_DEFINITION_KEY);
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
		
		StringBuilder whereSql = new StringBuilder("where rep.`KEY_` = '" + ReservedCashRefundConstants.PROCESS_DEFINITION_KEY + "'"
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
		
		List<ReservedCashRefundHistoryVO> vos = new ArrayList<ReservedCashRefundHistoryVO>();
		response.setHistories(vos);
		
		query.sql(baseSql.toString());
		List<HistoricProcessInstance> hpis = query.listPage((request.getPageNumber() - 1) * request.getPageSize(), request.getPageSize()); 
		if(hpis == null) {
			return response;
		}
		
		for (HistoricProcessInstance hpi : hpis) {
			ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
					.processDefinitionId(hpi.getProcessDefinitionId()).singleResult();
			
			List<HistoricVariableInstance> hvis = historyService.createHistoricVariableInstanceQuery()
					.processInstanceId(hpi.getProcessDefinitionId()).list();
			
			Map<String, Object> variableMap = ProcessUtility
					.getVariableMap(hvis).get(ProcessConstants.KEY_NON_TASK);
			
			ProcessInstance pi = runtimeService.createProcessInstanceQuery()
					.processInstanceId(hpi.getProcessDefinitionId()).singleResult();
			 String currentTaskName = "";
			 if(pi != null){
					Task task = taskService.createTaskQuery().processInstanceId(
							hpi.getId()).taskDefinitionKey(pi.getActivityId()).singleResult();
				 
				 if(task != null) {
					 currentTaskName = task.getName();
				 }
			 }
			 vos.add(ReservedCashRefundHistoryVO.toVO(pd, hpi, variableMap, currentTaskName));
		}
		
		return response;
	}
	
	//以下为兼容老流程代码，员工账号统一1.2
	@Override
	public ServiceResponse directManagementLargeAreaDirectorApprove(ApproveRequest request) throws Exception {
		processHandler.completeTask(request, ReservedCashRefundConstants.TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}

}
