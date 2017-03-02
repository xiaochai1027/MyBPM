package com.i360r.bpm.service.rs.process.employee.global.salary.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.employee.global.salary.GlobalSalaryConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;

public interface IGlobalSalaryService {

	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<GlobalSalaryDetailVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);

	@POST
	@Path("/" + GlobalSalaryConstants.TASK_CREATE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(GlobalSalaryRequest request) throws Exception;

	@POST
	@Path("/" + GlobalSalaryConstants.TASK_CPOAPPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse CPOApprove(GlobalSalaryApproveRequest request) throws Exception;

	@POST
	@Path("/" + GlobalSalaryConstants.TASK_COOAPPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse COOApprove(GlobalSalaryApproveRequest request) throws Exception;

	@POST
	@Path("/" + GlobalSalaryConstants.TASK_COMPENSATION_BENEFITS_SPECIALIST_CONFIRM)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse compensationBenefitsSpecialistConfirm(GlobalSalaryApproveRequest request) throws Exception;

	@POST
	@Path("/" + GlobalSalaryConstants.TASK_COMPENSATION_SPECIALIST_CONFIRM)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse compensationSpecialistConfirm(GlobalSalaryApproveRequest request) throws Exception;


}
