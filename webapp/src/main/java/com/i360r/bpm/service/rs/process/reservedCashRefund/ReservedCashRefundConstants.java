package com.i360r.bpm.service.rs.process.reservedCashRefund;

public class ReservedCashRefundConstants {
	public static final String PROCESS_DEFINITION_KEY = "reservedCashRefund";
	
	public static final String TASK_CREATE = "create";
	
	public static final String TASK_LOGISTICS_DIRECTOR_APPROVE = "logisticsDirectorApprove";
	public static final String TASK_LOGISTICS_DIRECTOR_APPROVED = "logisticsDirectorApproved";
	
	//城市经理
	public static final String TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE = "directManagementCityManagerApprove";
	public static final String TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED = "directManagementCityManagerApproved";
	
	//运营管理副总监
	public static final String TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVE = "operationManagementViceDirectorApprove";
	public static final String TASK_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVED = "operationManagementViceDirectorApproved";
	public static final String TASK_EXCEED_OPERATION_MANAGEMENT_VICE_DIRECTOR_APPROVED_LIMIT = "exceedOperationManagementViceDirectorApprovedLimit";

	
	public static final String TASK_MODIFYAPPLICATION_REPORT = "modifyApplicationReport";
	public static final String TASK_APPROVE_REAPPLY = "approveReapply";
	
	public static final String TASK_STATION_MANAGER_CONFIRM = "stationMasterConfirm"; 
	public static final String TASK_STATION_MANAGER_CONFIRMD = "stationMasterConfirmd"; 
	
	public static final String TASK_CASHIER_CONFIRM = "cashierConfirm";
	public static final String TASK_CASHIER_CONFIRMD = "cashierConfirmed";
	
	// 以下为兼容老流程，员工账号统一1.2
	//大区总监
	public static final String TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVE = "directManagementLargeAreaDirectorApprove";
	public static final String TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVED = "directManagementLargeAreaDirectorApproved";
	public static final String TASK_EXCEED_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVED_LIMIT = "exceedDirectManagementLargeAreaDirectorApprovedLimit";

}
