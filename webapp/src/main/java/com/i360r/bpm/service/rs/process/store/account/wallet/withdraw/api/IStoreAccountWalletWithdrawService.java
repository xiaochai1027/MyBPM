package com.i360r.bpm.service.rs.process.store.account.wallet.withdraw.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.store.account.wallet.withdraw.StoreAccountWalletWithdrawCreationConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;

public interface IStoreAccountWalletWithdrawService {
	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<StoreAccountWalletWithdrawVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);
	
	@POST
    @Path("/" + StoreAccountWalletWithdrawCreationConstants.TASK_CREATE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(StoreAccountWalletWithdrawRequest storeAccountWalletWithdrawRequest) throws Exception;

	@POST
    @Path("/" + StoreAccountWalletWithdrawCreationConstants.TASK_CASHIER_CONFIRM)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse cashierConfirm(ApproveRequest request) throws Exception;

}
