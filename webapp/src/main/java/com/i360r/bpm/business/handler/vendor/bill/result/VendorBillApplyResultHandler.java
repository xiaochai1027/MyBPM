package com.i360r.bpm.business.handler.vendor.bill.result;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.handler.vendor.bill.IVendorBillHandler;
import com.i360r.bpm.business.util.DateTimeUtility;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.vendor.bill.VendorBillConstants;
import com.i360r.bpm.service.rs.process.vendor.bill.api.VendorBillDataDetailVO;
import com.i360r.oas.api.internal.rs.vendor.bill.ModifyVendorBillRequest;

public class VendorBillApplyResultHandler extends AbstractEngineHandler implements IProcessResultHandler {
	
	private static final String BILL_STATUS_CODE = "SETTLEMENTED";
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@Autowired
	private IVendorBillHandler vendorBillHandler;

	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		VendorBillDataDetailVO detail = ProcessUtility.getProcessObject(processVariables, VendorBillDataDetailVO.class);
		
		//得到账单id,实际金额，收款日
		String receivedDate = detail.getReceivedDate();
		String remark = detail.getRemark();
		BigDecimal realAmount = detail.getRealAmount();
		Integer billId = detail.getBillId();
		
		Date date = DateTimeUtility.parseYYYYMMDD(receivedDate);
		ModifyVendorBillRequest request = new ModifyVendorBillRequest();
		request.setId(billId);
		request.setRealAmount(realAmount);
		request.setReceivedDate(date);
		request.setRemark(remark);
		request.setStatusCode(BILL_STATUS_CODE);
		
		vendorBillHandler.modifyVendorBill(request);
		messageHandler.notifyApplierNotPass(execution);
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		messageHandler.notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		return VendorBillConstants.PROCESS_DEFINITION_KEY;
	}

}
