package com.i360r.bpm.service.rs.process.deliverystaffcancelleave;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.deliverystaff.DeliveryStaffHandler;
import com.i360r.bpm.business.handler.deliverystaff.api.DeliveryStaffVO;
import com.i360r.bpm.business.handler.employee.EmployeeHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.business.model.PositionCode;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.deliverystaffcancelleave.api.DeliveryStaffCancelLeaveDetailVO;
import com.i360r.bpm.service.rs.process.deliverystaffcancelleave.api.DeliveryStaffCancelLeaveRequest;
import com.i360r.bpm.service.rs.process.deliverystaffcancelleave.api.DeliveryStaffCancelLeaveVO;
import com.i360r.bpm.service.rs.process.deliverystaffcancelleave.api.GetCancelLeaveListRequest;
import com.i360r.bpm.service.rs.process.deliverystaffcancelleave.api.GetCancelLeaveListResponse;
import com.i360r.bpm.service.rs.process.deliverystaffcancelleave.api.IDeliveryStaffCancelLeaveService;
import com.i360r.framework.common.model.PagingResult;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.service.api.exception.IllegalRequestException;

public class DeliveryStaffCancelLeaveService implements IDeliveryStaffCancelLeaveService {

	@Autowired
	private EmployeeHandler employeeHandler;
	
	@Autowired
	private DeliveryStaffHandler deliveryStaffHandler;
	
	@Autowired
	private IProcessHandler processHandler;
	
	@Autowired
	private HistoryService historyService;
	
	@Override
	public ProcessDetailResponse<DeliveryStaffCancelLeaveDetailVO> getDetail(String processInstanceId) {
		ProcessDetailResponse<DeliveryStaffCancelLeaveDetailVO>	response = processHandler.getProcessDetail(processInstanceId, DeliveryStaffCancelLeaveDetailVO.class);
		if (response.getDetail() != null) {
			response.getDetail().initOtherProperties();
		}
		return response;
				
	}
	
	@Override
	public GetCancelLeaveListResponse getCancelLeaveList(GetCancelLeaveListRequest request) {
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		
		HistoricProcessInstanceQuery query = historyService
				.createHistoricProcessInstanceQuery().processDefinitionKey(
						DeliveryStaffCancelLeaveConstants.PROCESS_DEFINITION_KEY).startedBy(employee.getEmployeePositionCode());
		
		query = query.orderByProcessInstanceStartTime().desc();
		
		List<HistoricProcessInstance> hpis = query.listPage(
				(request.getPageNumber() - 1) * request.getPageSize(), request.getPageSize());
		
		long count = query.count();
		
		GetCancelLeaveListResponse response = new GetCancelLeaveListResponse();
		
		PagingResult result = new PagingResult();
		result.setPageNumber(request.getPageNumber());
		result.setPageSize(request.getPageSize());
		result.setRecordNumber((int) count);
		
		response.setPagingResult(result);

		List<DeliveryStaffCancelLeaveVO> vos = new ArrayList<DeliveryStaffCancelLeaveVO>();
		for (HistoricProcessInstance hpi : hpis) {
			
			List<HistoricVariableInstance> variables = historyService
					.createHistoricVariableInstanceQuery().processInstanceId(hpi.getId()).list();
			
			Map<String, Object> variableMap = ProcessUtility
					.getVariableMap(variables).get(ProcessConstants.KEY_NON_TASK);
			
			vos.add(DeliveryStaffCancelLeaveVO.toVO(hpi, variableMap));
		}
		
		response.setProcessList(vos);
		
		return response;
	}
	
	@Override
	public ServiceResponse startProcess(DeliveryStaffCancelLeaveRequest request) throws Exception {
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		
		DeliveryStaffVO deliveryStaffVO = deliveryStaffHandler.getDeliveryStaffByCode(employee.getDeliveryStaffCode());
		
		String stationManagerCode = employeeHandler.getStationManagerCode(deliveryStaffVO.getBusinessAreaCode());
		if (StringUtils.isBlank(stationManagerCode)) {
			throw new IllegalRequestException();
		}
		
		deliveryStaffHandler.checkCancelLeave(employee.getEmployeePositionCode(), request.getDeliveryStaffLeaveId(), request.getCancelLeaves());
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(DeliveryStaffCancelLeaveConstants.KEY_LEAVE_TYPE_CODE, request.getLeaveTypeCode());
		variables.put(DeliveryStaffCancelLeaveConstants.KEY_STATION_MANAGER_CODE, stationManagerCode);

		request.setBusinessAreaName(deliveryStaffVO.getBusinessAreaName());
		
		variables.put(DeliveryStaffCancelLeaveConstants.KEY_DELIVERY_STAFF_LEAVE_ID, request.getDeliveryStaffLeaveId());
		variables.put(DeliveryStaffCancelLeaveConstants.KEY_DELIVERY_STAFF_NAME, request.getDeliveryStaffName());
		variables.put(DeliveryStaffCancelLeaveConstants.KEY_CANCEL_LEAVES, request.getCancelLeaves());
		variables.put(DeliveryStaffCancelLeaveConstants.KEY_IS_STATION_MANAGER, PositionCode.DELIVERY_STATION_MANAGER.getCode().equals(deliveryStaffVO.getPositonCode()));

		processHandler.startProcess(employee, request, DeliveryStaffCancelLeaveConstants.PROCESS_DEFINITION_KEY, variables, AccountType.DELIVERY_STAFF);
		
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse stationManagerApprove(ApproveRequest request) {
		
		processHandler.completeTask(request, DeliveryStaffCancelLeaveConstants.KEY_STATION_MANAGER_APPROVED);
		
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse directManagementAreaManagerApprove(
			ApproveRequest request) {
		processHandler.completeTask(request, DeliveryStaffCancelLeaveConstants.KEY_DIRECT_MANAGEMENT_AREA_MANAGER_APPROVED);
		
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse directManagementCityManagerApprove(
			ApproveRequest request) {
		processHandler.completeTask(request, DeliveryStaffCancelLeaveConstants.KEY_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED);
		
		return new ServiceResponse();
	}

	
}
