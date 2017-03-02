package com.i360r.bpm.service.rs.process.deliverystaffleave;

import java.math.BigDecimal;
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

import com.i360r.bpm.business.exception.InvalidDeliveryStaffException;
import com.i360r.bpm.business.exception.RemoteServerException;
import com.i360r.bpm.business.handler.deliverystaff.DeliveryStaffHandler;
import com.i360r.bpm.business.handler.deliverystaff.api.DeliveryStaffVO;
import com.i360r.bpm.business.handler.employee.EmployeeHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.business.model.DateAMPM;
import com.i360r.bpm.business.model.EmployeeStatus;
import com.i360r.bpm.business.model.PositionCode;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.deliverystaffleave.api.DeliveryStaffLeaveDetailVO;
import com.i360r.bpm.service.rs.process.deliverystaffleave.api.DeliveryStaffLeaveRequest;
import com.i360r.bpm.service.rs.process.deliverystaffleave.api.DeliveryStaffLeaveVO;
import com.i360r.bpm.service.rs.process.deliverystaffleave.api.GetLeaveListRequest;
import com.i360r.bpm.service.rs.process.deliverystaffleave.api.GetLeaveListResponse;
import com.i360r.bpm.service.rs.process.deliverystaffleave.api.IDeliveryStaffLeaveService;
import com.i360r.framework.common.model.PagingResult;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.util.DateTimeUtility;

public class DeliveryStaffLeaveService implements IDeliveryStaffLeaveService {

	@Autowired
	private EmployeeHandler employeeHandler;
	
	@Autowired
	private DeliveryStaffHandler deliveryStaffHandler;
	
	@Autowired
	private IProcessHandler processHandler;
	
	@Autowired
	private HistoryService historyService;
	
	@Override
	public ProcessDetailResponse<DeliveryStaffLeaveDetailVO> getDetail(String processInstanceId) {
		ProcessDetailResponse<DeliveryStaffLeaveDetailVO> response = processHandler.getProcessDetail(processInstanceId, DeliveryStaffLeaveDetailVO.class);
		if (response.getDetail() != null) {
			response.getDetail().initOtherProperties();
		}
		return response;
	}
	
	@Override
	public GetLeaveListResponse getLeaveList(GetLeaveListRequest request) {
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		
		HistoricProcessInstanceQuery query = historyService
				.createHistoricProcessInstanceQuery().processDefinitionKey(
						DeliveryStaffLeaveConstants.PROCESS_DEFINITION_KEY).startedBy(employee.getEmployeePositionCode());
		
		query = query.orderByProcessInstanceStartTime().desc();
		
		List<HistoricProcessInstance> hpis = query.listPage(
				(request.getPageNumber() - 1) * request.getPageSize(), request.getPageSize());
		
		long count = query.count();
		
		GetLeaveListResponse response = new GetLeaveListResponse();
		
		PagingResult result = new PagingResult();
		result.setPageNumber(request.getPageNumber());
		result.setPageSize(request.getPageSize());
		result.setRecordNumber((int) count);
		
		response.setPagingResult(result);
		
		List<DeliveryStaffLeaveVO> vos = new ArrayList<DeliveryStaffLeaveVO>();
		for (HistoricProcessInstance hpi : hpis) {
			
			List<HistoricVariableInstance> variables = historyService
					.createHistoricVariableInstanceQuery().processInstanceId(hpi.getId()).list();
			
			Map<String, Object> variableMap = ProcessUtility
					.getVariableMap(variables).get(ProcessConstants.KEY_NON_TASK);
			DeliveryStaffLeaveVO vo = DeliveryStaffLeaveVO.toVO(hpi, variableMap);
			if (request.getProcessStatusCode() == null || vo.getStatusCode().equals(request.getProcessStatusCode())) {
				vos.add(vo);
			}
		}
		
		response.setProcessList(vos);
		
		return response;
	}
	
	@Override
	public ServiceResponse startProcess(DeliveryStaffLeaveRequest request) throws Exception {
		
		EmployeeCO account = SessionContextAccessor.getCurrentAccount();
		DeliveryStaffVO deliveryStaffVO = deliveryStaffHandler.getDeliveryStaffByCode(account.getDeliveryStaffCode());
		if (deliveryStaffVO == null
				|| EmployeeStatus.OUT_OF_POSITION.getCode().equals(deliveryStaffVO.getStatusCode())) {
			throw new InvalidDeliveryStaffException();
		}
		
		String stationManagerCode = employeeHandler.getStationManagerCode(deliveryStaffVO.getBusinessAreaCode());
		if (StringUtils.isBlank(stationManagerCode)) {
			throw new RemoteServerException("上级不存在");
		}
		
		DateAMPM fromDateHour = new DateAMPM(DateTimeUtility.parseYYYYMMDD(request.getFromDate()), request.getFromAM(), true);
		DateAMPM toDateHour = new DateAMPM(DateTimeUtility.parseYYYYMMDD(request.getToDate()), request.getToAM(), false);
		
		request.setBeginTime(fromDateHour.getDateHour());
		request.setEndTime(toDateHour.getDateHour());
		
		deliveryStaffHandler.checkLeave(account.getEmployeePositionCode(), fromDateHour.getDateHour(), toDateHour.getDateHour(), request.getLeaveTypeCode());
		
		BigDecimal totalLeaveDays = BigDecimal.ZERO;
		totalLeaveDays = com.i360r.bpm.business.util.DateTimeUtility.getIntervalDays(
				fromDateHour.getDateHour(), toDateHour.getDateHour());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(DeliveryStaffLeaveConstants.KEY_IS_STATION_MANAGER, PositionCode.DELIVERY_STATION_MANAGER.getCode().equals(deliveryStaffVO.getPositonCode()));
		variables.put(DeliveryStaffLeaveConstants.KEY_STATION_MANAGER_CODE, stationManagerCode);
		variables.put(DeliveryStaffLeaveConstants.KEY_TOTAL_LEAVE_DAYS, totalLeaveDays);
		variables.put(DeliveryStaffLeaveConstants.KEY_FROM_DATE_HOUR, DateTimeUtility.formatYYYYMMDDHHMMSS(fromDateHour.getDateHour()));
		variables.put(DeliveryStaffLeaveConstants.KEY_TO_DATE_HOUR, DateTimeUtility.formatYYYYMMDDHHMMSS(toDateHour.getDateHour()));
		
		request.setBusinessAreaName(deliveryStaffVO.getBusinessAreaName());
		request.setFullName(deliveryStaffVO.getFullName());
		variables.put(DeliveryStaffLeaveConstants.KEY_ENTRY_DATE, deliveryStaffVO.getEntryDate());
		
		processHandler.startProcess(account, request, DeliveryStaffLeaveConstants.PROCESS_DEFINITION_KEY, variables, AccountType.DELIVERY_STAFF);
		
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse stationManagerApprove(ApproveRequest request) {
		
		processHandler.completeTask(request, DeliveryStaffLeaveConstants.KEY_STATION_MANAGER_APPROVED);
		
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse directManagementAreaManagerApprove(
			ApproveRequest request) {
		processHandler.completeTask(request, DeliveryStaffLeaveConstants.KEY_DIRECT_MANAGEMENT_AREA_MANAGER_APPROVED);
		
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse directManagementCityManagerApprove(
			ApproveRequest request) {
		processHandler.completeTask(request, DeliveryStaffLeaveConstants.KEY_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED);
		
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse directManagementLogisticsDirectorApprove(
			ApproveRequest request) {
		processHandler.completeTask(request, DeliveryStaffLeaveConstants.KEY_DIRECT_MANAGEMENT_LOGISTICS_DIRECTOR_APPROVE);
		
		return new ServiceResponse();
	}

}
