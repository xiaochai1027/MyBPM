package com.i360r.bpm.service.rs.process.employee.city.salary.api;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.employee.city.salary.CitySalaryConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

public interface ICitySalaryService {
	
	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<CitySalaryDetailVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);

	@POST
	@Path("/" + CitySalaryConstants.TASK_CREATE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(CitySalaryRequest request) throws Exception;
	
	@POST
	@Path("/" + CitySalaryConstants.TASK_CHIEF_COMMISSAR_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse chiefCommissarApprove(ApproveRequest request) throws Exception;
	
	@POST
	@Path("/" + CitySalaryConstants.TASK_COMPENSATION_BENEFITS_SPECIALIST_CHECK)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse compensationBenefitsSpecialistCheck(ApproveRequest request) throws Exception;

	@POST
	@Path("/" + CitySalaryConstants.TASK_COMPENSATION_SPECIALIST_CHECK)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse compensationSpecialistCheck(ApproveRequest request) throws Exception;
	
}
