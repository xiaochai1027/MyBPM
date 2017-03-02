package com.i360r.bpm.service.rs.process.housing.relet.api;

import java.math.BigDecimal;

public class HousingContractReletConstants {
	public static final String PROCESS_DEFINITION_KEY = "housingRelet";
	
	public static final String TASK_CREATE = "create";
	
	//城市政委
	public static final String TASK_CITY_COMMISSAR_APPROVE = "cityCommissarApprove";
	public static final String TASK_CITY_COMMISSAR_APPROVED = "cityCommissarApproved";

	//城市经理
	public static final String TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE = "directManagementCityManagerApprove";
	public static final String TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED = "directManagementCityManagerApproved";

	//运营管理副总监
	public static final String TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVE = "operationManagementViceDirectorApprove";
	public static final String TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVED = "operationManagementViceDirectorApproved";
	public static final String TASK_EXCEED_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVED_LIMIT = "exceedOperationManagementViceDirectorApprovedLimit";
	
	//运营管理总监
	public static final String TASK_OPERATION_MANAGEMENT_DIRECTOR_APPROVE = "operationManagementDirectorApprove";
	public static final String TASK_OPERATION_MANAGEMENT_DIRECTOR_APPROVED = "operationManagementDirectorApproved";
	public static final String TASK_EXCEED_OPERATION_MANAGEMENT_DIRECTOR_APPROVED_LIMIT = "exceedOperationManagementDirectorApprovedLimit";	
	
	//COO
	public static final String TASK_COO_APPROVE = "COOApprove";
	public static final String TASK_COO_APPROVED = "COOApproved";
	public static final String TASK_EXCEED_COO_APPROVED_LIMIT = "exceedCOOApproveLimit";
	//CEO
	public static final String TASK_CEO_APPROVE = "CEOApprove";
	public static final String TASK_CEO_APPROVED = "CEOApproved";
	//会计审核
	public static final String TASK_ACCOUNTANT_APPROVE = "accountantApprove";
	public static final String TASK_ACCOUNTANT_APPROVED = "accountantApproved";
	//出纳确认
	public static final String TASK_CASHIER_CONFIRM = "cashierConfirm";
	public static final String TASK_CASHIER_CONFIRMED = "cashierConfirmed";
	//会计确认
	public static final String TASK_ACCOUNTANT_CONFIRM = "accountantConfirm";
	public static final String TASK_ACCOUNTANT_CONFIRMED = "accountantConfirmed";
	//确认租房成功（默认是行政专员）
	public static final String TASK_HOUSE_RENT_CONFIRM   = "houseRentConfirm";
	public static final String TASK_HOUSE_RENT_CONFIRMED  = "houseRentConfirmed";
	//发起人补充房屋合同信息
	public static final String TASK_UPLOAD_VOUCHER = "uploadVoucher";
	public static final String TASK_UPLOADED_VOUCHER = "uploadedVoucher";
	//出纳确认收到钱款
	public static final String TASK_CASHIER_CONFIRM_GATHERING = "cashierConfirmGathering";
	public static final String TASK_CASHIER_CONFIRMED_GATHERING = "cashierConfirmedGathering";
	
	//申请人修改报销申请
	public static final String TASK_APPLICANT_MODIFY = "applicantModify";
	public static final String TASK_APPROVE_REAPPLY = "approveReapply";
	
	//审批上限
	public static BigDecimal APPROVE_TOP_LIMIT = new BigDecimal(100000000);
	
	public static final String TASK_EMPLOYEE_POSITION = "employeePosition";
	public static final String TASK_EMPLOYEE_MOBILE = "employeeMobile";
	
	//押X付X
	public static final String TASK_RENT_PAYMENT_TYPE = "rentPaymentType";
	
}
