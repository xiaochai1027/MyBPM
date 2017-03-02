package com.i360r.bpm.service.rs.process.housingfeereimburse;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.i360r.bpm.business.exception.RemoteServerException;
import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.business.util.ValidateUtility;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.housingfeereimburse.api.HousingFeeReimburseCashierConfirmRequest;
import com.i360r.bpm.service.rs.process.housingfeereimburse.api.HousingFeeReimburseCreateRequest;
import com.i360r.bpm.service.rs.process.housingfeereimburse.api.HousingFeeReimburseDetailVO;
import com.i360r.bpm.service.rs.process.housingfeereimburse.api.HousingFeeReimburseItemVO;
import com.i360r.bpm.service.rs.process.housingfeereimburse.api.HousingFeeReimburseModifyRequest;
import com.i360r.bpm.service.rs.process.housingfeereimburse.api.HousingFeeReimburseRequest;
import com.i360r.bpm.service.rs.process.housingfeereimburse.api.IHousingFeeReimburseService;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.i18n.MessageSourceManager;

public class HousingFeeReimburseService implements IHousingFeeReimburseService {
	
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
	public ProcessDetailResponse<HousingFeeReimburseDetailVO> getDetail(
			String processInstanceId) {
		
		return processHandler.getProcessDetail(processInstanceId, HousingFeeReimburseDetailVO.class);
	}

	@Override
	public ServiceResponse startProcess(HousingFeeReimburseCreateRequest request) throws Exception {
		
		ValidateUtility.validateHousingFeeReimburseCount(
				HousingFeeReimburseConstants.PROCESS_DEFINITION_KEY,
				request.getBusinessAreaCode(), historyService, runtimeService);

		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		Map<String, Object> customVariables = new HashMap<String, Object>();

		String mobile = employeeHandler.getEmployeeMobileById(employee.getCode());
		
		// 用于详情中显示申请人岗位和手机号
		customVariables.put(HousingFeeReimburseConstants.TASK_EMPLOYEE_POSITION, employee.getPositionName());
		customVariables.put(HousingFeeReimburseConstants.TASK_EMPLOYEE_MOBILE, mobile);
		
		if (StringUtils.isBlank(request.getBusinessAreaCode())) {
			customVariables.put(ProcessConstants.KEY_LOCATION_CODE, request.getCityCode());
		}
		
		processHandler.startProcess(employee, request, HousingFeeReimburseConstants.PROCESS_DEFINITION_KEY, customVariables, AccountType.EMPLOYEE);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse directManagementCityManagerApprove(
			HousingFeeReimburseRequest request) throws Exception {
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, HousingFeeReimburseConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED);
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse operationManagementViceDirectorApprove(
			HousingFeeReimburseRequest request) throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request, HousingFeeReimburseConstants.TASK_EXCEED_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVED_LIMIT);		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, HousingFeeReimburseConstants.TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse operationManagementDirectorApprove(HousingFeeReimburseRequest request)
			throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request, HousingFeeReimburseConstants.TASK_EXCEED_OPERATION_MANAGEMENT_DIRECTOR_APPROVED_LIMIT);
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, HousingFeeReimburseConstants.TASK_OPERATION_MANAGEMENT_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}
	


	@Override
	public ServiceResponse COOApprove(HousingFeeReimburseRequest request) throws Exception {
		
		Map<String, Object> variables = exceedApproveLimit(request, HousingFeeReimburseConstants.TASK_EXCEED_COO_APPROVED_LIMIT);
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, HousingFeeReimburseConstants.TASK_COO_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse CEOApprove(HousingFeeReimburseRequest request) throws Exception {
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, HousingFeeReimburseConstants.TASK_CEO_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse applicantModifyReimburse(
			HousingFeeReimburseModifyRequest request) throws Exception {
		
		processHandler.completeTask(request, HousingFeeReimburseConstants.TASK_APPROVE_REAPPLY);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse cashierConfirm(
			HousingFeeReimburseCashierConfirmRequest request) throws Exception {
		
		if(request.getApproved()
				&& StringUtils.isBlank(request.getActualPaymentDate())) {
			throw new RemoteServerException(MessageSourceManager.getMessage("msg.deposit.apply"));
		}
		Task task = taskService.createTaskQuery().taskId(request.getTaskId()).singleResult();
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(ProcessConstants.KEY_PAID_BY_CODE, task.getAssignee());
		variables.put(ProcessConstants.KEY_PAY_TIME, task.getCreateTime());
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, HousingFeeReimburseConstants.TASK_CASHIER_CONFIRMED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse accountantApprove(HousingFeeReimburseRequest request)
			throws Exception {
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, HousingFeeReimburseConstants.TASK_ACCOUNTANT_APPROVED);
		return new ServiceResponse();
	}

	
	private Map<String, Object> exceedApproveLimit(HousingFeeReimburseRequest request, String keyword) {
		BigDecimal totalAmount = request.getTotalAmount();
		Boolean exceedApproveLimit = processHandler.exceedEmployeeApproveLimit(totalAmount, request.getTaskId());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(keyword, exceedApproveLimit);
		return variables;
	}
	
	private Map<String, Object> completeTaskBefore(Map<String, Object> variables, HousingFeeReimburseRequest request, EmployeeCO employee, List<HousingFeeReimburseItemVO> rejectItems, Boolean approved) {
		HousingFeeReimburseDetailVO detail = processHandler.getClassDetail(request.getTaskId(), HousingFeeReimburseDetailVO.class);
		List<HousingFeeReimburseItemVO> items = detail.getItems();
		if (!approved) {
			request.setRejectItemsRemind("");
			BigDecimal totalAmount = BigDecimal.ZERO;
			//流程未通过，把items里的rejected变为false
			for (HousingFeeReimburseItemVO vo : items) {
				vo.setRejected(false);
				vo.setRejectReason(null);
				vo.setRejectPersonName(null);
				totalAmount = totalAmount.add(vo.getAmount());
			}
			request.setTotalAmount(totalAmount.setScale(2, BigDecimal.ROUND_DOWN));
		} else {
			//流程通过了，需要查看有没有rejectItems	
			for (HousingFeeReimburseItemVO housingFeeReimburseItemVO : items) {
				for (HousingFeeReimburseItemVO vo : rejectItems) {
					if (vo.getItemId().equals(housingFeeReimburseItemVO.getItemId())) {
						housingFeeReimburseItemVO.setRejected(true);
						housingFeeReimburseItemVO.setRejectPersonName(employee.getName());
						housingFeeReimburseItemVO.setRejectReason(vo.getRejectReason());
					}
				}
			}
		}
		variables.put(HousingFeeReimburseConstants.TASK_ITEMS, items);
		//有驳回项修改关键字
		if (approved && !CollectionUtils.isEmpty(rejectItems)) {
			request.setRejectItemsRemind("<span style='color:red'>有驳回项目</span>");
		}
		return variables;
	}
	
	//以下为兼容老流程，员工账号统一1.2
	@Override
	public ServiceResponse directManagementLargeAreaDirectorApprove(
			HousingFeeReimburseRequest request) throws Exception {
		
		Map<String, Object> variables = exceedApproveLimit(request, HousingFeeReimburseConstants.TASK_EXCEED_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVED_LIMIT);
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, HousingFeeReimburseConstants.TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse logisticsChiefManagerApprove(HousingFeeReimburseRequest request)
			throws Exception {
		
		Map<String, Object> variables = exceedApproveLimit(request, HousingFeeReimburseConstants.TASK_EXCEED_LOGISTICS_CHIEF_MANAGER_APPROVED_LIMIT);
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		variables = completeTaskBefore(variables, request, employee, request.getRejectItems(), request.getApproved());
		processHandler.completeTask(request, variables, HousingFeeReimburseConstants.TASK_LOGISTICS_CHIEF_MANAGER_APPROVED);
		return new ServiceResponse();
	}
}
