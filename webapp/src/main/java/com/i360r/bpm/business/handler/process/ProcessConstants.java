package com.i360r.bpm.business.handler.process;

import java.math.BigDecimal;


public class ProcessConstants {

	public static final String ACTIVITY_TYPE_START_EVENT = "startEvent";
	public static final String ACTIVITY_TYPE_USER_TASK = "userTask";
	
	public static final String KEY_LOCATION_CODE = "locationCode";
	public static final String KEY_CREATE_TIME = "createTime";
	public static final String KEY_CREATED_BY_CODE = "createdByCode";
	public static final String KEY_CREATED_BY_NAME = "createdByName";
	public static final String KEY_CREATOR_POSITION_NAMES = "creatorPositionNames";
	public static final String KEY_CREATOR_POSITION_CODE = "creatorPositionCode";
	public static final String KEY_CREATOR_MOBILE = "creatorMobile";
	public static final String KEY_CREATED_BY_ACCOUNT_TYPE = "createdByAccountType";
	public static final String KEY_LAST_PROCESSOR_CODE = "lastProcessorCode";
	public static final String KEY_FULL_NAME = "fullName";
	public static final String KEY_MOBILE = "mobile";
	public static final String KEY_RESPONSIBLE = "responsible";
	
	public static final String KEY_BUSINESS_AREA_CODE = "businessAreaCode";
	public static final String KEY_BUSINESS_AREA_NAME = "businessAreaName";
	public static final String KEY_UNIQUE_ID = "uniqueId";
	public static final String KEY_BEGIN_TIME = "beginTime";
	public static final String KEY_END_TIME = "endTime";
	
	public static final String SYSTEM_KEY_PREFIX = "sys_";
	
	public static final String KEY_KEYWORD = SYSTEM_KEY_PREFIX + "keyword";
	public static final String KEY_KEYWORD_SEED = SYSTEM_KEY_PREFIX + "keyword_seed";
	public static final String KEY_LOCAL_RESULT = SYSTEM_KEY_PREFIX + "result";
	public static final String KEY_LOCAL_SUGGESTION = SYSTEM_KEY_PREFIX + "suggestion";
	public static final String KEY_PASS = SYSTEM_KEY_PREFIX + "pass";
	
	public static final String KEY_NON_TASK = "NON_TASK";
	public static final String KEY_REIMBURSE_ID = "reimburseId";

	public static final String KEY_PAY_TIME = "payTime";
	public static final String KEY_PAID_BY_CODE = "paidByCode";	
	//审批上限
	public static BigDecimal APPROVE_TOP_LIMIT = new BigDecimal(100000000);
	// store account
	public static final String KEY_STORE_ACCOUNT_ID = "storeAccountId";
	public static final String KEY_STORE_ACCOUNT_MOBILE = "storeAccountMobile";
	public static final String KEY_STORE_ACCOUNT_WALLET_AMOUNT = "storeAccountWalletAmount";
	public static final String KEY_STORE_ACCOUNT_WITHDRAW_FAILED_REASON = "failedReason";

}
