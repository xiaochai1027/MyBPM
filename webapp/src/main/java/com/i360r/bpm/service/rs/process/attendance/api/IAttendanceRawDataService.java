package com.i360r.bpm.service.rs.process.attendance.api;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.attendance.AttendanceRawDataConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;

import javax.ws.rs.*;

/**
 * Created by MengWeiBo on 2016-12-07
 */
public interface IAttendanceRawDataService {

    @POST
    @Path("/" + AttendanceRawDataConstants.TASK_CREATE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse startProcess(AttendanceRawDataRequest request) throws Exception;

    @GET
    @Path("/" + AttendanceRawDataConstants.TASK_DETAIL)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ProcessDetailResponse<AttendanceRawDataDetailVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);

    @POST
    @Path("/" + AttendanceRawDataConstants.TASK_DIRECT_MANAGEMENT_AREA_MANAGER_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse directManagementAreaManagerApprove(ApproveRequest request);

    @POST
    @Path("/" + AttendanceRawDataConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse directManagementCityManagerApprove(ApproveRequest request);
}
