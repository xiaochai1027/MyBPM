package com.i360r.bpm.service.rs.process.cityReimburse;

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
import org.springframework.util.CollectionUtils;

import com.i360r.bpm.business.exception.RemoteServerException;
import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.cityReimburse.api.CityReimburseCashierConfirmRequest;
import com.i360r.bpm.service.rs.process.cityReimburse.api.CityReimburseCreateRequest;
import com.i360r.bpm.service.rs.process.cityReimburse.api.CityReimburseDetailVO;
import com.i360r.bpm.service.rs.process.cityReimburse.api.CityReimburseHistoryItemRequest;
import com.i360r.bpm.service.rs.process.cityReimburse.api.CityReimburseHistoryItemResponse;
import com.i360r.bpm.service.rs.process.cityReimburse.api.CityReimburseHistoryItemVO;
import com.i360r.bpm.service.rs.process.cityReimburse.api.CityReimburseHistoryRequest;
import com.i360r.bpm.service.rs.process.cityReimburse.api.CityReimburseHistoryResponse;
import com.i360r.bpm.service.rs.process.cityReimburse.api.CityReimburseHistoryVO;
import com.i360r.bpm.service.rs.process.cityReimburse.api.CityReimburseItemVO;
import com.i360r.bpm.service.rs.process.cityReimburse.api.CityReimburseModifyRequest;
import com.i360r.bpm.service.rs.process.cityReimburse.api.CityReimburseRequest;
import com.i360r.bpm.service.rs.process.cityReimburse.api.ICityReimburseService;
import com.i360r.framework.common.model.PagingResult;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.i18n.MessageSourceManager;
import com.i360r.framework.log.Log;
import com.i360r.framework.service.api.exception.IllegalRequestException;

public class CityReimburseService implements ICityReimburseService {
	private static final Log LOG = Log.getLog(CityReimburseService.class);

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
	public ProcessDetailResponse<CityReimburseDetailVO> getDetail(
			String processInstanceId) {
		return processHandler.getProcessDetail(processInstanceId, CityReimburseDetailVO.class);
	}

	@Override
	public ServiceResponse startProcess(CityReimburseCreateRequest request) throws Exception {

		
		if (request.getReimburseItems().size() <= 0) {
			throw new IllegalRequestException();
		}
		
		if (request.getTotalAmount().compareTo(CityReimburseConstants.APPROVE_TOP_LIMIT) > 0) {
			throw new IllegalRequestException();
		}
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		String mobile = employeeHandler.getEmployeeMobileById(employee.getCode());
		Map<String, Object> customVariables = new HashMap<String, Object>();
		customVariables.put(CityReimburseConstants.TASK_EMPLOYEE_POSITION, employee.getPositionName());
		customVariables.put(CityReimburseConstants.TASK_EMPLOYEE_MOBILE, mobile);
		processHandler.startProcess(employee, request, CityReimburseConstants.PROCESS_DEFINITION_KEY, customVariables, AccountType.EMPLOYEE);
		return new ServiceResponse();
	}	
	
	@Override
	public ServiceResponse directManagementCityManagerApprove(CityReimburseRequest request) throws Exception {
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, CityReimburseConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED);
		return new ServiceResponse();
	}
	@Override
	public ServiceResponse operationManagementViceDirectorApprove(
			CityReimburseRequest request) throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request, CityReimburseConstants.TASK_EXCEED_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVED_LIMIT);
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, CityReimburseConstants.TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse operationManagementDirectorApprove(CityReimburseRequest request)
			throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request, CityReimburseConstants.TASK_EXCEED_OPERATION_MANAGEMENT_DIRECTOR_APPROVED_LIMIT);
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, CityReimburseConstants.TASK_OPERATION_MANAGEMENT_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse COOApprove(CityReimburseRequest request)
			throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request, CityReimburseConstants.TASK_EXCEED_COO_APPROVED_LIMIT);
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());		
		processHandler.completeTask(request, variables, CityReimburseConstants.TASK_COO_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse CEOApprove(CityReimburseRequest request)
			throws Exception {
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, CityReimburseConstants.TASK_CEO_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse modifyReimburseApplication(
			CityReimburseModifyRequest request) throws Exception {
		if (request.getTotalAmount().compareTo(new BigDecimal(0)) == 0) {
			throw new IllegalRequestException();
		}
		processHandler.completeTask(request, CityReimburseConstants.TASK_APPROVE_REAPPLY);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse cashierConfirm(CityReimburseCashierConfirmRequest request)
			throws Exception {
		if (request.getApproved()
				&& StringUtils.isBlank(request.getActualPaymentTime())) {
			throw new RemoteServerException(MessageSourceManager.getMessage("msg.before.reimburse"));
		}
		
		Task task = taskService.createTaskQuery().taskId(request.getTaskId()).singleResult();
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(ProcessConstants.KEY_PAID_BY_CODE, task.getAssignee());
		variables.put(ProcessConstants.KEY_PAY_TIME, task.getCreateTime());
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, CityReimburseConstants.TASK_CASHIER_CONFIRMED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse accountantConfirm(CityReimburseRequest request)
			throws Exception {
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, CityReimburseConstants.TASK_ACCOUNTANT_CONFIRMED);
		return new ServiceResponse();
	}
	
	private Map<String, Object> exceedApproveLimit(CityReimburseRequest request, String keyword) {
		BigDecimal amount = request.getTotalAmount();
		
		Boolean exceedApproveLimit = processHandler.exceedEmployeeApproveLimit(amount, request.getTaskId());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(keyword, exceedApproveLimit);
		return variables;
	}

	@Override
	public CityReimburseHistoryResponse getHistory(
			CityReimburseHistoryRequest request) throws Exception {
		
		if(StringUtils.isBlank(request.getCityCode())
				|| StringUtils.isBlank(request.getProcessInstanceId())) {
			throw new IllegalRequestException();
		}
		
		CityReimburseHistoryResponse response = new CityReimburseHistoryResponse();
		
		NativeHistoricProcessInstanceQuery query = historyService.createNativeHistoricProcessInstanceQuery();
		StringBuilder baseSql = new StringBuilder("select distinct T.* from " + managementService.getTableName(HistoricProcessInstance.class) + " T "
				+ " inner join " + managementService.getTableName(ProcessDefinition.class) + " rep on rep.`ID_` = T.`PROC_DEF_ID_` "
				+ " inner join " + managementService.getTableName(HistoricVariableInstance.class) + " hiv on hiv.`PROC_INST_ID_` = T.`PROC_INST_ID_` "
				);
		
		StringBuilder countSql =new StringBuilder("select count(distinct T.ID_) from " + managementService.getTableName(HistoricProcessInstance.class) + " T "
				+ " inner join " + managementService.getTableName(ProcessDefinition.class) + " rep on rep.`ID_` = T.`PROC_DEF_ID_` "
				+ " inner join " + managementService.getTableName(HistoricVariableInstance.class) + " hiv on hiv.`PROC_INST_ID_` = T.`PROC_INST_ID_` "
				);
		
		StringBuilder whereSql = new StringBuilder("where rep.`KEY_` = '" + CityReimburseConstants.PROCESS_DEFINITION_KEY + "'"
				+ " and hiv.`NAME_` = 'cityCode' and hiv.`TEXT_` = " + request.getCityCode()
				+ " and T.`PROC_INST_ID_` <> " + request.getProcessInstanceId()
				+ " ORDER BY T.START_TIME_ desc");
		
		baseSql.append(whereSql.toString());
		countSql.append(whereSql.toString());
		
		LOG.debug(baseSql.toString());
		LOG.debug(countSql.toString());
		
		//查询历史记录
		/*HistoricProcessInstanceQuery query = getHistoryService().createHistoricProcessInstanceQuery();
		query = query.processDefinitionKey(SpendBeforeReimburseConstants.PROCESS_DEFINITION_KEY);
		query = query.variableValueEquals("businessAreaCode", request.getBusinessAreaCode());
		query = query.orderByProcessInstanceStartTime().desc();*/
		
		long count = query.sql(countSql.toString()).count();
		PagingResult result = new PagingResult();
		result.setPageNumber(request.getPageNumber());
		result.setPageSize(request.getPageSize());
		result.setRecordNumber((int)count);
		response.setPagingResult(result);

		List<CityReimburseHistoryVO> vos = new ArrayList<CityReimburseHistoryVO>();
		response.setHistories(vos);
		
		query.sql(baseSql.toString());
		List<HistoricProcessInstance> hpis = query.listPage((request.getPageNumber() - 1) * request.getPageSize(), request.getPageSize());
		
		if (hpis == null) {
			return response;
		}
		
		for(HistoricProcessInstance hpi : hpis) {
			ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
					.processDefinitionId(hpi.getProcessDefinitionId()).singleResult();
			
			List<HistoricVariableInstance> variables = historyService
					.createHistoricVariableInstanceQuery().processInstanceId(hpi.getId()).list();
			
			Map<String, Object> variableMap = ProcessUtility
					.getVariableMap(variables).get(ProcessConstants.KEY_NON_TASK);
			
			ProcessInstance pi = runtimeService.createProcessInstanceQuery()
					.processInstanceId(hpi.getId()).singleResult();
			
			String currentTaskName = "";
			if(pi != null) {
				Task task = taskService.createTaskQuery().processInstanceId(
						hpi.getId()).taskDefinitionKey(pi.getActivityId()).singleResult();
				if(task != null) {
					currentTaskName = task.getName();
				}
			}
			
			vos.add(CityReimburseHistoryVO.toVO(hpi, pd, variableMap, currentTaskName));
		}
		return response;
	}

	@Override
	public CityReimburseHistoryItemResponse geteReimburseItem(
			CityReimburseHistoryItemRequest request) throws Exception {
		CityReimburseHistoryItemResponse response = new CityReimburseHistoryItemResponse();
		List<HistoricVariableInstance> hpis = historyService
				.createHistoricVariableInstanceQuery().processInstanceId(request.getProcessInstanceId()).list();
		Map<String, Object> variableMap = ProcessUtility.getVariableMap(hpis).get(ProcessConstants.KEY_NON_TASK);
		@SuppressWarnings("unchecked")
		List<CityReimburseItemVO> items = (List<CityReimburseItemVO>)variableMap.get("reimburseItems");
		
		int pageNumber = request.getPageNumber();
		int pageSize = request.getPageSize();
		int count = 0;
		if(items != null){
			count = items.size();
			int beginPageNumber = (pageNumber - 1) * pageSize;
			int endPageNumber = pageNumber * pageSize ;
			if(count < endPageNumber) {
				 endPageNumber = count;
			}
			items = items.subList(beginPageNumber, endPageNumber);
		}
		
		PagingResult result = new PagingResult();
		result.setPageNumber(pageNumber);
		result.setPageSize(pageSize);
		result.setRecordNumber(count);
		response.setPagingResult(result);
		
		response.setHistories(CityReimburseHistoryItemVO.toVOs(items));
		return response;
	}



	private Map<String, Object> completeTaskBefore(Map<String, Object> variables, CityReimburseRequest request, EmployeeCO employee, List<CityReimburseItemVO> rejectItems, Boolean approved) {
		CityReimburseDetailVO detail = processHandler.getClassDetail(request.getTaskId(), CityReimburseDetailVO.class);
		List<CityReimburseItemVO> items = detail.getReimburseItems();
		if (!approved) {
			request.setRejectItemsRemind("");
			BigDecimal totalAmount = BigDecimal.ZERO;
			//流程未通过，把items里的rejected变为false
			for (CityReimburseItemVO vo : items) {
				vo.setRejected(false);
				vo.setRejectReason(null);
				vo.setRejectPersonName(null);
				totalAmount = totalAmount.add(vo.getAmount());
			}
			request.setTotalAmount(totalAmount.setScale(2, BigDecimal.ROUND_DOWN));
		} else {
			//流程通过了，需要查看有没有rejectItems
			for (CityReimburseItemVO cityReimburseItemVO : items) {
				for (CityReimburseItemVO vo : rejectItems) {
					if (vo.getItemId().equals(cityReimburseItemVO.getItemId())) {
						cityReimburseItemVO.setRejected(true);
						cityReimburseItemVO.setRejectPersonName(employee.getName());
						cityReimburseItemVO.setRejectReason(vo.getRejectReason());
					}
				}
			}
		}
		variables.put(CityReimburseConstants.TASK_ITEMS, items);
		//有驳回项修改关键字
		if (approved && !CollectionUtils.isEmpty(rejectItems)) {
			request.setRejectItemsRemind("<span style='color:red'>有驳回项目</span>");
		}
		return variables;
	}
	
	
	
}
