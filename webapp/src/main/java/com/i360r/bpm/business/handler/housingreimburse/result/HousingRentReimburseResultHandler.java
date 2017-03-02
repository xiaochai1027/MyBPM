package com.i360r.bpm.business.handler.housingreimburse.result;

import java.util.Date;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.businessarea.fund.result.AbstractBusinessAreaFundResultHandler;
import com.i360r.bpm.business.handler.housingreimburse.api.IHousingReimburseHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.service.rs.process.housingrentreimburse.api.HousingRentReimburseConstants;
import com.i360r.bpm.service.rs.process.housingrentreimburse.api.HousingRentReimburseDetailVO;
import com.i360r.cds.api.internal.rs.employee.EmployeeSO;
import com.i360r.framework.log.Log;
import com.i360r.oas.api.internal.rs.house.CreateHousingRentReimburseRequest;

public class HousingRentReimburseResultHandler extends AbstractBusinessAreaFundResultHandler implements IProcessResultHandler {

	private static final Log LOG = Log.getLog(HousingRentReimburseResultHandler.class);
	
	
	@Autowired
	private IHousingReimburseHandler housingReimburseHandler;
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	
	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		
		LOG.info(">>>HousingRentReimburse Pass!");
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());

		HousingRentReimburseDetailVO detail = ProcessUtility.getProcessObject(processVariables, HousingRentReimburseDetailVO.class);
		
		CreateHousingRentReimburseRequest request = new CreateHousingRentReimburseRequest();
		
		request.setAmount(detail.getAmount());
		request.setContractId(detail.getContractId());

		request.setBeginDate(detail.getBeginDate());
		request.setEndDate(detail.getEndDate());
		
		request.setLastPay(detail.getLastPay());
		request.setNextPayDate(detail.getNextPayDate());
		request.setNote(detail.getNote());
		request.setLetter(detail.getLetter());
		request.setActualPayee(detail.getActualPayee());
		request.setLetterBankCardNumber(detail.getLetterBankCardNumber());
		request.setLetterInterbankNumber(detail.getLetterInterbankNumber());
		request.setLetterBankInstitution(detail.getLetterBankInstitution());

		//TODO 兼容老版本Operator
		request.setOperatorCode(detail.getOperatorCode());
		request.setOperatorBankCardNumber(detail.getOperatorBankCardNumber());
		request.setOperatorInterbankNumber(detail.getOperatorInterbankNumber());
		request.setOperatorName(detail.getOperatorName());

		request.setActualPaymentDate(detail.getActualPaymentDate());
		request.setAttachmentUrl(detail.getAttachmentUrl());
		request.setOrigAttachmentUrl(detail.getOrigAttachmentUrl());

		Date createTime = new Date();
		
		// 创建人、付款人信息
		String createdByCode = (String)processVariables.get(ProcessConstants.KEY_CREATED_BY_CODE);
		String createdByName = (String)processVariables.get(ProcessConstants.KEY_CREATED_BY_NAME);
		String paidByCode = (String)processVariables.get(ProcessConstants.KEY_PAID_BY_CODE);
		Date payTime = (Date)processVariables.get(ProcessConstants.KEY_PAY_TIME);
		
		EmployeeSO employee = employeeHandler.getEmployeeByCode(paidByCode);
		String paidByName = employee.getFullName();
		
		request.setCreateTime(createTime);
		request.setCreatedByCode(createdByCode);
		request.setCreatedByName(createdByName);
		
		request.setPayDate(payTime);
		request.setPaidByCode(paidByCode);
		request.setPaidByName(paidByName);
		
		housingReimburseHandler.createHousingRentReimburse(request);
		
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		
		LOG.info(">>>HousingRentReimburse deny!");
		
		messageHandler.notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		
		return HousingRentReimburseConstants.PROCESS_DEFINITION_KEY;
	}

}
