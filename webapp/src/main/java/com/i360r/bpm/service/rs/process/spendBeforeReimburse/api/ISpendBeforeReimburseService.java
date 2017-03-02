package com.i360r.bpm.service.rs.process.spendBeforeReimburse.api;

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

import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.SpendBeforeReimburseConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.aop.annotation.IgnoreValidate;
import com.i360r.framework.common.service.rs.api.ServiceResponse;

/**
 * 报销（后付款） 
 */
public interface ISpendBeforeReimburseService {

	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<SpendBeforeReimburseDetailVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);
	
	@POST
	@Path("/" + SpendBeforeReimburseConstants.TASK_CREATE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(SpendBeforeReimburseCreateRequest request) throws Exception;
	
	@POST
	@Path("/" + SpendBeforeReimburseConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementCityManagerApprove(SpendBeforeReimburseRequest request) throws Exception;
	
	@POST
    @Path("/" + SpendBeforeReimburseConstants.TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse operationManagementViceDirectorApprove(SpendBeforeReimburseRequest request) throws Exception;
	
	@POST
    @Path("/" + SpendBeforeReimburseConstants.TASK_OPERATION_MANAGEMENT_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse operationManagementDirectorApprove(SpendBeforeReimburseRequest request) throws Exception;
	 
	@POST
	@Path("/" + SpendBeforeReimburseConstants.TASK_COO_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse COOApprove(SpendBeforeReimburseRequest request) throws Exception;
		
		
	@POST
	@Path("/" + SpendBeforeReimburseConstants.TASK_CEO_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse CEOApprove(SpendBeforeReimburseRequest request) throws Exception;
	
		
	@POST
	@Path("/" + SpendBeforeReimburseConstants.TASK_MODIFY_REIMBURSEAPPLICATION)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse modifyReimburseApplication(SpendBeforeReimburseModifyRequest request) throws Exception;
		
		
	@POST
	@Path("/" + SpendBeforeReimburseConstants.TASK_CASHIER_CONFIRM)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse cashierConfirm(SpendBeforeReimburseCashierConfirmRequest request) throws Exception;
	
	@POST
	@Path("/" + SpendBeforeReimburseConstants.TASK_ACCOUNTANT_CONFIRM)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse accountantConfirm(SpendBeforeReimburseRequest request) throws Exception;
	
	@POST
	@Path("/history")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public SpendBeforeReimburseHistoryResponse getHistory(@FormParam("") SpendBeforeReimburseHistoryRequest request) throws Exception;
	
	@POST
	@Path("/history/item")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public SpendBeforeReimburseHistoryItemResponse geteReimburseItem(@FormParam("") SpendBeforeReimburseHistoryItemRequest request) throws Exception;
	
	@GET
	@Path("/excel/download")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse downLoadExcelFile(@QueryParam("processInstanceId") String processInstanceId,
			@Context @IgnoreValidate HttpServletRequest request, 
			@Context @IgnoreValidate HttpServletResponse response) throws Exception;
	
	//以下为兼容老流程，员工账号统一1.2
	//老版流程（物流总监审批）
	@POST
	@Path("/" + SpendBeforeReimburseConstants.TASK_LOGISTICS_DIRECTOR_APPROVE)
	public ServiceResponse logisticsDirectorApprove(SpendBeforeReimburseRequest request)	throws Exception;
	
	@POST
	@Path("/" + SpendBeforeReimburseConstants.TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementLargeAreaDirectorApprove(SpendBeforeReimburseRequest request) throws Exception;
	
	@POST
	@Path("/" + SpendBeforeReimburseConstants.TASK_LOGISTICS_CHIEF_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse logisticsChiefManagerApprove(SpendBeforeReimburseRequest request) throws Exception;

	
}
