package com.i360r.bpm.service.rs.process.vendor.api;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.vendor.VendorConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;

import javax.ws.rs.*;

/**
 * Created by MengWeiBo on 2016-12-22
 */
public interface IVendorService {

    @GET
    @Path("/" + VendorConstants.TASK_DETAILS)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ProcessDetailResponse<VendorVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);

    @POST
    @Path("/" + VendorConstants.TASK_CREATE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse startProcess(CreateVendorRequest request) throws Exception;

    @POST
    @Path("/" + VendorConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse directManagementCityManagerApprove(ApproveRequest request) throws Exception;

    @POST
    @Path("/" + VendorConstants.TASK_BUSINESS_DEVELOPMENT_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse businessSupportManagerApprove(ApproveRequest request) throws Exception;

    @POST
    @Path("/" + VendorConstants.TASK_OPERATION_MANAGEMENT_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse operationManagementDirectorApprove(ApproveRequest request) throws Exception;

    @POST
    @Path("/" + VendorConstants.TASK_FINANCE_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse financeDirectorApprove(ApproveRequest request) throws Exception;

    @POST
    @Path("/" + VendorConstants.TASK_COO_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse cooApprove(ApproveRequest request) throws Exception;


}
