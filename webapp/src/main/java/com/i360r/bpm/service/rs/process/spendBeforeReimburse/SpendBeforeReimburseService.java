package com.i360r.bpm.service.rs.process.spendBeforeReimburse;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.i360r.bpm.business.exception.RemoteServerException;
import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.business.util.DateTimeUtility;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.business.util.ValidateUtility;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.api.ISpendBeforeReimburseService;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.api.SpendBeforeReimburseCashierConfirmRequest;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.api.SpendBeforeReimburseCreateRequest;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.api.SpendBeforeReimburseDetailVO;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.api.SpendBeforeReimburseExportUtils;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.api.SpendBeforeReimburseHistoryItemRequest;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.api.SpendBeforeReimburseHistoryItemResponse;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.api.SpendBeforeReimburseHistoryItemVO;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.api.SpendBeforeReimburseHistoryRequest;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.api.SpendBeforeReimburseHistoryResponse;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.api.SpendBeforeReimburseHistoryVO;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.api.SpendBeforeReimburseItemVO;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.api.SpendBeforeReimburseModifyRequest;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.api.SpendBeforeReimburseRequest;
import com.i360r.framework.common.model.PagingResult;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.i18n.MessageSourceManager;
import com.i360r.framework.log.Log;
import com.i360r.framework.service.api.exception.IllegalRequestException;
import com.i360r.framework.util.ExcelUtility;

public class SpendBeforeReimburseService implements ISpendBeforeReimburseService {
	private static final Log LOG = Log.getLog(SpendBeforeReimburseService.class);

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
	public ProcessDetailResponse<SpendBeforeReimburseDetailVO> getDetail(
			String processInstanceId) {
		return processHandler.getProcessDetail(processInstanceId, SpendBeforeReimburseDetailVO.class);
	}

	@Override
	public ServiceResponse startProcess(SpendBeforeReimburseCreateRequest request) throws Exception {
		
		ValidateUtility.validateSpendBeforeReimburseCount(
				SpendBeforeReimburseConstants.PROCESS_DEFINITION_KEY,
				request.getBusinessAreaCode(), historyService, runtimeService);
		
		if (request.getReimburseItems().size() <= 0) {
			throw new IllegalRequestException();
		}
		
		if (request.getTotalAmount().compareTo(SpendBeforeReimburseConstants.APPROVE_TOP_LIMIT) > 0) {
			throw new IllegalRequestException();
		}
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		String mobile = employeeHandler.getEmployeeMobileById(employee.getCode());
		Map<String, Object> customVariables = new HashMap<String, Object>();
		customVariables.put(SpendBeforeReimburseConstants.TASK_EMPLOYEE_POSITION, employee.getPositionName());
		customVariables.put(SpendBeforeReimburseConstants.TASK_EMPLOYEE_MOBILE, mobile);
		processHandler.startProcess(employee, request, SpendBeforeReimburseConstants.PROCESS_DEFINITION_KEY, customVariables, AccountType.EMPLOYEE);
		return new ServiceResponse();
	}	
	
	@Override
	public ServiceResponse directManagementCityManagerApprove(SpendBeforeReimburseRequest request) throws Exception {
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, SpendBeforeReimburseConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED);
		return new ServiceResponse();
	}
	@Override
	public ServiceResponse operationManagementViceDirectorApprove(
			SpendBeforeReimburseRequest request) throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request, SpendBeforeReimburseConstants.TASK_EXCEED_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVED_LIMIT);
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, SpendBeforeReimburseConstants.TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse operationManagementDirectorApprove(SpendBeforeReimburseRequest request)
			throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request, SpendBeforeReimburseConstants.TASK_EXCEED_OPERATION_MANAGEMENT_DIRECTOR_APPROVED_LIMIT);
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, SpendBeforeReimburseConstants.TASK_OPERATION_MANAGEMENT_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse COOApprove(SpendBeforeReimburseRequest request)
			throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request, SpendBeforeReimburseConstants.TASK_EXCEED_COO_APPROVED_LIMIT);
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());		
		processHandler.completeTask(request, variables, SpendBeforeReimburseConstants.TASK_COO_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse CEOApprove(SpendBeforeReimburseRequest request)
			throws Exception {
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, SpendBeforeReimburseConstants.TASK_CEO_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse modifyReimburseApplication(
			SpendBeforeReimburseModifyRequest request) throws Exception {
		if (request.getTotalAmount().compareTo(new BigDecimal(0)) == 0) {
			throw new IllegalRequestException();
		}
		processHandler.completeTask(request, SpendBeforeReimburseConstants.TASK_APPROVE_REAPPLY);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse cashierConfirm(SpendBeforeReimburseCashierConfirmRequest request)
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
		processHandler.completeTask(request, variables, SpendBeforeReimburseConstants.TASK_CASHIER_CONFIRMED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse accountantConfirm(SpendBeforeReimburseRequest request)
			throws Exception {
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, SpendBeforeReimburseConstants.TASK_ACCOUNTANT_CONFIRMED);
		return new ServiceResponse();
	}
	
	private Map<String, Object> exceedApproveLimit(SpendBeforeReimburseRequest request, String keyword) {
		BigDecimal amount = request.getTotalAmount();
		
		Boolean exceedApproveLimit = processHandler.exceedEmployeeApproveLimit(amount, request.getTaskId());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(keyword, exceedApproveLimit);
		return variables;
	}

	@Override
	public SpendBeforeReimburseHistoryResponse getHistory(
			SpendBeforeReimburseHistoryRequest request) throws Exception {
		
		if(StringUtils.isBlank(request.getBusinessAreaCode())
				|| StringUtils.isBlank(request.getProcessInstanceId())) {
			throw new IllegalRequestException();
		}
		
		SpendBeforeReimburseHistoryResponse response = new SpendBeforeReimburseHistoryResponse();
		
		NativeHistoricProcessInstanceQuery query = historyService.createNativeHistoricProcessInstanceQuery();
		StringBuilder baseSql = new StringBuilder("select distinct T.* from " + managementService.getTableName(HistoricProcessInstance.class) + " T "
				+ " inner join " + managementService.getTableName(ProcessDefinition.class) + " rep on rep.`ID_` = T.`PROC_DEF_ID_` "
				+ " inner join " + managementService.getTableName(HistoricVariableInstance.class) + " hiv on hiv.`PROC_INST_ID_` = T.`PROC_INST_ID_` "
				);
		
		StringBuilder countSql =new StringBuilder("select count(distinct T.ID_) from " + managementService.getTableName(HistoricProcessInstance.class) + " T "
				+ " inner join " + managementService.getTableName(ProcessDefinition.class) + " rep on rep.`ID_` = T.`PROC_DEF_ID_` "
				+ " inner join " + managementService.getTableName(HistoricVariableInstance.class) + " hiv on hiv.`PROC_INST_ID_` = T.`PROC_INST_ID_` "
				);
		
		StringBuilder whereSql = new StringBuilder("where rep.`KEY_` = '" + SpendBeforeReimburseConstants.PROCESS_DEFINITION_KEY + "'"
				+ " and hiv.`NAME_` = 'businessAreaCode' and hiv.`TEXT_` = " + request.getBusinessAreaCode()
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

		List<SpendBeforeReimburseHistoryVO> vos = new ArrayList<SpendBeforeReimburseHistoryVO>();
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
			
			vos.add(SpendBeforeReimburseHistoryVO.toVO(hpi, pd, variableMap, currentTaskName));
		}
		return response;
	}

	@Override
	public SpendBeforeReimburseHistoryItemResponse geteReimburseItem(
			SpendBeforeReimburseHistoryItemRequest request) throws Exception {
		SpendBeforeReimburseHistoryItemResponse response = new SpendBeforeReimburseHistoryItemResponse();
		List<HistoricVariableInstance> hpis = historyService
				.createHistoricVariableInstanceQuery().processInstanceId(request.getProcessInstanceId()).list();
		Map<String, Object> variableMap = ProcessUtility.getVariableMap(hpis).get(ProcessConstants.KEY_NON_TASK);
		@SuppressWarnings("unchecked")
		List<SpendBeforeReimburseItemVO> items = (List<SpendBeforeReimburseItemVO>)variableMap.get("reimburseItems");
		
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
		
		response.setHistories(SpendBeforeReimburseHistoryItemVO.toVOs(items));
		return response;
	}

	@Override
	public ServiceResponse downLoadExcelFile(String processInstanceId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ServiceResponse serviceResponse = new ServiceResponse();
		HSSFWorkbook workbook = new HSSFWorkbook();
		String fileName = SpendBeforeReimburseExportUtils.LABEL_REIMBURSE_DETAIL_INFO + DateTimeUtility.formatNoSpaceYYYYMMDDHHMMSS(new Date());
		HistoricProcessInstance hpi = historyService
				.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		if (hpi != null) {
			ProcessDetailResponse<SpendBeforeReimburseDetailVO> detailVO = processHandler.getProcessDetail(processInstanceId, SpendBeforeReimburseDetailVO.class);
			ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
					.processDefinitionId(hpi.getProcessDefinitionId()).singleResult();
			SpendBeforeReimburseExportUtils.spendBeforeReimburseExcelTemplate(workbook, pd, detailVO);
		}
		
		OutputStream outputStream = null;
		try {
			fileName = ExcelUtility.encodeFileName(fileName, request);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename*=utf-8'zh_cn'" + fileName + ".xls");
			
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
		} catch (Exception e) {
			serviceResponse.setResultCode("100001");
			serviceResponse.setResultMessage(e.getMessage());
		} finally {
			if (outputStream != null) {
				outputStream.flush();
				outputStream.close();
			}
		}
		return serviceResponse;
	}

	private Map<String, Object> completeTaskBefore(Map<String, Object> variables, SpendBeforeReimburseRequest request, EmployeeCO employee, List<SpendBeforeReimburseItemVO> rejectItems, Boolean approved) {
		SpendBeforeReimburseDetailVO detail = processHandler.getClassDetail(request.getTaskId(), SpendBeforeReimburseDetailVO.class);
		List<SpendBeforeReimburseItemVO> items = detail.getReimburseItems();
		if (!approved) {
			request.setRejectItemsRemind("");
			BigDecimal totalAmount = BigDecimal.ZERO;
			//流程未通过，把items里的rejected变为false
			for (SpendBeforeReimburseItemVO vo : items) {
				vo.setRejected(false);
				vo.setRejectReason(null);
				vo.setRejectPersonName(null);
				totalAmount = totalAmount.add(vo.getAmount());
			}
			request.setTotalAmount(totalAmount.setScale(2, BigDecimal.ROUND_DOWN));
		} else {
			//流程通过了，需要查看有没有rejectItems
			for (SpendBeforeReimburseItemVO spendBeforeReimburseItemVO : items) {
				for (SpendBeforeReimburseItemVO vo : rejectItems) {
					if (vo.getItemId().equals(spendBeforeReimburseItemVO.getItemId())) {
						spendBeforeReimburseItemVO.setRejected(true);
						spendBeforeReimburseItemVO.setRejectPersonName(employee.getName());
						spendBeforeReimburseItemVO.setRejectReason(vo.getRejectReason());
					}
				}
			}
		}
		variables.put(SpendBeforeReimburseConstants.TASK_ITEMS, items);
		//有驳回项修改关键字
		if (approved && !CollectionUtils.isEmpty(rejectItems)) {
			request.setRejectItemsRemind("<span style='color:red'>有驳回项目</span>");
		}
		return variables;
	}
	
	//以下为兼容老流程，员工账号统一1.2
	//老版流程（物流总监审批）
	@Override
	public ServiceResponse logisticsDirectorApprove(SpendBeforeReimburseRequest request)	throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request, SpendBeforeReimburseConstants.TASK_EXCEED_LOGISTICS_DIRECTOR_APPROVED_LIMIT);
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, SpendBeforeReimburseConstants.TASK_LOGISTICS_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse directManagementLargeAreaDirectorApprove(SpendBeforeReimburseRequest request) throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request, SpendBeforeReimburseConstants.TASK_EXCEED_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVED_LIMIT);
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, SpendBeforeReimburseConstants.TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse logisticsChiefManagerApprove(SpendBeforeReimburseRequest request)
			throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request, SpendBeforeReimburseConstants.TASK_EXCEED_LOGISTICS_CHIEF_MANAGER_APPROVED_LIMIT);
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, SpendBeforeReimburseConstants.TASK_LOGISTICS_CHIEF_MANAGER_APPROVED);
		return new ServiceResponse();
	}

	
}
