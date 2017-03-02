package com.i360r.bpm.service.rs.process.spendAfterReimburse;

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

import com.i360r.bpm.business.exception.RemoteServerException;
import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.business.util.DateTimeUtility;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.spendAfterReimburse.api.ISpendAfterReimburseService;
import com.i360r.bpm.service.rs.process.spendAfterReimburse.api.SpendAfterReimburseCashierConfirmRequest;
import com.i360r.bpm.service.rs.process.spendAfterReimburse.api.SpendAfterReimburseDetailVO;
import com.i360r.bpm.service.rs.process.spendAfterReimburse.api.SpendAfterReimburseExcelUtility;
import com.i360r.bpm.service.rs.process.spendAfterReimburse.api.SpendAfterReimburseHistoryItemRequest;
import com.i360r.bpm.service.rs.process.spendAfterReimburse.api.SpendAfterReimburseHistoryItemResponse;
import com.i360r.bpm.service.rs.process.spendAfterReimburse.api.SpendAfterReimburseHistoryItemVO;
import com.i360r.bpm.service.rs.process.spendAfterReimburse.api.SpendAfterReimburseHistoryRequest;
import com.i360r.bpm.service.rs.process.spendAfterReimburse.api.SpendAfterReimburseHistoryResponse;
import com.i360r.bpm.service.rs.process.spendAfterReimburse.api.SpendAfterReimburseHistoryVO;
import com.i360r.bpm.service.rs.process.spendAfterReimburse.api.SpendAfterReimburseModifyRequest;
import com.i360r.bpm.service.rs.process.spendAfterReimburse.api.SpendAfterReimburseRequest;
import com.i360r.bpm.service.rs.process.spendAfterReimburse.api.SpendAfterReimburseUploadVoucherRequest;
import com.i360r.bpm.service.rs.process.spendAfterReimburse.api.SpendAfterReimburseUploadVoucherVO;
import com.i360r.bpm.service.rs.process.spendAfterReimburse.api.SpendAfterReimburseVoucherDetailResponse;
import com.i360r.framework.common.model.PagingResult;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.i18n.MessageSourceManager;
import com.i360r.framework.log.Log;
import com.i360r.framework.service.api.exception.IllegalRequestException;
import com.i360r.framework.util.ExcelUtility;

public class SpendAfterReimburseService implements ISpendAfterReimburseService {
	private static final Log LOG = Log.getLog(SpendAfterReimburseService.class);
	
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
	public ProcessDetailResponse<SpendAfterReimburseDetailVO> getDetail(String processInstanceId) {
		return processHandler.getProcessDetail(processInstanceId, SpendAfterReimburseDetailVO.class);
	}

	@Override
	public ServiceResponse startProcess(SpendAfterReimburseRequest request)
			throws Exception {
		
		if ((request.getTotalAmount().compareTo(new BigDecimal(0)) == 0)
				|| (request.getTotalAmount().compareTo(SpendAfterReimburseConstans.APPROVE_TOP_LIMIT) > 0)) {
			throw new IllegalRequestException();
		}
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		String mobile = employeeHandler.getEmployeeMobileById(employee.getCode());
		Map<String, Object> customVariables = new HashMap<String, Object>();
		customVariables.put(SpendAfterReimburseConstans.TASK_EMPLOYEE_POSITION, employee.getPositionName());
		customVariables.put(SpendAfterReimburseConstans.TASK_EMPLOYEE_MOBILE, mobile);
		processHandler.startProcess(employee, request, SpendAfterReimburseConstans.PROCESS_DEFINITION_KEY, customVariables, AccountType.EMPLOYEE);
		return new ServiceResponse();
	}

	
	@Override
	public ServiceResponse logisticsDirectorApprove(ApproveRequest request) throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request.getTaskId(), SpendAfterReimburseConstans.TASK_EXCEED_LOGISTICS_DIRECTOR_APPROVED_LIMIT);
		processHandler.completeTask(request, variables, SpendAfterReimburseConstans.TASK_LOGISTICS_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse directManagementCityManagerApprove(ApproveRequest request) throws Exception {
		processHandler.completeTask(request, SpendAfterReimburseConstans.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse operationManagementViceDirectorApprove(
			ApproveRequest request) throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request.getTaskId(), SpendAfterReimburseConstans.TASK_EXCEED_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVED_LIMIT);
		processHandler.completeTask(request, variables, SpendAfterReimburseConstans.TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse operationManagementDirectorApprove(ApproveRequest request)
			throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request.getTaskId(), SpendAfterReimburseConstans.TASK_EXCEED_OPERATION_MANAGEMENT_DIRECTOR_APPROVED_LIMIT);
		processHandler.completeTask(request, variables, SpendAfterReimburseConstans.TASK_OPERATION_MANAGEMENT_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse COOApprove(ApproveRequest request) throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request.getTaskId(), SpendAfterReimburseConstans.TASK_EXCEED_COO_APPROVED_LIMIT);
		processHandler.completeTask(request, variables, SpendAfterReimburseConstans.TASK_COO_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse CEOApprove(ApproveRequest request) throws Exception {
		processHandler.completeTask(request, SpendAfterReimburseConstans.TASK_CEO_APPROVED);
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse accountantApprove(ApproveRequest request) throws Exception {
		processHandler.completeTask(request, SpendAfterReimburseConstans.TASK_ACCOUNTANT_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse modifyReimburseApplication(SpendAfterReimburseModifyRequest request) throws Exception {
		if (request.getTotalAmount().compareTo(new BigDecimal(0)) == 0) {
			throw new IllegalRequestException();
		}
		processHandler.completeTask(request, SpendAfterReimburseConstans.TASK_APPROVE_REAPPLY);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse cashierConfirm(SpendAfterReimburseCashierConfirmRequest request)
			throws Exception {
		if(request.getApproved()
				&& StringUtils.isBlank(request.getActualPaymentTime())) {
			throw new RemoteServerException(MessageSourceManager.getMessage("msg.after.reimburse"));
		}
		Task task = taskService.createTaskQuery().taskId(request.getTaskId()).singleResult();
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(ProcessConstants.KEY_PAID_BY_CODE, task.getAssignee());
		variables.put(ProcessConstants.KEY_PAY_TIME, task.getCreateTime());
		processHandler.completeTask(request, variables, SpendAfterReimburseConstans.TASK_CASHIER_CONFIRMED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse accountantConfirm(ApproveRequest request)
			throws Exception {
		processHandler.completeTask(request, SpendAfterReimburseConstans.TASK_ACCOUNTANT_CONFIRMED);
		return new ServiceResponse();
	}
	
	@Override
	public SpendAfterReimburseVoucherDetailResponse voucherDetail(String processInstanceId) throws Exception {
		SpendAfterReimburseVoucherDetailResponse response = new SpendAfterReimburseVoucherDetailResponse();
		
		List<HistoricVariableInstance> taskVariables = historyService
				.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
		
		Map<String, Map<String, Object>> variableMap = ProcessUtility.getVariableMap(taskVariables);
		Map<String, Object> variables = variableMap.get(ProcessConstants.KEY_NON_TASK);
		@SuppressWarnings("unchecked")
		List<SpendAfterReimburseUploadVoucherVO> uploadVoucherVOs = (List<SpendAfterReimburseUploadVoucherVO>)variables.get("uploadVoucherVOs");
		response.setUploadVoucherVOs(uploadVoucherVOs);
		return response;
	}

	@Override
	public ServiceResponse uploadVoucher(SpendAfterReimburseUploadVoucherRequest request)
			throws Exception {
		if (request.getUploadVoucherVOs().size() <= 0) {
			throw new IllegalRequestException();
		}
		
		processHandler.completeTask(request);
		
		return new ServiceResponse();
	}

	private Map<String, Object> exceedApproveLimit(String taskId, String keyword) {
		SpendAfterReimburseDetailVO detail = processHandler.getClassDetail(taskId, SpendAfterReimburseDetailVO.class);
		BigDecimal amount = detail.getTotalAmount();

		Boolean exceedApproveLimit = processHandler.exceedEmployeeApproveLimit(amount, taskId);
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(keyword, exceedApproveLimit);
		return variables;
	}

	@Override
	public SpendAfterReimburseHistoryResponse getHistory(
			SpendAfterReimburseHistoryRequest request) throws Exception {
		if(StringUtils.isBlank(request.getBusinessAreaCode())
				|| StringUtils.isBlank(request.getProcessInstanceId())) {
			throw new IllegalRequestException(); 
		}
		
		SpendAfterReimburseHistoryResponse response  = new SpendAfterReimburseHistoryResponse();
		
		/*HistoricProcessInstanceQuery query = getHistoryService().createHistoricProcessInstanceQuery();
		query = query.processDefinitionKey(SpendAfterReimburseConstans.PROCESS_DEFINITION_KEY);
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
		
		StringBuilder whereSql = new StringBuilder("where rep.`KEY_` = '" + SpendAfterReimburseConstans.PROCESS_DEFINITION_KEY + "'"
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
		
		List<SpendAfterReimburseHistoryVO> vos = new ArrayList<SpendAfterReimburseHistoryVO>();
		response.setHistories(vos);
		
		query.sql(baseSql.toString());
		List<HistoricProcessInstance> hpis = query.listPage((request.getPageNumber()-1) * request.getPageSize(), request.getPageSize());
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
			vos.add(SpendAfterReimburseHistoryVO.toVO(pd, hpi, variableMaps, currentTask));
		}
		
		return response;
	}

	@Override
	public SpendAfterReimburseHistoryItemResponse getItemHistory(
			SpendAfterReimburseHistoryItemRequest request) throws Exception {
		SpendAfterReimburseHistoryItemResponse response = new SpendAfterReimburseHistoryItemResponse();
		List<HistoricVariableInstance> hvi = historyService
				.createHistoricVariableInstanceQuery().processInstanceId(request.getProcessInstanceId()).list();
		Map<String, Object> variableMap = ProcessUtility.getVariableMap(hvi).get(ProcessConstants.KEY_NON_TASK);
		
		@SuppressWarnings("unchecked")
		List<SpendAfterReimburseUploadVoucherVO> vos =(List<SpendAfterReimburseUploadVoucherVO>)variableMap.get("uploadVoucherVOs");
		int pageNumber = request.getPageNumber();
		int pageSize = request.getPageSize();
		int count = 0;
		if(vos != null) {
			count = vos.size();
			int beginPage = (pageNumber - 1) * pageSize;
			int endPage = pageNumber * pageSize;
			
			if (count < endPage) {
				endPage = count;
			}
			vos = vos.subList(beginPage, endPage);
		}
		PagingResult result = new PagingResult();
		result.setPageNumber(pageNumber);
		result.setPageSize(pageSize);
		result.setRecordNumber(count);
		response.setPagingResult(result);
		
		response.setHistories(SpendAfterReimburseHistoryItemVO.toVOs(vos));
		return response;
	}

	@Override
	public ServiceResponse downLoadExcelFile(String processInstanceId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ServiceResponse serviceResponse = new ServiceResponse();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HistoricProcessInstance hpi = historyService
				.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		
		if (hpi != null) {
			ProcessDefinition pd = repositoryService
					.createProcessDefinitionQuery().processDefinitionId(hpi.getProcessDefinitionId()).singleResult();
			ProcessDetailResponse<SpendAfterReimburseDetailVO> detailResponse = processHandler.getProcessDetail(processInstanceId, SpendAfterReimburseDetailVO.class);
			
			SpendAfterReimburseExcelUtility.createSpendAfterReimburseExcel(workbook, pd, detailResponse);
			
		}
		
		String fileName = SpendAfterReimburseExcelUtility.LABEL_REIMBURSE_DETAIL_INFO + DateTimeUtility.formatNoSpaceYYYYMMDDHHMMSS(new Date());
		OutputStream ouputStream = null;
		try {
			fileName = ExcelUtility.encodeFileName(fileName, request);
            response.setContentType("application/vnd.ms-excel");     
            response.setHeader("Content-disposition", "attachment;filename*=utf-8'zh_cn'" + fileName + ".xls");
            
            ouputStream = response.getOutputStream();  
            workbook.write(ouputStream);
		} catch (Exception e) {
			serviceResponse.setResultCode("100001");
			serviceResponse.setResultMessage(e.getMessage());
		} finally {
			if (ouputStream != null) {
				ouputStream.flush();   
	            ouputStream.close();
			}
		}
		
		return serviceResponse;
	}

	//以下为兼容老流程代码，员工账号统一1.2
	@Override
	public ServiceResponse directManagementLargeAreaDirectorApprove(ApproveRequest request) throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request.getTaskId(), SpendAfterReimburseConstans.TASK_EXCEED_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVED_LIMIT);
		processHandler.completeTask(request, variables, SpendAfterReimburseConstans.TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse logisticsChiefManagerApprove(ApproveRequest request)	throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request.getTaskId(), SpendAfterReimburseConstans.TASK_EXCEED_LOGISTICS_CHIEF_MANAGER_APPROVED_LIMIT);
		processHandler.completeTask(request, variables, SpendAfterReimburseConstans.TASK_LOGISTICS_CHIEF_MANAGER_APPROVED);
		return new ServiceResponse();
	}

}
