package com.i360r.bpm.service.rs.process.deliverystaff.workovertime;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.deliverystaff.api.DeliveryStaffVO;
import com.i360r.bpm.business.handler.deliverystaff.api.IDeliveryStaffHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.deliverystaff.workovertime.api.DeliveryStaffWorkOvertimeDetailVO;
import com.i360r.bpm.service.rs.process.deliverystaff.workovertime.api.DeliveryStaffWorkOvertimeRequest;
import com.i360r.bpm.service.rs.process.deliverystaff.workovertime.api.IDeliveryStaffWorkOvertimeService;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.service.api.exception.IllegalRequestException;

public class DeliveryStaffWorkOvertimeService implements IDeliveryStaffWorkOvertimeService {

	@Autowired
	private IDeliveryStaffHandler deliveryStaffHandler;
	
	@Autowired
	private IProcessHandler processHandler;
	
	@Override
	public ProcessDetailResponse<DeliveryStaffWorkOvertimeDetailVO> getDetail(String processInstanceId) {
		return processHandler.getProcessDetail(processInstanceId, DeliveryStaffWorkOvertimeDetailVO.class);
	}
	
	@Override
	public ServiceResponse startProcess(DeliveryStaffWorkOvertimeRequest request) throws Exception {
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		
		DeliveryStaffVO deliveryStaff = deliveryStaffHandler.getDeliveryStaffByCode(request.getDeliveryStaffCode());
		if (deliveryStaff == null 
				|| StringUtils.isBlank(deliveryStaff.getBusinessAreaCode())) {
			throw new IllegalRequestException("delivery staff code or businessareaCode not exist");
		}
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(ProcessConstants.KEY_BUSINESS_AREA_CODE, deliveryStaff.getBusinessAreaCode());
		variables.put(ProcessConstants.KEY_BUSINESS_AREA_NAME, deliveryStaff.getBusinessAreaName());
		
		deliveryStaffHandler.startWorkOvertimeProcess(employee, request, variables);

		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse directManagementAreaManagerApprove(ApproveRequest request) {
		processHandler.completeTask(request, DeliveryStaffWorkOvertimeConstants.KEY_DIRECT_MANAGEMENT_AREA_MANAGER_APPROVED);
		
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse directManagementCityManagerApprove(ApproveRequest request) {
		processHandler.completeTask(request, DeliveryStaffWorkOvertimeConstants.KEY_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED);
		
		return new ServiceResponse();
		
	}
	
	@Override
	public ServiceResponse directManagementLogisticsDirectorApprove(ApproveRequest request) {
		processHandler.completeTask(request, DeliveryStaffWorkOvertimeConstants.KEY_DIRECT_MANAGEMENT_LOGISTICS_DIRECTOR_APPROVED);
		
		return new ServiceResponse();
	}

}
