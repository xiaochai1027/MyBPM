package com.i360r.bpm.service.rs.process.deliverystaff.grade.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.deliverystaff.grade.DeliveryStaffGradeConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.service.aop.security.annotation.RequireAuthentication;

@RequireAuthentication
public interface IDeliveryStaffGradeService {

	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<DeliveryStaffGradeDetailVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);
	
	@POST
	@Path("/" + DeliveryStaffGradeConstants.TASK_CREATE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(CreateDeliveryStaffGradeRequest request) throws Exception;
	
	@POST
	@Path("/" + DeliveryStaffGradeConstants.TASK_DIRECT_MANAGEMENT_AREA_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementAreaManagerApprove(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/" + DeliveryStaffGradeConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementCityManagerApprove(ApproveRequest request) throws Exception;
	
}
