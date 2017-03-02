package com.i360r.bpm.service.rs.process.loanRefund.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.loanRefund.LoanRefundConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;

public interface ILoanRefundService {
	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<LoanRefundDetailVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);
	
	@POST
	@Path("/" + LoanRefundConstants.TASK_CREATE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(LoanRefundRequest request) throws Exception;
	
	@POST
	@Path("/" + LoanRefundConstants.TASK_ACCOUNTANT_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse accountantApprove(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/" + LoanRefundConstants.TASK_CASHIER_CONFIRM)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse cashierConfirm(LoanRefundCashierConfirmRequest request) throws Exception;
	
	@POST
	@Path("/" + LoanRefundConstants.TASK_MODIFY_APPLICATIONREPORT)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse modifyApplicationReport(LoanRefundModifyRequest request) throws Exception;
	
	@POST
	@Path("/history")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public LoanRefundHistoryResponse getHistory(@FormParam("") LoanRefundHistoryRequest request) throws Exception;
	
}
