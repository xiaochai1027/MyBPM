package com.i360r.bpm.service.rs.process.vendor.bill;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.handler.vendor.bill.IVendorBillHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.business.util.DateTimeUtility;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.vendor.bill.api.BusinessManagerApproveRequest;
import com.i360r.bpm.service.rs.process.vendor.bill.api.CashierConfirmRequest;
import com.i360r.bpm.service.rs.process.vendor.bill.api.CreateVendorBillRequest;
import com.i360r.bpm.service.rs.process.vendor.bill.api.IVendorBillService;
import com.i360r.bpm.service.rs.process.vendor.bill.api.ReassignBillHandleRequest;
import com.i360r.bpm.service.rs.process.vendor.bill.api.VendorBillDataDetailVO;
import com.i360r.bpm.service.rs.process.vendor.bill.api.VendorBillDataRequest;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.oas.api.internal.rs.vendor.bill.ModifyVendorBillRequest;
import com.i360r.oas.api.internal.rs.vendor.bill.VendorBillInfoResponse;

public class VendorBillService implements IVendorBillService {
	
	private static final String BILL_STATUS_CODE = "SETTLEMENTING";
	
	@Autowired
	private IProcessHandler processHandler;
	
	@Autowired
	private IVendorBillHandler vendorBillHandler;

	@Override
	public ServiceResponse startProcess(VendorBillDataRequest request) throws Exception {
		VendorBillInfoResponse response = vendorBillHandler.getVendorBillInfo(request.getBillId());
		CreateVendorBillRequest billRequest = new CreateVendorBillRequest();
		
		billRequest.setAssignCode(request.getAssignCode()); 
		billRequest.setBillId(request.getBillId()); 
		billRequest.setStoreAccountId(response.getStoreAccountId());
		billRequest.setCompanyName(response.getCompanyName()); 
		billRequest.setInvoiceTitle(response.getInvoiceTitle());
		billRequest.setInvoiceContentName(response.getInvoiceContentName()); 
		billRequest.setInvoiceTypeName(response.getInvoiceTypeName()); 		
		billRequest.setVendorBillCreateTime(DateTimeUtility.formatYYYYMMDDHHMMSS(response.getCreateTime())); 
		billRequest.setBeginDate(DateTimeUtility.formatYYYYMMDD(response.getBeginDate())); 
		billRequest.setEndDate(DateTimeUtility.formatYYYYMMDD(response.getEndDate())); 
		billRequest.setAmount(response.getAmount());
		billRequest.setVendorId(response.getVendorId());
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		ProcessInstance instance = processHandler.startProcess(employee, billRequest, VendorBillConstants.PROCESS_DEFINITION_KEY, AccountType.EMPLOYEE);
		if (instance != null) {
			ModifyVendorBillRequest applyRequest = new ModifyVendorBillRequest();
			applyRequest.setHandleByName(request.getHandleByName());
			applyRequest.setStatusCode(BILL_STATUS_CODE);
			applyRequest.setId(request.getBillId());
			try {
				vendorBillHandler.modifyVendorBill(applyRequest);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ServiceResponse();
	}

	@Override
	public ProcessDetailResponse<VendorBillDataDetailVO> getDetail(String processInstanceId) {
		return processHandler.getProcessDetail(processInstanceId,VendorBillDataDetailVO.class);
	}

	@Override
	public ServiceResponse businessManagerApprove(BusinessManagerApproveRequest request) {
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		ModifyVendorBillRequest applyRequest = new ModifyVendorBillRequest();
		applyRequest.setHandleByName(employee.getName());
		applyRequest.setId(request.getBillId());
		try {
			vendorBillHandler.modifyVendorBill(applyRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		processHandler.completeTask(request,VendorBillConstants.TASK_BUSINESS_MANANGER_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse cashierConfirm(CashierConfirmRequest request) {
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		ModifyVendorBillRequest applyRequest = new ModifyVendorBillRequest();
		applyRequest.setHandleByName(employee.getName());
		applyRequest.setId(request.getBillId());
		try {
			vendorBillHandler.modifyVendorBill(applyRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		processHandler.completeTask(request,VendorBillConstants.TASK_CASHIER_CONFIRMD);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse reassignBillHandle(ReassignBillHandleRequest request) {
		processHandler.completeTask(request,VendorBillConstants.TASK_REASSIGN_BUSINESS_MANANGER_APPROVED);
		return new ServiceResponse();
	}

}
