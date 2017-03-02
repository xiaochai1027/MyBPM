package com.i360r.bpm.service.rs.process.deliverystaffdimission;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.exception.DimissionExistException;
import com.i360r.bpm.business.exception.ProcessApproveErrorException;
import com.i360r.bpm.business.handler.deliverystaff.api.DeliveryStaffVO;
import com.i360r.bpm.business.handler.deliverystaff.api.IDeliveryStaffHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.business.util.DateTimeUtility;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.business.util.ValidateUtility;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.deliverystaffdimission.api.DeliveryStaffDimissionDetailVO;
import com.i360r.bpm.service.rs.process.deliverystaffdimission.api.DeliveryStaffDimissionRequest;
import com.i360r.bpm.service.rs.process.deliverystaffdimission.api.IDeliveryStaffDimissionService;
import com.i360r.bpm.service.rs.process.util.FlowUtils;
import com.i360r.cds.api.internal.rs.deliverystaff.DeliveryStaffSO;
import com.i360r.cds.api.internal.rs.employee.EmployeeSO;
import com.i360r.cds.api.internal.rs.employee.EmployeeStatusSO;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.i18n.MessageSourceManager;
import com.i360r.framework.service.api.exception.IllegalRequestException;

public class DeliveryStaffDimissionService implements IDeliveryStaffDimissionService {

	@Autowired
	private IDeliveryStaffHandler deliveryStaffHandler;
	
	@Autowired
	private IProcessHandler processHandler;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Override
	public ProcessDetailResponse<DeliveryStaffDimissionDetailVO> getDetail(String processInstanceId) {
		ProcessDetailResponse<DeliveryStaffDimissionDetailVO> response = processHandler.getProcessDetail(processInstanceId, DeliveryStaffDimissionDetailVO.class);
		//兼容老流程
		if (response.getDetail() != null 
				&& (StringUtils.isBlank(response.getDetail().getDimissionReasonName()) || StringUtils.isBlank(response.getDetail().getDimissionTypeName()))) {
			response.getDetail().initOtherProperties();
		}
		return response;
	}
	
	@Override
	public ServiceResponse startProcess(DeliveryStaffDimissionRequest request) throws Exception {
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		
		DeliveryStaffVO deliveryStaff = deliveryStaffHandler.getDeliveryStaffByCode(request.getDeliveryStaffCode());
		if (deliveryStaff == null 
				|| StringUtils.isBlank(deliveryStaff.getBusinessAreaCode())) {
			throw new IllegalRequestException();
		}
		if (DateTimeUtility.parseYYYYMMDD(deliveryStaff.getEmployeePositionEntryDate()).after(DateTimeUtility.parseYYYYMMDD(request.getDimissionDate()))) {
			throw new DimissionExistException();
		}

		String deliveryStaffName = deliveryStaffHandler.getDeliveryStaffByCode(request.getDeliveryStaffCode()).getFullName();
		FlowUtils.checkdeliveryStaffFlow(runtimeService, request.getDeliveryStaffCode(), deliveryStaffName);
		
		//检查是否存在流程已通过，但未生效的转岗或者离职流程或者调级
		ValidateUtility.validateHistoricDeliveryStaffEntryProcess(historyService, request.getDeliveryStaffCode());
		ValidateUtility.validateHistoricDeliveryStaffDismissionProcess(historyService, request.getDeliveryStaffCode());
		ValidateUtility.validateHistoricDeliveryStaffGradeProcess(historyService, request.getDeliveryStaffCode());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(ProcessConstants.KEY_BUSINESS_AREA_CODE, deliveryStaff.getBusinessAreaCode());
		variables.put(ProcessConstants.KEY_BUSINESS_AREA_NAME, deliveryStaff.getBusinessAreaName());
		variables.put(DeliveryStaffDimissionConstants.KEY_ENTRY_DATE, deliveryStaff.getEmployeeEntryDate());
		
		processHandler.startProcess(employee, request, DeliveryStaffDimissionConstants.PROCESS_DEFINITION_KEY, variables, AccountType.EMPLOYEE);
		
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse directManagerApprove(ApproveRequest request) {
		
		processHandler.completeTask(request, DeliveryStaffDimissionConstants.KEY_DIRECT_MANAGER_APPROVED);
		
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse indirectManagerApprove(ApproveRequest request) {
		
		processHandler.completeTask(request, DeliveryStaffDimissionConstants.KEY_INDIRECT_MANAGER_APPROVED);
		
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse directManagementAreaManagerApprove(ApproveRequest request) {
		processHandler.completeTask(request, DeliveryStaffDimissionConstants.KEY_DIRECT_MANAGEMENT_AREA_MANAGER_APPROVED);
		
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse directManagementCityManagerApprove(ApproveRequest request) {
		processHandler.completeTask(request, DeliveryStaffDimissionConstants.KEY_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED);
		
		return new ServiceResponse();
		
	}
	
	@Override
	public ServiceResponse humanResourceApprove(ApproveRequest request) throws Exception {
		onCheck(request, DeliveryStaffDimissionConstants.KEY_HUMAN_RESOURCE_APPROVED);
		
		processHandler.completeTask(request, DeliveryStaffDimissionConstants.KEY_HUMAN_RESOURCE_APPROVED);
		
		return new ServiceResponse();
	}

	// 保留之前的物流总监的入口，是历史流程可以走下去
	@Override
	public ServiceResponse directManagementLogisticsDirectorApprove(
			ApproveRequest request) {
		
		processHandler.completeTask(request, DeliveryStaffDimissionConstants.KEY_DIRECT_MANAGEMENT_LOGISTICS_DIRECTOR_APPROVED);
		
		return new ServiceResponse();
	}
	
	private void onCheck(ApproveRequest request, String processResultKey) throws Exception {
		Task task = taskService.createTaskQuery().taskId(request.getTaskId()).singleResult();
		Map<String, Object> variables = runtimeService.getVariables(task.getExecutionId());
		DeliveryStaffDimissionDetailVO detail = ProcessUtility.getProcessObject(variables, DeliveryStaffDimissionDetailVO.class);
		
		DeliveryStaffSO deliveryStaff = deliveryStaffHandler.getDeliveryStaffById(detail.getDeliveryStaffCode());
		EmployeeSO employee = deliveryStaff.getEmployeePosition().getEmployee();
		
		// 员工已经离职
		if (EmployeeStatusSO.OUT_OF_POSITION.getCode().equals(employee.getStatusCode())) {
			String messageKey = request.getApproved() ? "process.deliverystaff.judge.dimission.pass" : "process.deliverystaff.judge.dimission.notpass";
			String dialogMsg = MessageSourceManager.getMessage(messageKey);
			
			if (request.getApproved()) {
				request.setApproved(false);
			}
			
			processHandler.completeTask(request, processResultKey);
			
			String errorMsg = String.format("Delivery staff %s current dimission", detail.getDeliveryStaffCode());
			throw new ProcessApproveErrorException(errorMsg, dialogMsg);

		}
	}
}
