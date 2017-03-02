package com.i360r.bpm.service.rs.process.housingcheckout;

public class HousingCheckoutCreationConstants {
	public static final String PROCESS_DEFINITION_KEY = "housingCheckout";
	
	public static final String TASK_CREATE = "create";

	//城市政委
	public static final String TASK_CITY_COMMISSAR_APPROVE = "cityCommissarApprove";
	public static final String TASK_CITY_COMMISSAR_APPROVED = "cityCommissarApproved";
	
	//城市经理
	public static final String TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE = "directManagementCityManagerApprove";
	public static final String TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED = "directManagementCityManagerApproved";
	
	//会计审核
	public static final String TASK_ACCOUNTANT_APPROVE = "accountantApprove";
	public static final String TASK_ACCOUNTANT_APPROVED = "accountantApproved";
	//出纳确认
	public static final String TASK_CASHIER_CONFIRM = "cashierConfirm";
	public static final String TASK_CASHIER_CONFIRMED = "cashierConfirmed";
	
	public static final String TASK_APPLICANT_MODIFY = "applicantModify";
	public static final String TASK_APPROVE_REAPPLY = "approveReapply";
	
	//以下为兼容老流程，员工账号统一1.2
	//行政经理
	public static final String TASK_ADMIN_MANAGER_APPROVE = "directManagementAdminManagerApprove";
	public static final String TASK_ADMIN_MANAGER_APPROVED = "directManagementAdminManagerApproved";
	
}
