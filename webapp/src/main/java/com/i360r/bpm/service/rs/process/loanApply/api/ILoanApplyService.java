package com.i360r.bpm.service.rs.process.loanApply.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.loanApply.LoanApplyConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;

public interface ILoanApplyService {
	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<LoanApplyDetailVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);
	
	@POST
	@Path("/" + LoanApplyConstants.TASK_CREATE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(LoanApplyRequest request) throws Exception;
	
	//老接口
	@POST
	@Path("/" + LoanApplyConstants.TASK_LOGISTICS_DIRECTOR_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse logisticsDirectorApprove(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/" + LoanApplyConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementCityManagerApprove(ApproveRequest request) throws Exception;
	
	@POST
    @Path("/" + LoanApplyConstants.TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse operationManagementViceDirectorApprove(ApproveRequest request) throws Exception;
	
	@POST
    @Path("/" + LoanApplyConstants.TASK_OPERATION_MANAGEMENT_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse operationManagementDirectorApprove(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/" + LoanApplyConstants.TASK_COO_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse COOApprove(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/" + LoanApplyConstants.TASK_CEO_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse CEOApprove(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/" + LoanApplyConstants.TASK_ACCOUNTANT_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse accountantApprove(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/" + LoanApplyConstants.TASK_MODIFY_APPLICATIONREPORT)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse modifyApplicationReport(LoanApplyModifyRequest request) throws Exception;
	
	@POST
	@Path("/" + LoanApplyConstants.TASK_CASHIER_CONFIRM)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse cashierConfirm(LoanApplyCashierConfirmRequest request) throws Exception;
	
	@POST
	@Path("/history")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public LoanApplyHistoryResponse getHistory(@FormParam("") LoanApplyHistoryRequest request) throws Exception;
	
	//以下为老流程兼容，员工账号统一1.2
	@POST
	@Path("/" + LoanApplyConstants.TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementLargeAreaDirectorApprove(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/" + LoanApplyConstants.TASK_LOGISTICS_CHIEF_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse logisticsChiefManagerApprove(ApproveRequest request) throws Exception;

}
