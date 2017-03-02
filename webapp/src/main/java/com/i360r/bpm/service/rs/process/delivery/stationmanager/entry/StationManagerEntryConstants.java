package com.i360r.bpm.service.rs.process.delivery.stationmanager.entry;

/**
 * 
 * @ClassName: StationManagerEntryConstants
 * @Description: 站长入职流程常量
 * @author 柴方晨
 * @date 2016年7月15日 下午5:27:37
 *
 */
public class StationManagerEntryConstants {

	public static final String PROCESS_DEFINITION_KEY = "stationManagerEntry";

	
	public static final String TASK_CREATE = "create";
	public static final String TASK_ENTRY_APPLICATION = "entryApplication";
	public static final String TASK_MODIFY_HEAD_COUNT_APPLICATION = "modifyHeadCountApplication";
	public static final String TASK_MODIFY_ENTRY_APPLICATION = "modifyEntryApplication";
	public static final String TASK_HUMAN_RESOURCE_APPROVE = "humanResourceApprove";
	public static final String TASK_HUMAN_RESOURCE_ENTRY_APPROVE = "humanResourceEntryApprove";
	public static final String SEND_MOBILE_VERIFICATION_CODE = "sendMobileVerificationCode";
	
	public static final String KEY_HUMAN_RESOURCE_APPROVED 	= "humanResourceApproved";
	public static final String KEY_HUMAN_RESOURCE_ENTRY_APPROVED = "humanResourceEntryApproved";
	//流程发起人的code
	public static final String KEY_STATIONMANAGER_CREATE_BY_CODE = "stationManagerEntryCreateByCode";
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
	public static final String KEY_ENTRY_MODIFY = "entryModify";
	
}
