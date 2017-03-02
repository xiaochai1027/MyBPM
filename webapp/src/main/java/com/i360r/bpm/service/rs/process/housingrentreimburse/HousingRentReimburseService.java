package com.i360r.bpm.service.rs.process.housingrentreimburse;

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
import com.i360r.bpm.service.rs.process.housingrentreimburse.api.HousingRentReimburseCashierConfirmRequest;
import com.i360r.bpm.service.rs.process.housingrentreimburse.api.HousingRentReimburseConstants;
import com.i360r.bpm.service.rs.process.housingrentreimburse.api.HousingRentReimburseDetailVO;
import com.i360r.bpm.service.rs.process.housingrentreimburse.api.HousingRentReimburseModifyRequest;
import com.i360r.bpm.service.rs.process.housingrentreimburse.api.HousingRentReimburseRequest;
import com.i360r.bpm.service.rs.process.housingrentreimburse.api.IHousingRentReimburseService;
import com.i360r.bpm.service.rs.process.housingrentreimburse.api.ReimburseRentHistoriesRequest;
import com.i360r.bpm.service.rs.process.housingrentreimburse.api.ReimburseRentHistoriesResponse;
import com.i360r.bpm.service.rs.process.housingrentreimburse.api.ReimburseRentHistoryVO;
import com.i360r.bpm.service.rs.process.housingrentreimburse.api.ReimburseRentUploadVoucherRequest;
import com.i360r.framework.common.model.PagingResult;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.i18n.MessageSourceManager;
import com.i360r.framework.log.Log;
import com.i360r.framework.service.api.exception.IllegalRequestException;

public class HousingRentReimburseService implements IHousingRentReimburseService {

	private static final Log LOG = Log.getLog(HousingRentReimburseService.class);
	
	@Autowired
	private IEmployeeHandler employeeHandler;

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
	public ProcessDetailResponse<HousingRentReimburseDetailVO> getDetail(
			String processInstanceId) {
		
		return processHandler.getProcessDetail(processInstanceId, HousingRentReimburseDetailVO.class);
	}

	@Override
	public ServiceResponse startProcess(HousingRentReimburseRequest request)
			throws Exception {
		
		if ((request.getAmount().compareTo(new BigDecimal(0)) == 0)
				|| (request.getAmount().compareTo(ProcessConstants.APPROVE_TOP_LIMIT) > 0)) {
			throw new IllegalRequestException();
		}
		
		if (request.getLastPay()) {
			request.setNextPayDate(null);
		}
		
		if (!request.getLastPay() && StringUtils.isBlank(request.getNextPayDate())) {
			throw new IllegalRequestException("Parameter nextPayDate must be not null.");
		}
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		Map<String, Object> customVariables = new HashMap<String, Object>();

		String mobile = employeeHandler.getEmployeeMobileById(employee.getCode());
		// 用于详情中显示申请人岗位和手机号
		customVariables.put(HousingRentReimburseConstants.TASK_EMPLOYEE_POSITION, employee.getPositionName());
		customVariables.put(HousingRentReimburseConstants.TASK_EMPLOYEE_MOBILE, mobile);
		
		if (StringUtils.isBlank(request.getBusinessAreaCode())) {
			customVariables.put(ProcessConstants.KEY_LOCATION_CODE, request.getCityCode());
		}
		
		processHandler.startProcess(employee, request, HousingRentReimburseConstants.PROCESS_DEFINITION_KEY, customVariables, AccountType.EMPLOYEE);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse cityCommissarApprove(ApproveRequest request)
			throws Exception {
		processHandler.completeTask(request, HousingRentReimburseConstants.TASK_CITY_COMMISSAR_APPROVED);
		return new ServiceResponse();
	}

	
	@Override
	public ServiceResponse directManagementCityManagerApprove(
			ApproveRequest request) throws Exception {
		
		processHandler.completeTask(request, HousingRentReimburseConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse operationManagementViceDirectorApprove(
			ApproveRequest request) throws Exception {
		processHandler.completeTask(request, HousingRentReimburseConstants.TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}


	@Override
	public ServiceResponse accountantApprove(ApproveRequest request)
			throws Exception {
		processHandler.completeTask(request, HousingRentReimburseConstants.TASK_ACCOUNTANT_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse applicantModifyReimburse(
			HousingRentReimburseModifyRequest request) throws Exception {
		
		if ((request.getAmount().compareTo(new BigDecimal(0)) == 0)
				|| (request.getAmount().compareTo(ProcessConstants.APPROVE_TOP_LIMIT) > 0)) {
			throw new IllegalRequestException();
		}
		
		processHandler.completeTask(request, HousingRentReimburseConstants.TASK_APPROVE_REAPPLY);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse cashierConfirm(
			HousingRentReimburseCashierConfirmRequest request) throws Exception {
		
		if(request.getApproved()
				&& StringUtils.isBlank(request.getActualPaymentDate())) {
			throw new RemoteServerException(MessageSourceManager.getMessage("msg.deposit.apply"));
		}
		Task task = taskService.createTaskQuery().taskId(request.getTaskId()).singleResult();
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(ProcessConstants.KEY_PAID_BY_CODE, task.getAssignee());
		variables.put(ProcessConstants.KEY_PAY_TIME, task.getCreateTime());
		
		processHandler.completeTask(request, variables, HousingRentReimburseConstants.TASK_CASHIER_CONFIRMED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse uploadVoucher(
			ReimburseRentUploadVoucherRequest request) throws Exception {
		
		
		if (StringUtils.isBlank(request.getAttachmentUrl()) || StringUtils.isBlank(request.getOrigAttachmentUrl())) {
			throw new IllegalRequestException();
		}
		
		processHandler.completeTask(request);
		
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse accountantConfirm(ApproveRequest request)
			throws Exception {
		
		processHandler.completeTask(request, HousingRentReimburseConstants.TASK_ACCOUNTANT_CONFIRMED);
		return new ServiceResponse();
	}

	private Map<String, Object> exceedApproveLimit(String taskId, String keyword) {
		HousingRentReimburseDetailVO detail = processHandler.getClassDetail(taskId, HousingRentReimburseDetailVO.class);
		BigDecimal amount = detail.getAmount();

		Boolean exceedApproveLimit = processHandler.exceedEmployeeApproveLimit(amount, taskId);
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(keyword, exceedApproveLimit);
		return variables;
	}
	
	
	@Override
	public ReimburseRentHistoriesResponse getHistory(ReimburseRentHistoriesRequest request) throws Exception {
		
		if(StringUtils.isBlank(request.getCityCode())
				|| StringUtils.isBlank(request.getProcessInstanceId())) {
			
			throw new IllegalRequestException(); 
		}
		
		ReimburseRentHistoriesResponse response  = new ReimburseRentHistoriesResponse();
		
		NativeHistoricProcessInstanceQuery query = historyService.createNativeHistoricProcessInstanceQuery();
		StringBuilder baseSql = new StringBuilder("select distinct T.* from " + managementService.getTableName(HistoricProcessInstance.class) + " T "
				+ " inner join " + managementService.getTableName(ProcessDefinition.class) + " rep on rep.`ID_` = T.`PROC_DEF_ID_` "
				+ " inner join " + managementService.getTableName(HistoricVariableInstance.class) + " hiv on hiv.`PROC_INST_ID_` = T.`PROC_INST_ID_` "
				);
		
		StringBuilder countSql =new StringBuilder("select count(distinct T.ID_) from " + managementService.getTableName(HistoricProcessInstance.class) + " T "
				+ " inner join " + managementService.getTableName(ProcessDefinition.class) + " rep on rep.`ID_` = T.`PROC_DEF_ID_` "
				+ " inner join " + managementService.getTableName(HistoricVariableInstance.class) + " hiv on hiv.`PROC_INST_ID_` = T.`PROC_INST_ID_` "
				);
		
		StringBuilder whereSql = new StringBuilder("where rep.`KEY_` = '" + HousingRentReimburseConstants.PROCESS_DEFINITION_KEY + "'"
				+ " and hiv.`NAME_` = 'cityCode' and hiv.`TEXT_` = '" + request.getCityCode() + "'");
		
		if (StringUtils.isNotBlank(request.getBusinessAreaCode())) {
			whereSql.append(" and hiv.`NAME_` = 'businessAreaCode' and hiv.`TEXT_` = '").append(request.getBusinessAreaCode()).append("'");
		}
		
		whereSql.append(" and T.`PROC_INST_ID_` <> '").append(request.getProcessInstanceId()).append("'");
		whereSql.append(" ORDER BY T.START_TIME_ desc");
		
		baseSql.append(whereSql.toString());
		countSql.append(whereSql.toString());
		
		LOG.debug(baseSql.toString());
		LOG.debug(countSql.toString());
		
		PagingResult result = new PagingResult();
		result.setPageNumber(request.getPageNumber());
		result.setPageSize(request.getPageSize());
		result.setRecordNumber((int)query.sql(countSql.toString()).count());
		response.setPagingResult(result);
		
		List<ReimburseRentHistoryVO> vos = new ArrayList<ReimburseRentHistoryVO>();
		response.setHistories(vos);
		
		query.sql(baseSql.toString());
		List<HistoricProcessInstance> hpis = query.listPage((request.getPageNumber() - 1) * request.getPageSize(), request.getPageSize());
		if (hpis == null) {
			return response;
		}
		
		for(HistoricProcessInstance hpi : hpis) {
			ProcessDefinition pd = repositoryService
					.createProcessDefinitionQuery().processDefinitionId(hpi.getProcessDefinitionId()).singleResult();
			
			List<HistoricVariableInstance> variables = historyService
					.createHistoricVariableInstanceQuery().processInstanceId(hpi.getId()).list();
			Map<String, Object> variableMaps = ProcessUtility.getVariableMap(variables).get(ProcessConstants.KEY_NON_TASK);
			ProcessInstance pi = runtimeService.createProcessInstanceQuery()
					.processInstanceId(hpi.getId()).singleResult();
			
			String currentTask = "";
			if(pi != null) {
				Task task = taskService.createTaskQuery().processInstanceId(hpi.getId())
						.taskDefinitionKey(pi.getActivityId()).singleResult();
				
				if(task != null) {
					currentTask = task.getName();
				}
			}
			vos.add(ReimburseRentHistoryVO.toVO(pd, hpi, variableMaps, currentTask));
		}
		
		return response;
	}

	//以下为兼容老流程
	@Override
	public ServiceResponse directManagementAdminManagerApprove(ApproveRequest request) throws Exception {
		processHandler.completeTask(request, HousingRentReimburseConstants.TASK_DIRECT_MANAGEMENT_ADMIN_MANAGER_APPROVED);
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse directManagementLargeAreaDirectorApprove(
			ApproveRequest request) throws Exception {
		processHandler.completeTask(request, HousingRentReimburseConstants.TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}

}
