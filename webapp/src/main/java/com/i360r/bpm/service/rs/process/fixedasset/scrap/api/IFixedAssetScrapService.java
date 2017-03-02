package com.i360r.bpm.service.rs.process.fixedasset.scrap.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.fixedasset.scrap.FixedAssetScrapConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;

public interface IFixedAssetScrapService {
	
	@POST
	@Path("/" + FixedAssetScrapConstants.TASK_CREATE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(FixedAssetScrapRequest request) throws Exception;

	@POST
	@Path("/" + FixedAssetScrapConstants.TASK_ADMIN_SPECIALIST_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse adminSpecialistApprove(ApproveRequest request);
	
	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<FixedAssetScrapDetailVO> getDetail(
			@QueryParam("processInstanceId") String processInstanceId);
}
