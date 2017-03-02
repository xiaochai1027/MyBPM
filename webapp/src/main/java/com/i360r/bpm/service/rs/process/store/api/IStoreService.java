package com.i360r.bpm.service.rs.process.store.api;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.store.StoreConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;

import javax.ws.rs.*;

/**
 * Created by MengWeiBo on 2016-12-23
 */
public interface IStoreService {
    @GET
    @Path("/" + StoreConstants.TASK_DETAILS)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ProcessDetailResponse<StoreVO> getDetail(@QueryParam("processInstanceId") String processInstanceId) throws Exception;

    @POST
    @Path("/" + StoreConstants.TASK_CREATE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse startProcess(CreateStoreRequest request) throws Exception;

    @POST
    @Path("/" + StoreConstants.TASK_BUSINESS_SUPPORT_MANAGER_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse businessSupportManagerApprove(ApproveRequest request) throws Exception;
}
