package com.i360r.bpm.service.rs.process.loanRefund;

public class LoanRefundConstants {
	public static final String PROCESS_DEFINITION_KEY = "loanRefund";
	
	public static final String TASK_CREATE = "create";
	
	//会计审核
	public static final String TASK_ACCOUNTANT_APPROVE = "accountantApprove";
	public static final String TASK_ACCOUNTANT_APPROVED = "accountantApproved";
	
	public static final String TASK_MODIFY_APPLICATIONREPORT = "modifyApplicationReport";
	public static final String TASK_APPROVE_REAPPLY = "approveReapply";
	
	public static final String TASK_CASHIER_CONFIRM = "cashierConfirm";
	public static final String TASK_CASHIER_CONFIRMED = "cashierConfirmed";
}
