package com.i360r.bpm.service.rs.process.inner.announcement.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.inner.announcement.InnerAnnouncementConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;

public interface IInnerAnnouncementService {
	
	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<InnerAnnouncementDetailVO> getDetail(
			@QueryParam("processInstanceId") String processInstanceId);
	
	@POST
	@Path("/" + InnerAnnouncementConstants.TASK_CREATE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(CreateInnerAnnouncementRequest request) throws Exception;
	
	@POST
	@Path("/" + InnerAnnouncementConstants.TASK_DIRECT_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManageApprove(ApproveRequest request);
	
	@POST
	@Path("/" + InnerAnnouncementConstants.TASK_MODIFY_APPLICANT_REPORT)
	@Consumes(ContentType.APPLICATION_JSON)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse applicantModifyInnerMessage(ModifyInnerAnnouncementRequest request) throws Exception;

}
