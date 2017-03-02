package com.i360r.bpm.service.rs.process.loanApply;

public class LoanApplyConstants {
	
	public static final String PROCESS_DEFINITION_KEY = "loanApply";
	
	public static final String TASK_CREATE = "create";
	
	//老版流程
	public static final String TASK_LOGISTICS_DIRECTOR_APPROVE = "logisticsDirectorApprove";
	public static final String TASK_LOGISTICS_DIRECTOR_APPROVED = "logisticsDirectorApproved";
	
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
	
	//主管领导
	public static final String TASK_COO_APPROVE = "COOApprove";
	public static final String TASK_COO_APPROVED = "COOApproved";
	public static final String TASK_EXCEED_COO_APPROVED_LIMIT = "exceedCOOApproveLimit";
	
	//总经理
	public static final String TASK_CEO_APPROVE = "CEOApprove";
	public static final String TASK_CEO_APPROVED = "CEOApproved";
	
	public static final String TASK_MODIFY_APPLICATIONREPORT = "modifyApplicationReport";
	public static final String TASK_APPROVE_REAPPLY = "approveReapply";
	
	public static final String TASK_CASHIER_CONFIRM = "cashierConfirm";
	public static final String TASK_CASHIER_CONFIRMED = "cashierConfirmed";
	
	//会计审核
	public static final String TASK_ACCOUNTANT_APPROVE = "accountantApprove";
	public static final String TASK_ACCOUNTANT_APPROVED = "accountantApproved";
	
	//以下为老流程兼容，员工账号统一1.2
	//大区总监
	public static final String TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVE = "directManagementLargeAreaDirectorApprove";
	public static final String TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVED = "directManagementLargeAreaDirectorApproved";
	public static final String TASK_EXCEED_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVED_LIMIT = "exceedDirectManagementLargeAreaDirectorApprovedLimit";
	
	//物流总经理
	public static final String TASK_LOGISTICS_CHIEF_MANAGER_APPROVE = "logisticsChiefManagerApprove";
	public static final String TASK_LOGISTICS_CHIEF_MANAGER_APPROVED = "logisticsChiefManagerApproved";
	public static final String TASK_EXCEED_LOGISTICS_CHIEF_MANAGER_APPROVED_LIMIT = "exceedLogisticsChiefManagerApprovedLimit";

}
