package com.i360r.bpm.service.rs.process.store.account.api;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.store.account.StoreAccountConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;

import javax.ws.rs.*;

/**
 * Created by MengWeiBo on 2016-12-22
 */
public interface IStoreAccountService {

    @GET
    @Path("/" + StoreAccountConstants.TASK_DETAILS)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ProcessDetailResponse<StoreAccountVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);

    @POST
    @Path("/" + StoreAccountConstants.TASK_CREATE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse startProcess(CreateStoreAccountRequest request) throws Exception;

    @POST
    @Path("/" + StoreAccountConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse directManagementCityManagerApprove(ApproveRequest request) throws Exception;

    @POST
    @Path("/" + StoreAccountConstants.TASK_BUSINESS_DEVELOPMENT_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse businessSupportManagerApprove(ApproveRequest request) throws Exception;

    @POST
    @Path("/" + StoreAccountConstants.TASK_OPERATION_MANAGEMENT_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse operationManagementDirectorApprove(ApproveRequest request) throws Exception;

    @POST
    @Path("/" + StoreAccountConstants.TASK_FINANCE_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse financeDirectorApprove(ApproveRequest request) throws Exception;

    @POST
    @Path("/" + StoreAccountConstants.TASK_COO_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse cooApprove(ApproveRequest request) throws Exception;


}
