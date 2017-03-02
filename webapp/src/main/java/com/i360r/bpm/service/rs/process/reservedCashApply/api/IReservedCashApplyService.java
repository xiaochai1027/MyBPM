package com.i360r.bpm.service.rs.process.reservedCashApply.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.reservedCashApply.ReservedCashApplyConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;

public interface IReservedCashApplyService {
	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<ReservedCashApplyDetailVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);
	
	@POST
	@Path("/" + ReservedCashApplyConstants.TASK_CREATE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(ReservedCashApplyRequest request) throws Exception;
	
	//老接口（物流总监审批）
	@POST
	@Path("/" + ReservedCashApplyConstants.TASK_LOGISTICS_DIRECTOR_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse logisticsDirectorApprove(ApproveRequest request) throws Exception;
		
	@POST
	@Path("/" + ReservedCashApplyConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementCityManagerApprove(ApproveRequest request) throws Exception;

	@POST
    @Path("/" + ReservedCashApplyConstants.TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse operationManagementViceDirectorApprove(ApproveRequest request) throws Exception;
	
	@POST
    @Path("/" + ReservedCashApplyConstants.TASK_OPERATION_MANAGEMENT_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse operationManagementDirectorApprove(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/" + ReservedCashApplyConstants.TASK_COO_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse COOApprove(ApproveRequest request) throws Exception;
		
		
	@POST
	@Path("/" + ReservedCashApplyConstants.TASK_CEO_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse CEOApprove(ApproveRequest request) throws Exception;
	
		
	@POST
	@Path("/" + ReservedCashApplyConstants.TASK_MODIFY_APPLICATIONREPORT)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse modifyApplicationReport(ReservedCashApplyModifyRequest request) throws Exception;
		
		
	@POST
	@Path("/" + ReservedCashApplyConstants.TASK_CASHIER_CONFIRM)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse cashierConfirm(ReservedCashCashierConfirmRequest request) throws Exception;
	
	@POST
	@Path("/history")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ReservedCashApplyHistoryResponse getHistory(@FormParam("") ReservedCashApplyHistoryRequest request) throws Exception;
	
	//以下为兼容老流程代码，员工账号统一1.2
	@POST
	@Path("/" + ReservedCashApplyConstants.TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementLargeAreaDirectorApprove(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/" + ReservedCashApplyConstants.TASK_LOGISTICS_CHIEF_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse logisticsChiefManagerApprove(ApproveRequest request) throws Exception;

}
