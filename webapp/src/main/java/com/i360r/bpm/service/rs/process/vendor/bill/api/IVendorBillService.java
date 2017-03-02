package com.i360r.bpm.service.rs.process.vendor.bill.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.vendor.bill.VendorBillConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;

public interface IVendorBillService {
	
	@POST
	@Path("/" + VendorBillConstants.TASK_CREATE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(VendorBillDataRequest request) throws Exception;//创建流程

	@GET
	@Path("/" + VendorBillConstants.TASK_DETAILS)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<VendorBillDataDetailVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);//流程详情

	@POST
	@Path("/"+ VendorBillConstants.TASK_BUSINESS_MANANGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse businessManagerApprove(BusinessManagerApproveRequest request);//账单负责人审批账单

	@POST
	@Path("/"+ VendorBillConstants.TASK_CASHIER_CONFIRM)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse cashierConfirm(CashierConfirmRequest request);//出纳审批账单
	
	@POST
	@Path("/"+ VendorBillConstants.TASK_REASSIGN_BUSINESS_MANANGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse reassignBillHandle(ReassignBillHandleRequest request);//重新指派
}
