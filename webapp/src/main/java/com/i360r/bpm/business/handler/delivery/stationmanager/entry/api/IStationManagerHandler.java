package com.i360r.bpm.business.handler.delivery.stationmanager.entry.api;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.i360r.bpm.business.handler.deliverystaff.api.DeliveryStaffVO;
import com.i360r.bpm.service.rs.process.delivery.stationmanager.entry.api.StationManagerHeadCountRequest;
import com.i360r.bpm.service.rs.process.deliverystaff.workovertime.api.DeliveryStaffWorkOvertimeRequest;
import com.i360r.bpm.service.rs.process.deliverystaffcancelleave.api.HalfDate;
import com.i360r.cds.api.internal.rs.deliverystaff.CreateOfficialAccountRequest;
import com.i360r.cds.api.internal.rs.deliverystaff.CreateTrialAccountRequest;
import com.i360r.cds.api.internal.rs.deliverystaff.DeliveryStaffTransformationRequest;
import com.i360r.cds.api.internal.rs.deliverystaff.DimissDeliveryStaffRequest;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.oas.api.internal.rs.deliverystaff.leave.CancelLeaveRequest;
import com.i360r.oas.api.internal.rs.deliverystaff.leave.CreateWorkOvertimeRequest;
import com.i360r.oas.api.internal.rs.deliverystaff.leave.LeaveSuccessRequest;
import com.i360r.oas.api.internal.rs.deliverystaff.leave.UpdateWorkOvertimeRequest;

public interface IStationManagerHandler {
	
	public DeliveryStaffVO getDeliveryStaffByCode(String code) throws Exception;
	
	public void createOfficialAccount(CreateOfficialAccountRequest request) throws Exception;
	
	public void transferPosition(DeliveryStaffTransformationRequest request) throws Exception;
	
	public void updateWorkOvertime(UpdateWorkOvertimeRequest request) throws Exception;
	
	public String createTrialAccount(CreateTrialAccountRequest request) throws Exception;
	
	public int createApprovingWorkOvertime(CreateWorkOvertimeRequest request) throws Exception;
	
	public void deleteApprovingWorkOvertime(int deliveryStaffWorkOvertimeId) throws Exception;

	public void deliveryStaffDimission(DimissDeliveryStaffRequest request) throws Exception;
	
	public void checkLeave(String deliveryStaffCode, 
			Date fromDateHour, Date toDateHour, String leaveTypeCode) throws Exception;
	
	public void leaveSuccess(LeaveSuccessRequest request) throws Exception;
	
	public void cancelLeaveSuccess(CancelLeaveRequest request) throws Exception;
	
	public boolean isExistingDingdingMobile(String employeeCode, String dingdingMobile) throws Exception;
	
	public void checkCancelLeave(String deliveryStaffCode, int deliveryStaffLeaveId, List<HalfDate> originCancelLeaves)
			throws Exception;

	public void startEntryProcess(EmployeeCO employee, StationManagerHeadCountRequest request) throws Exception;
	
	public void startWorkOvertimeProcess(EmployeeCO employee, 
			DeliveryStaffWorkOvertimeRequest request, Map<String, Object> customVariables) throws Exception;
}
