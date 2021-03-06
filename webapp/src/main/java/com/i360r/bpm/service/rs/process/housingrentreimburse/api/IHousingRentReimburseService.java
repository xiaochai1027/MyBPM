package com.i360r.bpm.service.rs.process.housingrentreimburse.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.service.aop.security.annotation.RequireAuthentication;

/**
 * 房屋租金报销流程api
 * 
 * @author liugang
 *
 */
@RequireAuthentication
public interface IHousingRentReimburseService {

	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<HousingRentReimburseDetailVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);
	
	@POST
	@Path("/" + HousingRentReimburseConstants.TASK_CREATE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(HousingRentReimburseRequest request) throws Exception;

	@POST
    @Path("/" + HousingRentReimburseConstants.TASK_CITY_COMMISSAR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse cityCommissarApprove(ApproveRequest request) throws Exception;
	
	
	@POST
	@Path("/" + HousingRentReimburseConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementCityManagerApprove(ApproveRequest request) throws Exception;
	
	@POST
    @Path("/" + HousingRentReimburseConstants.TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse operationManagementViceDirectorApprove(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/" + HousingRentReimburseConstants.TASK_ACCOUNTANT_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse accountantApprove(ApproveRequest request) throws Exception;
		
	@POST
	@Path("/" + HousingRentReimburseConstants.TASK_APPLICANT_MODIFY)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse applicantModifyReimburse(HousingRentReimburseModifyRequest request) throws Exception;
		
		
	@POST
	@Path("/" + HousingRentReimburseConstants.TASK_CASHIER_CONFIRM)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse cashierConfirm(HousingRentReimburseCashierConfirmRequest request) throws Exception;

	
	@POST
	@Path("/" + HousingRentReimburseConstants.TASK_APPLICANT_UPLOAD_VOUCHER)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse uploadVoucher(ReimburseRentUploadVoucherRequest request) throws Exception;
		
	@POST
	@Path("/" + HousingRentReimburseConstants.TASK_ACCOUNTANT_CONFIRM)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse accountantConfirm(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/history")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ReimburseRentHistoriesResponse getHistory(@FormParam("") ReimburseRentHistoriesRequest request) throws Exception;

	//以下为兼容老流程，员工账号统一1.2
	@POST
	@Path("/" + HousingRentReimburseConstants.TASK_DIRECT_MANAGEMENT_ADMIN_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementAdminManagerApprove(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/" + HousingRentReimburseConstants.TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementLargeAreaDirectorApprove(ApproveRequest request) throws Exception;

}
