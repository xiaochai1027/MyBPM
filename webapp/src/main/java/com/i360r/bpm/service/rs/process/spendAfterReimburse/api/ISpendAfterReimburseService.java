package com.i360r.bpm.service.rs.process.spendAfterReimburse.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.spendAfterReimburse.SpendAfterReimburseConstans;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.aop.annotation.IgnoreValidate;
import com.i360r.framework.common.service.rs.api.ServiceResponse;

/**
 * 报销（后付款） 
 */
public interface ISpendAfterReimburseService {
	
	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<SpendAfterReimburseDetailVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);
	
	@POST
	@Path("/" + SpendAfterReimburseConstans.TASK_CREATE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(SpendAfterReimburseRequest request) throws Exception;
	
	//老接口
	@POST
	@Path("/" + SpendAfterReimburseConstans.TASK_LOGISTICS_DIRECTOR_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse logisticsDirectorApprove(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/" + SpendAfterReimburseConstans.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementCityManagerApprove(ApproveRequest request) throws Exception;
	
	@POST
    @Path("/" + SpendAfterReimburseConstans.TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse operationManagementViceDirectorApprove(ApproveRequest request) throws Exception;
	
	@POST
    @Path("/" + SpendAfterReimburseConstans.TASK_OPERATION_MANAGEMENT_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse operationManagementDirectorApprove(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/" + SpendAfterReimburseConstans.TASK_COO_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse COOApprove(ApproveRequest request) throws Exception;
		
	@POST
	@Path("/" + SpendAfterReimburseConstans.TASK_CEO_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse CEOApprove(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/" + SpendAfterReimburseConstans.TASK_ACCOUNTANT_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse accountantApprove(ApproveRequest request) throws Exception;
		
	@POST
	@Path("/" + SpendAfterReimburseConstans.TASK_MODIFY_REIMBURSEAPPLICATION)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse modifyReimburseApplication(SpendAfterReimburseModifyRequest request) throws Exception;
		
		
	@POST
	@Path("/" + SpendAfterReimburseConstans.TASK_CASHIER_CONFIRM)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse cashierConfirm(SpendAfterReimburseCashierConfirmRequest request) throws Exception;
	
	@GET
	@Path("/" + SpendAfterReimburseConstans.TASK_VOUCHER_DETAIL)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public SpendAfterReimburseVoucherDetailResponse voucherDetail(@QueryParam("processInstanceId") String processInstanceId) throws Exception;
	
	@POST
	@Path("/" + SpendAfterReimburseConstans.TASK_UPLOAD_VOUCHER)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse uploadVoucher(SpendAfterReimburseUploadVoucherRequest request) throws Exception;
		
	@POST
	@Path("/" + SpendAfterReimburseConstans.TASK_ACCOUNTANT_CONFIRM)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse accountantConfirm(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/history")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public SpendAfterReimburseHistoryResponse getHistory(@FormParam("") SpendAfterReimburseHistoryRequest request) throws Exception;

	@POST
	@Path("/history/item")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public SpendAfterReimburseHistoryItemResponse getItemHistory(@FormParam("") SpendAfterReimburseHistoryItemRequest request) throws Exception;
		
	@GET
	@Path("/excel/download")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse downLoadExcelFile(@QueryParam("processInstanceId") String processInstanceId,
			@Context @IgnoreValidate HttpServletRequest request,
			@Context @IgnoreValidate HttpServletResponse response) throws Exception;
	
	//以下为兼容老流程，员工账号统一1.2
	@POST
	@Path("/" + SpendAfterReimburseConstans.TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementLargeAreaDirectorApprove(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/" + SpendAfterReimburseConstans.TASK_LOGISTICS_CHIEF_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse logisticsChiefManagerApprove(ApproveRequest request) throws Exception;

}
