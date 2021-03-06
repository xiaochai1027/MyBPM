package com.i360r.bpm.service.rs.process.reservedCashApply;

import java.math.BigDecimal;

public class ReservedCashApplyConstants {
	public static final String PROCESS_DEFINITION_KEY = "reservedCashApply";
	
	public static final String TASK_CREATE = "create";
	
	//部门负责人(老接口常量定义)
	public static final String TASK_LOGISTICS_DIRECTOR_APPROVE = "logisticsDirectorApprove";
	public static final String TASK_LOGISTICS_DIRECTOR_APPROVED = "logisticsDirectorApproved";
	public static final String TASK_EXCEED_LOGISTICS_DIRECTOR_APPROVED_LIMIT = "exceedLogisticsDirectorApproveLimit";
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
	//修改报销申请
	public static final String TASK_MODIFY_APPLICATIONREPORT = "modifyApplicationReport";
	public static final String TASK_APPROVE_REAPPLY = "approveReapply";
	//出纳确认
	public static final String TASK_CASHIER_CONFIRM = "cashierConfirm";
	public static final String TASK_CASHIER_CONFIRMED = "cashierConfirmed";
	
	public static final String TASK_EMPLOYEE_POSITION = "employeePosition";
	public static final String TASK_EMPLOYEE_MOBILE = "employeeMobile";
	
	//审批上限
	public static BigDecimal APPROVE_TOP_LIMIT = new BigDecimal(100000000);
	
	//以下为兼容老流程，员工账号统一1.2
	//大区总监
	public static final String TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVE = "directManagementLargeAreaDirectorApprove";
	public static final String TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVED = "directManagementLargeAreaDirectorApproved";
	public static final String TASK_EXCEED_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVED_LIMIT = "exceedDirectManagementLargeAreaDirectorApprovedLimit";
	//物流总经理
	public static final String TASK_LOGISTICS_CHIEF_MANAGER_APPROVE = "logisticsChiefManagerApprove";
	public static final String TASK_LOGISTICS_CHIEF_MANAGER_APPROVED = "logisticsChiefManagerApproved";
	public static final String TASK_EXCEED_LOGISTICS_CHIEF_MANAGER_APPROVED_LIMIT = "exceedLogisticsChiefManagerApprovedLimit";	

}
