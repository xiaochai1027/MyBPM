package com.i360r.bpm.service.rs.process.housing.contract.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.framework.common.http.ContentType;
import com.i360r.framework.common.service.rs.api.ServiceResponse;

public interface IHouseCheckinService {
	@GET
	@Path("/detail")
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ProcessDetailResponse<HousingContractVO> getDetail(@QueryParam("processInstanceId") String processInstanceId);
	
	@POST
    @Path("/" + HousingContractCreationConstants.TASK_CREATE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse startProcess(NewHouseApplyRequest newHouseApplyRequest) throws Exception;
	
	@POST
    @Path("/" + HousingContractCreationConstants.TASK_CITY_COMMISSAR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse cityCommissarApprove(ApproveRequest request) throws Exception;
	
	@POST
    @Path("/" + HousingContractCreationConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementCityManagerApprove(ApproveRequest request) throws Exception;
		
	@POST
    @Path("/" + HousingContractCreationConstants.TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse operationManagementViceDirectorApprove(ApproveRequest request) throws Exception;
	
	@POST
    @Path("/" + HousingContractCreationConstants.TASK_OPERATION_MANAGEMENT_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse operationManagementDirectorApprove(ApproveRequest request) throws Exception;
	
	@POST
    @Path("/" + HousingContractCreationConstants.TASK_COO_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse COOApprove(ApproveRequest request) throws Exception;
	
	@POST
    @Path("/" + HousingContractCreationConstants.TASK_CEO_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse CEOApprove(ApproveRequest request) throws Exception;
	
	@POST
    @Path("/" + HousingContractCreationConstants.TASK_ACCOUNTANT_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse accountantApprove(ApproveRequest request) throws Exception;
	
	@POST
    @Path("/" + HousingContractCreationConstants.TASK_CASHIER_CONFIRM)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse cashierConfirm(HousingContractCashierConfirmRequest request) throws Exception;
	
	@POST
    @Path("/" + HousingContractCreationConstants.TASK_ACCOUNTANT_CONFIRM)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse accountantConfirm(ApproveRequest request) throws Exception;

	@POST
	@Path("/" + HousingContractCreationConstants.TASK_HOUSE_RENT_CONFIRM)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse houseRentConfirm(ApproveRequest request) throws Exception;

	@POST
	@Path("/" + HousingContractCreationConstants.TASK_UPLOAD_VOUCHER)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse uploadVoucher(NewHouseApplyUploadVoucherRequest request) throws Exception;
	
	@POST
    @Path("/" + HousingContractCreationConstants.TASK_CASHIER_CONFIRM_GATHERING)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse cashierConfirmGathering(ApproveRequest request) throws Exception;
		
	@POST
	@Path("/" + HousingContractCreationConstants.TASK_APPLICANT_MODIFY)
	@Consumes(ContentType.APPLICATION_JSON_UTF8)
	@Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse applicantModifyHousingContract(HousingContractModifyRequest request) throws Exception;
	
	//以下为老流程需要兼容的代码，员工账统一1.2
	@POST
    @Path("/" + HousingContractCreationConstants.TASK_DIRECT_MANAGEMENT_ADMIN_MANAGER_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse adminManagerApprove(ApproveRequest request) throws Exception;
	
	@POST
    @Path("/" + HousingContractCreationConstants.TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse directManagementLargeAreaDirectorApprove(ApproveRequest request) throws Exception;
	
	@POST
    @Path("/" + HousingContractCreationConstants.TASK_LOGISTICS_CHIEF_MANAGER_APPROVE)
    @Consumes(ContentType.APPLICATION_JSON_UTF8)
    @Produces(ContentType.APPLICATION_JSON_UTF8)
	public ServiceResponse logisticsChiefManagerApprove(ApproveRequest request) throws Exception;
	
	
}
