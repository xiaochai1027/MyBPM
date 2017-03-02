package com.i360r.bpm.service.rs.process.deliverystaffcancelleave.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.deliverystaffcancelleave.DeliveryStaffCancelLeaveConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.service.aop.security.annotation.RequireAuthentication;

@RequireAuthentication
public interface IDeliveryStaffCancelLeaveService {

	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<DeliveryStaffCancelLeaveDetailVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);
	
	@GET
	@Path("/leaveList")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public GetCancelLeaveListResponse getCancelLeaveList(@QueryParam("") GetCancelLeaveListRequest request);
	
	@POST
	@Path("/" + DeliveryStaffCancelLeaveConstants.TASK_CREATE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(DeliveryStaffCancelLeaveRequest request) throws Exception;
	
	@POST
	@Path("/" + DeliveryStaffCancelLeaveConstants.TASK_STATION_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse stationManagerApprove(ApproveRequest request);
	
	@POST
	@Path("/" + DeliveryStaffCancelLeaveConstants.TASK_DIRECT_MANAGEMENT_AREA_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementAreaManagerApprove(ApproveRequest request);
	
	@POST
	@Path("/" + DeliveryStaffCancelLeaveConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementCityManagerApprove(ApproveRequest request);
	
}
