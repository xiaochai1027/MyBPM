package com.i360r.bpm.service.rs.process.loanRefund;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.i360r.bpm.service.rs.process.loanRefund.api.ILoanRefundService;
import com.i360r.bpm.service.rs.process.loanRefund.api.LoanRefundCashierConfirmRequest;
import com.i360r.bpm.service.rs.process.loanRefund.api.LoanRefundDetailVO;
import com.i360r.bpm.service.rs.process.loanRefund.api.LoanRefundHistoryRequest;
import com.i360r.bpm.service.rs.process.loanRefund.api.LoanRefundHistoryResponse;
import com.i360r.bpm.service.rs.process.loanRefund.api.LoanRefundHistoryVO;
import com.i360r.bpm.service.rs.process.loanRefund.api.LoanRefundModifyRequest;
import com.i360r.bpm.service.rs.process.loanRefund.api.LoanRefundRequest;
import com.i360r.framework.common.model.PagingResult;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.i18n.MessageSourceManager;
import com.i360r.framework.log.Log;
import com.i360r.framework.service.api.exception.IllegalRequestException;
import com.i360r.framework.util.NumberFormatUtility;

public class LoanRefundService implements ILoanRefundService {
	private static final Log LOG = Log.getLog(LoanRefundService.class);
	
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
	public ProcessDetailResponse<LoanRefundDetailVO> getDetail(
			String processInstanceId) {
		return processHandler.getProcessDetail(processInstanceId, LoanRefundDetailVO.class);
	}

	@Override
	public ServiceResponse startProcess(LoanRefundRequest request)
			throws Exception {
		BigDecimal amount = NumberFormatUtility.parse2DecimalPlacesThousandth(request.getAmount());
		if (amount.compareTo(new BigDecimal(0)) == 0) {
			throw new IllegalRequestException();
		}
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		processHandler.startProcess(employee, request, LoanRefundConstants.PROCESS_DEFINITION_KEY, AccountType.EMPLOYEE);
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse accountantApprove(ApproveRequest request) throws Exception {
		processHandler.completeTask(request, LoanRefundConstants.TASK_ACCOUNTANT_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse cashierConfirm(LoanRefundCashierConfirmRequest request)
			throws Exception {
		if (request.getApproved()
				&& StringUtils.isBlank(request.getActualTurnoverTime())) {
			throw new RemoteServerException(MessageSourceManager.getMessage("msg.loan.refund"));
		}
		processHandler.completeTask(request, LoanRefundConstants.TASK_CASHIER_CONFIRMED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse modifyApplicationReport(
			LoanRefundModifyRequest request) throws Exception {
		BigDecimal amount = NumberFormatUtility.parse2DecimalPlacesThousandth(request.getAmount());
		if (amount.compareTo(new BigDecimal(0)) == 0) {
			throw new IllegalRequestException();
		}
		processHandler.completeTask(request, LoanRefundConstants.TASK_APPROVE_REAPPLY);
		return new ServiceResponse();
	}

	@Override
	public LoanRefundHistoryResponse getHistory(LoanRefundHistoryRequest request)
			throws Exception {
		LoanRefundHistoryResponse response = new LoanRefundHistoryResponse();
		if(StringUtils.isBlank(request.getBusinessAreaCode())
				|| StringUtils.isBlank(request.getProcessInstanceId())) {
			throw new IllegalRequestException();
		}
		
		/*HistoricProcessInstanceQuery query = getHistoryService().createHistoricProcessInstanceQuery();
		query = query.processDefinitionKey(LoanRefundConstants.PROCESS_DEFINITION_KEY);
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
		
		StringBuilder whereSql = new StringBuilder("where rep.`KEY_` = '" + LoanRefundConstants.PROCESS_DEFINITION_KEY + "'"
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
		
		List<LoanRefundHistoryVO> vos = new ArrayList<LoanRefundHistoryVO>();
		response.setHistories(vos);
		
		query.sql(baseSql.toString());
		List<HistoricProcessInstance> hpis = query.listPage((request.getPageNumber() - 1) * request.getPageSize(), request.getPageSize());
		if(hpis == null) {
			return response;
		}
		
		for(HistoricProcessInstance hpi : hpis) {
			ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
					.processDefinitionId(hpi.getProcessDefinitionId()).singleResult();
			List<HistoricVariableInstance> hvi = historyService.createHistoricVariableInstanceQuery()
					.processInstanceId(hpi.getId()).list();
			Map<String , Object> variabelMap = ProcessUtility.getVariableMap(hvi).get(ProcessConstants.KEY_NON_TASK);
			ProcessInstance pi = runtimeService.createProcessInstanceQuery()
					.processInstanceId(hpi.getId()).singleResult();
			
			String taskName = "";
			if(pi != null) {
				Task task = taskService.createTaskQuery().processInstanceId(
						hpi.getId()).taskDefinitionKey(pi.getActivityId()).singleResult();
				
				if(task != null) {
					taskName = task.getName();
				}
			}
			vos.add(LoanRefundHistoryVO.toVO(hpi, pd, variabelMap, taskName));
		}
		
		return response;
	}

}
