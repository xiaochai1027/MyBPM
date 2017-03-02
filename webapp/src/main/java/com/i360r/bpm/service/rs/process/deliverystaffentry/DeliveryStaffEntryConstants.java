package com.i360r.bpm.service.rs.process.deliverystaffentry;

public class DeliveryStaffEntryConstants {

	public static final String PROCESS_DEFINITION_KEY = "deliveryStaffEntry";
	
	// version 1
	public static final String TASK_DIRECT_MANAGER_APPROVE = "directManagerApprove";
	public static final String TASK_INDIRECT_MANAGER_APPROVE = "indirectManagerApprove";
	public static final String KEY_DIRECT_MANAGER_APPROVED 	= "directManagerApproved";
	public static final String KEY_INDIRECT_MANAGER_APPROVED= "indirectManagerApproved";
	
	// version 2
	public static final String TASK_CREATE = "create";
	public static final String TASK_ENTRY_APPLICATION = "entryApplication";
	public static final String TASK_MODIFY_HEAD_COUNT_APPLICATION = "modifyHeadCountApplication";
	public static final String TASK_MODIFY_ENTRY_APPLICATION = "modifyEntryApplication";
	public static final String TASK_DIRECT_MANAGEMENT_AREA_MANAGER_APPROVE = "directManagementAreaManagerApprove";
	public static final String TASK_DIRECT_MANAGEMENT_AREA_MANAGER_ENTRY_APPROVE = "directManagementAreaManagerEntryApprove";
	
	public static final String TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVE = "directManagementCityManagerApprove";
	public static final String TASK_DIRECT_MANAGEMENT_LOGISTICS_DIRECTOR_APPROVE = "directManagementLogisticsDirectorApprove";
	public static final String TASK_HUMAN_RESOURCE_APPROVE = "humanResourceApprove";
	public static final String TASK_HUMAN_RESOURCE_ENTRY_APPROVE = "humanResourceEntryApprove";
	public static final String SEND_MOBILE_VERIFICATION_CODE = "sendMobileVerificationCode";
	
	public static final String KEY_DIRECT_MANAGEMENT_AREA_MANAGER_APPROVED = "directManagementAreaManagerApproved";
	public static final String KEY_DIRECT_MANAGEMENT_AREA_MANAGER_ENTRY_APPROVED = "directManagementAreaManagerEntryApproved";
	public static final String KEY_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED = "directManagementCityManagerApproved";
	public static final String KEY_DIRECT_MANAGEMENT_LOGISTICS_DIRECTOR_APPROVED = "directManagementLogisticsDirectorApproved";
	public static final String KEY_HUMAN_RESOURCE_APPROVED 	= "humanResourceApproved";
	public static final String KEY_HUMAN_RESOURCE_ENTRY_APPROVED = "humanResourceEntryApproved";
	//流程发起人的code
	public static final String KEY_DELIVERYSTAFFENTRY_CREATE_BY_CODE = "deliveryStaffEntryCreateByCode";
	//试用账号配送员code
	public static final String KEY_DELIVERY_STAFF_CODE = "deliveryStaffCode";
	//试用账号配送员password
	public static final String KEY_DELIVERY_STAFF_PASSWORD = "password";
	//是否转岗 true是转岗 false是入职
	public static final String KEY_JOB_TRANSFER = "jobTransfer";
	public static final String KEY_JOB_TRANSFER_DATE = "jobTransferDate";
	//转岗前配送员code
	public static final String KEY_ORIGIN_DELIVERY_STAFF_CODE = "originDeliveryStaffCode";
	//转岗前配送员originPositionCode
	public static final String KEY_ORIGIN_POSITION_CODE = "originPositionCode";
	
	public static final String KEY_ENTRY_APPLICATION = "entryApplication";
	public static final String KEY_HEAD_COUNT_REAPPLY = "headCountReapply";
	public static final String KEY_ENTRY_REAPPLY = "entryReapply";
	public static final String KEY_ENTRY_NEEDMODIFY = "needModify";
	
}
