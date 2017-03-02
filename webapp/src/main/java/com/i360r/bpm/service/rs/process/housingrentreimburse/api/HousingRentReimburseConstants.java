package com.i360r.bpm.service.rs.process.housingrentreimburse.api;

/**
 * 房屋租金报销Constants
 * 
 * @author liugang
 *
 */
public class HousingRentReimburseConstants {
	
	public static final String PROCESS_DEFINITION_KEY = "housingRentReimburse";
	
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
	
	//会计审核
	public static final String TASK_ACCOUNTANT_APPROVE = "accountantApprove";
	public static final String TASK_ACCOUNTANT_APPROVED = "accountantApproved";
	
	//申请人修改报销申请
	public static final String TASK_APPLICANT_MODIFY = "applicantModify";
	public static final String TASK_APPROVE_REAPPLY = "approveReapply";
	
	//出纳确认
	public static final String TASK_CASHIER_CONFIRM = "cashierConfirm";
	public static final String TASK_CASHIER_CONFIRMED = "cashierConfirmed";
	
	//申请人上传凭证
	public static final String TASK_APPLICANT_UPLOAD_VOUCHER = "applicantUploadVoucher";
	

	//会计确认
	public static final String TASK_ACCOUNTANT_CONFIRM = "accountantConfirm";
	public static final String TASK_ACCOUNTANT_CONFIRMED = "accountantConfirmed";
	
	public static final String TASK_EMPLOYEE_POSITION = "employeePosition";
	public static final String TASK_EMPLOYEE_MOBILE = "employeeMobile";
	
	//以下为兼容老流程，员工账号统一1.2
	// 行政经理
	public static final String TASK_DIRECT_MANAGEMENT_ADMIN_MANAGER_APPROVE = "directManagementAdminManagerApprove";
	public static final String TASK_DIRECT_MANAGEMENT_ADMIN_MANAGER_APPROVED = "directManagementAdminManagerApproved";
	
	//大区总监
	public static final String TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVE = "directManagementLargeAreaDirectorApprove";
	public static final String TASK_DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR_APPROVED = "directManagementLargeAreaDirectorApproved";

	
	
}
