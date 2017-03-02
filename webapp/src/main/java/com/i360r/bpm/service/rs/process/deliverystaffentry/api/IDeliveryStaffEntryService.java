package com.i360r.bpm.service.rs.process.deliverystaffentry.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.deliverystaffentry.DeliveryStaffEntryConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.service.aop.security.annotation.RequireAuthentication;

@RequireAuthentication
public interface IDeliveryStaffEntryService {

	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<DeliveryStaffEntryDetailVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);
	
	@POST
	@Path("/" + DeliveryStaffEntryConstants.TASK_CREATE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(DeliveryStaffHeadCountRequest request) throws Exception;
	
	@POST
	@Path("/" + DeliveryStaffEntryConstants.TASK_DIRECT_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagerApprove(ApproveRequest request);
	
	@POST
	@Path("/" + DeliveryStaffEntryConstants.TASK_INDIRECT_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse indirectManagerApprove(ApproveRequest request);
	
	@POST
	@Path("/" + DeliveryStaffEntryConstants.TASK_DIRECT_MANAGEMENT_AREA_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementAreaManagerApprove(ApproveRequest request);
	
	@POST
	@Path("/" + DeliveryStaffEntryConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementCityManagerApprove(ApproveRequest request);
	
	@POST
	@Path("/" + DeliveryStaffEntryConstants.TASK_DIRECT_MANAGEMENT_LOGISTICS_DIRECTOR_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementLogisticsDirectorApprove(ApproveRequest request);
	
	@POST
	@Path("/" + DeliveryStaffEntryConstants.TASK_HUMAN_RESOURCE_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse humanResourceApprove(ApproveRequest request);
	
	@POST
	@Path("/" + DeliveryStaffEntryConstants.TASK_MODIFY_HEAD_COUNT_APPLICATION)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse modifyHeadCountApplication(DeliveryStaffHeadCountModifyRequest request) throws Exception;
	
	@POST
	@Path("/" + DeliveryStaffEntryConstants.TASK_ENTRY_APPLICATION)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse entryApplication(DeliveryStaffEntryRequest request);

	@POST
	@Path("/" + DeliveryStaffEntryConstants.TASK_DIRECT_MANAGEMENT_AREA_MANAGER_ENTRY_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementAreaManagerAgainApprove(ApproveRequest request);
	
	@POST
	@Path("/" + DeliveryStaffEntryConstants.TASK_HUMAN_RESOURCE_ENTRY_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse humanResourceEntryApprove(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/" + DeliveryStaffEntryConstants.TASK_MODIFY_ENTRY_APPLICATION)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse modifyEntryApplication(DeliveryStaffEntryModifyRequest request) throws Exception;
	
	@POST
    @Path("/" + DeliveryStaffEntryConstants.SEND_MOBILE_VERIFICATION_CODE)
    @Consumes(ContentType.APPLICATION_JSON)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse sendMobileVerificationCode(SendMobileVerificationCodeRequest verificationRequest) throws Exception ;
	
}
