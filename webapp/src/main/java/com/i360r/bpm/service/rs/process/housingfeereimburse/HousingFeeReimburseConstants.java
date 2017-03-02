package com.i360r.bpm.service.rs.process.housingfeereimburse;

public class HousingFeeReimburseConstants {

	public static final String PROCESS_DEFINITION_KEY = "housingFeeReimburse";

	public static final String TASK_CREATE = "create";

	// 城市经理
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
	
	// COO
	public static final String TASK_COO_APPROVE = "COOApprove";
	public static final String TASK_COO_APPROVED = "COOApproved";
	public static final String TASK_EXCEED_COO_APPROVED_LIMIT = "exceedCOOApproveLimit";
	
	// CEO
	public static final String TASK_CEO_APPROVE = "CEOApprove";
	public static final String TASK_CEO_APPROVED = "CEOApproved";
	
	// 修改报销申请
	public static final String TASK_APPLICANT_MODIFY = "applicantModify";
	public static final String TASK_APPROVE_REAPPLY = "approveReapply";
	
	// 会计审批
	public static final String TASK_ACCOUNTANT_APPROVE = "accountantApprove";
	public static final String TASK_ACCOUNTANT_APPROVED = "accountantApproved";
	
	// 出纳确认
	public static final String TASK_CASHIER_CONFIRM = "cashierConfirm";
	public static final String TASK_CASHIER_CONFIRMED = "cashierConfirmed";


	public static final String TASK_EMPLOYEE_POSITION = "employeePosition";
	public static final String TASK_EMPLOYEE_MOBILE = "employeeMobile";

	public static final String TASK_ITEMS = "items";
	public static final String REJECT_ITEMS_REMIND = "rejectItemsRemind";

	public static final Integer MONTHLY_HOUSING_FEE_REIMBURSE_COUNT = 2;

	//以下为兼容老流程需要的常量，员工账号统一1.2
	// 大区总监
	public static final String TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVE = "directManagementLargeAreaDirectorApprove";
	public static final String TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVED = "directManagementLargeAreaDirectorApproved";
	public static final String TASK_EXCEED_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVED_LIMIT = "exceedDirectManagementLargeAreaDirectorApprovedLimit";
	
	// 物流总经理
	public static final String TASK_LOGISTICS_CHIEF_MANAGER_APPROVE = "logisticsChiefManagerApprove";
	public static final String TASK_LOGISTICS_CHIEF_MANAGER_APPROVED = "logisticsChiefManagerApproved";
	public static final String TASK_EXCEED_LOGISTICS_CHIEF_MANAGER_APPROVED_LIMIT = "exceedLogisticsChiefManagerApprovedLimit";
	

	
}
