package com.i360r.bpm.service.rs.process.housing.relet;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.exception.EntityNotExistException;
import com.i360r.bpm.business.exception.RemoteServerException;
import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.housing.contract.api.IHousingContractHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.housing.relet.api.HouseReletApplyRequest;
import com.i360r.bpm.service.rs.process.housing.relet.api.HouseReletApplyUploadVoucherRequest;
import com.i360r.bpm.service.rs.process.housing.relet.api.HousingContractCashierConfirmRequest;
import com.i360r.bpm.service.rs.process.housing.relet.api.HousingContractModifyRequest;
import com.i360r.bpm.service.rs.process.housing.relet.api.HousingContractReletConstants;
import com.i360r.bpm.service.rs.process.housing.relet.api.HousingContractReletVO;
import com.i360r.bpm.service.rs.process.housing.relet.api.IHouseReletService;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.i18n.MessageSourceManager;
import com.i360r.framework.service.api.exception.IllegalRequestException;

public class HouseReletService implements IHouseReletService {

	@Autowired
	private IEmployeeHandler employeeHandler;

	@Autowired
	private IProcessHandler processHandler;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	protected ManagementService managementService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private IHousingContractHandler housingContractHandler;

	@Override
	public ProcessDetailResponse<HousingContractReletVO> getDetail(
			String processInstanceId) {
		return processHandler.getProcessDetail(processInstanceId, HousingContractReletVO.class);
	}

	@Override
	public ServiceResponse startProcess(HouseReletApplyRequest houseReletApplyRequest) throws Exception {
		if ((houseReletApplyRequest.getTotalAmount().compareTo(new BigDecimal(0)) == 0)
				|| (houseReletApplyRequest.getTotalAmount().compareTo(ProcessConstants.APPROVE_TOP_LIMIT) > 0)) {
			throw new IllegalRequestException("total amount equals to ZERO or too big!");
		}
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		Map<String, Object> customVariables = new HashMap<String, Object>();

		String mobile = employeeHandler.getEmployeeMobileById(employee.getCode());
		
		customVariables.put(HousingContractReletConstants.TASK_EMPLOYEE_POSITION, employee.getPositionName());
		customVariables.put(HousingContractReletConstants.TASK_EMPLOYEE_MOBILE, mobile);
		customVariables.put(HousingContractReletConstants.TASK_RENT_PAYMENT_TYPE, "押" + String.valueOf(houseReletApplyRequest.getRentDepositNumber())
																						+ "付" + String.valueOf(houseReletApplyRequest.getRentPaymentNumber()));

		if (StringUtils.isBlank(houseReletApplyRequest.getBusinessAreaCode())) {
			customVariables.put(ProcessConstants.KEY_LOCATION_CODE, houseReletApplyRequest.getCityCode());
		} else {
			customVariables.put(ProcessConstants.KEY_LOCATION_CODE, houseReletApplyRequest.getBusinessAreaCode());
		}
		
		processHandler.startProcess(employee, houseReletApplyRequest, HousingContractReletConstants.PROCESS_DEFINITION_KEY, customVariables, AccountType.EMPLOYEE);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse cityCommissarApprove(ApproveRequest request)
			throws Exception {
		processHandler.completeTask(request, HousingContractReletConstants.TASK_CITY_COMMISSAR_APPROVED);
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse directManagementCityManagerApprove(
			ApproveRequest request) throws Exception {
		processHandler.completeTask(request, HousingContractReletConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse operationManagementViceDirectorApprove(
			ApproveRequest request) throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request.getTaskId(), HousingContractReletConstants.TASK_EXCEED_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVED_LIMIT);
		processHandler.completeTask(request, variables, HousingContractReletConstants.TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse operationManagementDirectorApprove(ApproveRequest request)
			throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request.getTaskId(), HousingContractReletConstants.TASK_EXCEED_OPERATION_MANAGEMENT_DIRECTOR_APPROVED_LIMIT);
		processHandler.completeTask(request, variables, HousingContractReletConstants.TASK_OPERATION_MANAGEMENT_DIRECTOR_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse COOApprove(ApproveRequest request) throws Exception {
		Map<String, Object> variables = exceedApproveLimit(request.getTaskId(), HousingContractReletConstants.TASK_EXCEED_COO_APPROVED_LIMIT);
		processHandler.completeTask(request, variables, HousingContractReletConstants.TASK_COO_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse CEOApprove(ApproveRequest request) throws Exception {
		processHandler.completeTask(request, HousingContractReletConstants.TASK_CEO_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse accountantApprove(ApproveRequest request)
			throws Exception {
		processHandler.completeTask(request, HousingContractReletConstants.TASK_ACCOUNTANT_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse cashierConfirm(HousingContractCashierConfirmRequest request)
			throws Exception {
		if(request.getApproved()
				&& StringUtils.isBlank(request.getActualPaymentDate())) {
			throw new RemoteServerException(MessageSourceManager.getMessage("msg.housing.contract"));
		}
		Task task = taskService.createTaskQuery().taskId(request.getTaskId()).singleResult();
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(ProcessConstants.KEY_PAID_BY_CODE, task.getAssignee());
		variables.put(ProcessConstants.KEY_PAY_TIME, task.getCreateTime());
		processHandler.completeTask(request, variables, HousingContractReletConstants.TASK_CASHIER_CONFIRMED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse accountantConfirm(ApproveRequest request) throws Exception {
		processHandler.completeTask(request, HousingContractReletConstants.TASK_ACCOUNTANT_CONFIRMED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse houseRentConfirm(ApproveRequest request) throws Exception {
		Map<String, Object> variables = new HashMap<String, Object>();
		if (request.getApproved()){
			Task task = taskService.createTaskQuery().taskId(request.getTaskId()).singleResult();
			if (task == null) {
				throw new EntityNotExistException("taskId=" + request.getTaskId(), "任务");
			}
			Integer reimburseId = housingContractHandler.createHousingRentReimburse(task.getExecutionId());
			variables.put(ProcessConstants.KEY_REIMBURSE_ID, reimburseId);
		}

		processHandler.completeTask(request, variables, HousingContractReletConstants.TASK_HOUSE_RENT_CONFIRMED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse uploadVoucher(HouseReletApplyUploadVoucherRequest request) throws Exception {

		processHandler.completeTask(request);

		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse cashierConfirmGathering(ApproveRequest request) throws Exception {
		processHandler.completeTask(request, HousingContractReletConstants.TASK_CASHIER_CONFIRMED_GATHERING);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse applicantModifyHousingContract(
			HousingContractModifyRequest request) throws Exception {
		if ((request.getTotalAmount().compareTo(new BigDecimal(0)) == 0)
				|| (request.getTotalAmount().compareTo(ProcessConstants.APPROVE_TOP_LIMIT) > 0)) {
			throw new IllegalRequestException();
		}
		
		processHandler.completeTask(request, HousingContractReletConstants.TASK_APPROVE_REAPPLY);
		return new ServiceResponse();
	}

	private Map<String, Object> exceedApproveLimit(String taskId, String keyword) {
		HousingContractReletVO detail = processHandler.getClassDetail(taskId, HousingContractReletVO.class);
		BigDecimal totalAmount = detail.getTotalAmount();

		Boolean exceedApproveLimit = processHandler.exceedEmployeeApproveLimit(totalAmount, taskId);
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(keyword, exceedApproveLimit);
		return variables;
	}

}
