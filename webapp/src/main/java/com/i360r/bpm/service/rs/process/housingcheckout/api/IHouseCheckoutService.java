package com.i360r.bpm.service.rs.process.housingcheckout.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.housingcheckout.HousingCheckoutCreationConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;

public interface IHouseCheckoutService {
	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<HousingCheckoutVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);
	
	@POST
    @Path("/" + HousingCheckoutCreationConstants.TASK_CREATE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(HousingCheckoutRequest housingCheckoutRequest) throws Exception;

	@POST
    @Path("/" + HousingCheckoutCreationConstants.TASK_CITY_COMMISSAR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse cityCommissarApprove(ApproveRequest request) throws Exception;
	
	@POST
    @Path("/" + HousingCheckoutCreationConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementCityManagerApprove(ApproveRequest request) throws Exception;
	
	@POST
    @Path("/" + HousingCheckoutCreationConstants.TASK_ACCOUNTANT_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse accountantApprove(ApproveRequest request) throws Exception;
	
	@POST
    @Path("/" + HousingCheckoutCreationConstants.TASK_CASHIER_CONFIRM)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse cashierConfirm(HousingCheckoutCashierConfirmRequest request) throws Exception;
	
	@POST
	@Path("/" + HousingCheckoutCreationConstants.TASK_APPLICANT_MODIFY)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse housingCheckoutModify(HousingCheckoutModifyRequest request) throws Exception;
	
	//以下为兼容老流程，员工账号统一1.2
	@POST
    @Path("/" + HousingCheckoutCreationConstants.TASK_ADMIN_MANAGER_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse adminManagerApprove(ApproveRequest request) throws Exception;
	
}
