package com.i360r.bpm.service.rs.process.housingfeereimburse.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.housingfeereimburse.HousingFeeReimburseConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.service.aop.security.annotation.RequireAuthentication;

@RequireAuthentication
public interface IHousingFeeReimburseService {
	
	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<HousingFeeReimburseDetailVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);
	
	@POST
	@Path("/" + HousingFeeReimburseConstants.TASK_CREATE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(HousingFeeReimburseCreateRequest request) throws Exception;
	
	@POST
	@Path("/" + HousingFeeReimburseConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementCityManagerApprove(HousingFeeReimburseRequest request) throws Exception;
	
	@POST
    @Path("/" + HousingFeeReimburseConstants.TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse operationManagementViceDirectorApprove(HousingFeeReimburseRequest request) throws Exception;
	
	@POST
    @Path("/" + HousingFeeReimburseConstants.TASK_OPERATION_MANAGEMENT_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse operationManagementDirectorApprove(HousingFeeReimburseRequest request) throws Exception;
	 
	@POST
	@Path("/" + HousingFeeReimburseConstants.TASK_COO_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse COOApprove(HousingFeeReimburseRequest request) throws Exception;
		
		
	@POST
	@Path("/" + HousingFeeReimburseConstants.TASK_CEO_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse CEOApprove(HousingFeeReimburseRequest request) throws Exception;
	
		
	@POST
	@Path("/" + HousingFeeReimburseConstants.TASK_APPLICANT_MODIFY)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse applicantModifyReimburse(HousingFeeReimburseModifyRequest request) throws Exception;
		
		
	@POST
	@Path("/" + HousingFeeReimburseConstants.TASK_CASHIER_CONFIRM)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse cashierConfirm(HousingFeeReimburseCashierConfirmRequest request) throws Exception;
	
	@POST
	@Path("/" + HousingFeeReimburseConstants.TASK_ACCOUNTANT_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse accountantApprove(HousingFeeReimburseRequest request) throws Exception;
	
	//以下为兼容老流程，员工账号统一1.2
	@POST
	@Path("/" + HousingFeeReimburseConstants.TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementLargeAreaDirectorApprove(HousingFeeReimburseRequest request) throws Exception;
	
	@POST
	@Path("/" + HousingFeeReimburseConstants.TASK_LOGISTICS_CHIEF_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse logisticsChiefManagerApprove(HousingFeeReimburseRequest request) throws Exception;

}
