package com.i360r.bpm.service.rs.process.deliverystaffdimission.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.deliverystaffdimission.DeliveryStaffDimissionConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.service.aop.security.annotation.RequireAuthentication;

@RequireAuthentication
public interface IDeliveryStaffDimissionService {

	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<DeliveryStaffDimissionDetailVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);
	
	@POST
	@Path("/" + DeliveryStaffDimissionConstants.TASK_CREATE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(DeliveryStaffDimissionRequest request) throws Exception;

	@POST
	@Path("/" + DeliveryStaffDimissionConstants.TASK_DIRECT_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagerApprove(ApproveRequest request);
	
	@POST
	@Path("/" + DeliveryStaffDimissionConstants.TASK_INDIRECT_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse indirectManagerApprove(ApproveRequest request);
	
	@POST
	@Path("/" + DeliveryStaffDimissionConstants.TASK_DIRECT_MANAGEMENT_AREA_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementAreaManagerApprove(ApproveRequest request);
	
	@POST
	@Path("/" + DeliveryStaffDimissionConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementCityManagerApprove(ApproveRequest request);
	
	@POST
	@Path("/" + DeliveryStaffDimissionConstants.TASK_DIRECT_MANAGEMENT_LOGISTICS_DIRECTOR_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementLogisticsDirectorApprove(ApproveRequest request);
	
	@POST
	@Path("/" + DeliveryStaffDimissionConstants.TASK_HUMAN_RESOURCE_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse humanResourceApprove(ApproveRequest request) throws Exception;
	
}
