package com.i360r.bpm.service.rs.process.cityReimburse.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.cityReimburse.CityReimburseConstants;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;

/**
 * 城市报销 
 */
public interface ICityReimburseService {

	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<CityReimburseDetailVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);
	
	@POST
	@Path("/" + CityReimburseConstants.TASK_CREATE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(CityReimburseCreateRequest request) throws Exception;
	
	@POST
	@Path("/" + CityReimburseConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementCityManagerApprove(CityReimburseRequest request) throws Exception;
	
	@POST
    @Path("/" + CityReimburseConstants.TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse operationManagementViceDirectorApprove(CityReimburseRequest request) throws Exception;
	
	@POST
    @Path("/" + CityReimburseConstants.TASK_OPERATION_MANAGEMENT_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse operationManagementDirectorApprove(CityReimburseRequest request) throws Exception;
	 
	@POST
	@Path("/" + CityReimburseConstants.TASK_COO_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse COOApprove(CityReimburseRequest request) throws Exception;
		
		
	@POST
	@Path("/" + CityReimburseConstants.TASK_CEO_APPROVE)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse CEOApprove(CityReimburseRequest request) throws Exception;
	
		
	@POST
	@Path("/" + CityReimburseConstants.TASK_MODIFY_REIMBURSEAPPLICATION)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse modifyReimburseApplication(CityReimburseModifyRequest request) throws Exception;
		
		
	@POST
	@Path("/" + CityReimburseConstants.TASK_CASHIER_CONFIRM)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse cashierConfirm(CityReimburseCashierConfirmRequest request) throws Exception;
	
	@POST
	@Path("/" + CityReimburseConstants.TASK_ACCOUNTANT_CONFIRM)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse accountantConfirm(CityReimburseRequest request) throws Exception;
	
	@POST
	@Path("/history")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public CityReimburseHistoryResponse getHistory(@FormParam("") CityReimburseHistoryRequest request) throws Exception;
	
	@POST
	@Path("/history/item")
	@Consumes(ContentType.APPLICATION_FORM_URLENCODED)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public CityReimburseHistoryItemResponse geteReimburseItem(@FormParam("") CityReimburseHistoryItemRequest request) throws Exception;
	
	

	
}
