package com.i360r.bpm.service.rs.process.delivery.stationmanager.entry.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.delivery.stationmanager.entry.StationManagerEntryConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.service.aop.security.annotation.RequireAuthentication;

/**
 * 
 * @ClassName: IStationManagerEntryService
 * @Description: 站长入职流程前端接口
 * @author 柴方晨
 * @date 2016年7月18日 下午3:26:42
 *
 */
@RequireAuthentication
public interface IStationManagerEntryService {

	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<StationManagerEntryDetailVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);
	
	@POST
	@Path("/" + StationManagerEntryConstants.TASK_CREATE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(StationManagerHeadCountRequest request) throws Exception;
		
	@POST
	@Path("/" + StationManagerEntryConstants.TASK_HUMAN_RESOURCE_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse humanResourceApprove(ApproveRequest request);
	
	@POST
	@Path("/" + StationManagerEntryConstants.TASK_MODIFY_HEAD_COUNT_APPLICATION)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse modifyHeadCountApplication(StationManagerHeadCountModifyRequest request) throws Exception;
	
	@POST
	@Path("/" + StationManagerEntryConstants.TASK_ENTRY_APPLICATION)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse entryApplication(StationManagerEntryRequest request);
	
	@POST
	@Path("/" + StationManagerEntryConstants.TASK_HUMAN_RESOURCE_ENTRY_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse humanResourceEntryApprove(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/" + StationManagerEntryConstants.TASK_MODIFY_ENTRY_APPLICATION)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse modifyEntryApplication(StationManagerEntryModifyRequest request) throws Exception;
	
	@POST
    @Path("/" + StationManagerEntryConstants.SEND_MOBILE_VERIFICATION_CODE)
    @Consumes(ContentType.APPLICATION_JSON)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse sendMobileVerificationCode(SendMobileVerificationCodeRequest verificationRequest) throws Exception ;
	
}
