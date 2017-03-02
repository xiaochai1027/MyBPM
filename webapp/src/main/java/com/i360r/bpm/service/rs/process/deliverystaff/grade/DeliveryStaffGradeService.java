package com.i360r.bpm.service.rs.process.deliverystaff.grade;

import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.exception.PositionNotSupportException;
import com.i360r.bpm.business.exception.ProcessApproveErrorException;
import com.i360r.bpm.business.handler.deliverystaff.api.DeliveryStaffVO;
import com.i360r.bpm.business.handler.deliverystaff.api.IDeliveryStaffHandler;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.business.model.DeliveryStaffGradeType;
import com.i360r.bpm.business.model.PositionCode;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.business.util.ValidateUtility;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.deliverystaff.grade.api.CreateDeliveryStaffGradeRequest;
import com.i360r.bpm.service.rs.process.deliverystaff.grade.api.DeliveryStaffGradeDetailVO;
import com.i360r.bpm.service.rs.process.deliverystaff.grade.api.DeliveryStaffGradeProcessRequest;
import com.i360r.bpm.service.rs.process.deliverystaff.grade.api.IDeliveryStaffGradeService;
import com.i360r.bpm.service.rs.process.deliverystaffentry.DeliveryStaffEntryConstants;
import com.i360r.bpm.service.rs.process.util.FlowUtils;
import com.i360r.cds.api.internal.rs.employee.position.EmployeePositionStatusSO;
import com.i360r.cds.api.internal.rs.employee.position.PositionCodeSO;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.i18n.MessageSourceManager;

public class DeliveryStaffGradeService implements IDeliveryStaffGradeService {

	@Autowired
	private IProcessHandler processHandler;
	
	@Autowired
	private IDeliveryStaffHandler deliveryStaffHandler;
	
	@Autowired
	private TaskService taskService;

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private HistoryService historyService;
	
	@Override
	public ProcessDetailResponse<DeliveryStaffGradeDetailVO> getDetail(String processInstanceId) {
		
		ProcessDetailResponse<DeliveryStaffGradeDetailVO> response = processHandler.getProcessDetail(processInstanceId, DeliveryStaffGradeDetailVO.class);
		
		return response;
	}

	@Override
	public ServiceResponse startProcess(CreateDeliveryStaffGradeRequest request) throws Exception {

		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		DeliveryStaffVO deliveryStaff = deliveryStaffHandler.getDeliveryStaffByCode(request.getDeliveryStaffId());
		
		// 只有全职配送员支持调级
		if (!deliveryStaff.getPositonCode().equals(PositionCodeSO.DIRECT_MANAGEMENT_FULL_TIME_DELIVERY_STAFF.getCode())) {
			throw new PositionNotSupportException("change grade error. cause of unsupport position. delivery staff id is " + request.getDeliveryStaffId(), 
					PositionCode.fromCode(deliveryStaff.getPositonCode()).getDescription(), "调级");
		}
		
		String deliveryStaffName = deliveryStaffHandler.getDeliveryStaffByCode(request.getDeliveryStaffId()).getFullName();
		FlowUtils.checkdeliveryStaffFlow(runtimeService, request.getDeliveryStaffId(), deliveryStaffName);
		
		DeliveryStaffGradeProcessRequest processRequest = new DeliveryStaffGradeProcessRequest(request);
		processRequest.setBusinessAreaCode(deliveryStaff.getBusinessAreaCode());
		processRequest.setBusinessAreaName(deliveryStaff.getBusinessAreaName());
		processRequest.setCityName(deliveryStaff.getCityName());
		processRequest.setDeliveryStaffGradeTypeName(DeliveryStaffGradeType.fromCode(request.getDeliveryStaffGradeTypeCode()).getName());
		processRequest.setOriginDeliveryStaffGradeTypeName(DeliveryStaffGradeType.fromCode(request.getOriginDeliveryStaffGradeTypeCode()).getName());
		processRequest.setFullName(deliveryStaff.getFullName());
		processRequest.setIdentityCard(deliveryStaff.getIdentityCardNumber());
		processRequest.setMobile(deliveryStaff.getMobile());
		
		//检查是否存在流程已通过，但未生效的转岗或者离职流程或者调级
		ValidateUtility.validateHistoricDeliveryStaffEntryProcess(historyService, request.getDeliveryStaffId());
		ValidateUtility.validateHistoricDeliveryStaffDismissionProcess(historyService, request.getDeliveryStaffId());
		ValidateUtility.validateHistoricDeliveryStaffGradeProcess(historyService, request.getDeliveryStaffId());
		
		processHandler.startProcess(employee, processRequest, DeliveryStaffGradeConstants.PROCESS_DEFINITION_KEY, null, AccountType.EMPLOYEE);
		
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse directManagementAreaManagerApprove(ApproveRequest request) throws Exception {
		
		onCheck(request, DeliveryStaffGradeConstants.KEY_DIRECT_MANAGEMENT_AREA_MANAGER_APPROVED);
		
		processHandler.completeTask(request, DeliveryStaffGradeConstants.KEY_DIRECT_MANAGEMENT_AREA_MANAGER_APPROVED);
		
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse directManagementCityManagerApprove(ApproveRequest request) throws Exception {
		
		onCheck(request, DeliveryStaffEntryConstants.KEY_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED);
		
		processHandler.completeTask(request, DeliveryStaffEntryConstants.KEY_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED);
		
		return new ServiceResponse();
	}
	
	private void onCheck(ApproveRequest request, String processResultKey) throws Exception {
		Task task = taskService.createTaskQuery().taskId(request.getTaskId()).singleResult();
		Map<String, Object> variables = runtimeService.getVariables(task.getExecutionId());
		DeliveryStaffGradeDetailVO detail = ProcessUtility.getProcessObject(variables, DeliveryStaffGradeDetailVO.class);
		DeliveryStaffVO deliveryStaff = deliveryStaffHandler.getDeliveryStaffByCode(detail.getDeliveryStaffId());
		
		// 如果级别是特级或者A级，而该操作是区域经理审批，则不需要进行等级重复的判断
		if (DeliveryStaffEntryConstants.KEY_DIRECT_MANAGEMENT_AREA_MANAGER_APPROVED.equals(processResultKey) 
				&& (DeliveryStaffGradeType.SUPER.getCode().equals(detail.getDeliveryStaffGradeTypeCode()) || DeliveryStaffGradeType.A.getCode().equals(detail.getDeliveryStaffGradeTypeCode()))) {
			return;
		}
		
		// 离职判断
		if (deliveryStaff.getStatusCode().equals(EmployeePositionStatusSO.OUT_OF_POSITION.getCode())) {
			String messageKey = request.getApproved() ? "process.deliverystaff.judge.dimission.pass" : "process.deliverystaff.judge.dimission.notpass";
			String dialogMsg = MessageSourceManager.getMessage(messageKey);
			
			if (request.getApproved()) {
				request.setApproved(false);
			}
			
			processHandler.completeTask(request, processResultKey);
			
			String errorMsg = String.format("Delivery staff %s current dimission", detail.getDeliveryStaffId());
			throw new ProcessApproveErrorException(errorMsg, dialogMsg);
		}
	}

}
