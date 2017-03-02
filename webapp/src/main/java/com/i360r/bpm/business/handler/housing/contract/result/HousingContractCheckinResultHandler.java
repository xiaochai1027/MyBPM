package com.i360r.bpm.business.handler.housing.contract.result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.housing.contract.api.IHousingContractHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.DateTimeUtility;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.housing.contract.api.HousingContractAttachmentVO;
import com.i360r.bpm.service.rs.process.housing.contract.api.HousingContractCreationConstants;
import com.i360r.bpm.service.rs.process.housing.contract.api.HousingContractVO;
import com.i360r.bpm.service.rs.process.housing.contract.api.HousingDepositVO;
import com.i360r.cds.api.internal.rs.employee.EmployeeSO;
import com.i360r.framework.log.Log;
import com.i360r.oas.api.internal.rs.house.CreateHousingContractAttachment;
import com.i360r.oas.api.internal.rs.house.CreateHousingContractRequest;
import com.i360r.oas.api.internal.rs.house.CreateHousingDeposit;

public class HousingContractCheckinResultHandler extends AbstractEngineHandler implements IProcessResultHandler {

	private static final Log LOG = Log.getLog(HousingContractCheckinResultHandler.class);
	
	@Autowired
	private IHousingContractHandler housingContractHandler;
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@Autowired
	protected IEmployeeHandler employeeHandler;
	
	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		LOG.debug(">>>>HousingContract Checkin approved!");
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		Boolean uploadedVoucher =  (Boolean)processVariables.get(HousingContractCreationConstants.TASK_UPLOADED_VOUCHER);
		Boolean houseRentConfirmed =  (Boolean)processVariables.get(HousingContractCreationConstants.TASK_HOUSE_RENT_CONFIRMED);

		//兼容老本版 新版本无uploadedVoucher
		if (houseRentConfirmed == null){
			if (uploadedVoucher == null){
				//uploadedVoucher为null时为最老流程
				houseRentConfirmed = true;
			}else {
				houseRentConfirmed = uploadedVoucher;
			}
		}
		if (houseRentConfirmed) {
			HousingContractVO detail = ProcessUtility.getProcessObject(processVariables, HousingContractVO.class);
			
			CreateHousingContractRequest request = new CreateHousingContractRequest();
			request.setAddress(detail.getAddress());
			request.setAgentCommissionFee(detail.getAgentCommissionFee());
			request.setAreaSize(detail.getAreaSize());
			request.setBusinessAreaCode(detail.getBusinessAreaCode());
			request.setBusinessAreaName(detail.getBusinessAreaName());
			request.setCityCode(detail.getCityCode());
			request.setCityName(detail.getCityName());
			request.setEffectiveEndDate(DateTimeUtility.parseYYYYMMDD(detail.getEffectiveEndDate()));
			request.setEffectiveStartDate(DateTimeUtility.parseYYYYMMDD(detail.getEffectiveStartDate()));
			request.setIsLastPay(detail.getIsLastPay());
			request.setLatestPayEndDate(DateTimeUtility.parseYYYYMMDD(detail.getLatestPayEndDate()));
			request.setLatestPayStartDate(DateTimeUtility.parseYYYYMMDD(detail.getLatestPayStartDate()));
			request.setLetter(detail.getLetter());
			request.setActualPayee(detail.getActualPayee());
			request.setLetterBankCardNumber(detail.getLetterBankCardNumber());
			request.setLetterInterbankNumber(detail.getLetterInterbankNumber());
			request.setLetterBankInstitution(detail.getLetterBankInstitution());
			request.setLivableNumber(detail.getLivableNumber());
			request.setLivedNumber(detail.getLivedNumber());
			request.setMonthlyRentFee(detail.getMonthlyRentFee());
			request.setPeriodInMonth(detail.getPeriodInMonth());
			request.setNextPayDate(DateTimeUtility.parseYYYYMMDD(detail.getNextPayDate()));
			request.setNote(detail.getNote());
			request.setOperatorBankCardNumber(detail.getOperatorBankCardNumber());
			request.setOperatorInterbankNumber(detail.getOperatorInterbankNumber());
			request.setOperatorCode(detail.getOperatorCode());
			request.setOperatorName(detail.getOperatorName());
			request.setRentDepositNumber(detail.getRentDepositNumber());
			request.setRentPaymentNumber(detail.getRentPaymentNumber());
			request.setUsageTypeCode(detail.getHousingUsageTypeCode());
			request.setUsageTypeName(detail.getHousingUsageTypeName());


			// 创建人、付款人信息
			String createdByCode = (String)processVariables.get(ProcessConstants.KEY_CREATED_BY_CODE);
			Date createTime = (Date)processVariables.get(ProcessConstants.KEY_CREATE_TIME);
			String createdByName = (String)processVariables.get(ProcessConstants.KEY_CREATED_BY_NAME);
			String paidByCode = (String)processVariables.get(ProcessConstants.KEY_PAID_BY_CODE);
			Date payTime = (Date)processVariables.get(ProcessConstants.KEY_PAY_TIME);

			Integer reimburseId = (Integer)processVariables.get(ProcessConstants.KEY_REIMBURSE_ID);
			request.setReimburseId(reimburseId);

			EmployeeSO employee = employeeHandler.getEmployeeByCode(paidByCode);
			String paidByName = employee.getFullName();
			request.setCreatedByCode(createdByCode);
			request.setCreatedByName(createdByName);
			request.setCreateTime(createTime);
			
			List<HousingContractAttachmentVO> attachments = detail.getHousingContractAttachments();
			if (attachments != null && attachments.size() > 0) {
				List<CreateHousingContractAttachment> createHousingContractAttachments = new ArrayList<CreateHousingContractAttachment>();
				for (int i = 0; i < attachments.size(); i++ ) {
					CreateHousingContractAttachment createHousingContractAttachment = new CreateHousingContractAttachment();
					createHousingContractAttachment.setSequence(attachments.get(i).getSequence());
					createHousingContractAttachment.setUrl(attachments.get(i).getAttachmentUrl());
					createHousingContractAttachment.setOrigUrl(attachments.get(i).getOrigAttachmentUrl());
					
					createHousingContractAttachments.add(createHousingContractAttachment);
				}
				
				request.setHousingContractAttachments(createHousingContractAttachments);
			}
			
			List<HousingDepositVO> deposits = detail.getHousingDeposits();
			if (deposits != null && deposits.size() > 0) {
				List<CreateHousingDeposit> createHousingDeposits = new ArrayList<CreateHousingDeposit>();
				for (int i = 0; i < deposits.size(); i++ ) {
					CreateHousingDeposit createHousingDeposit = new CreateHousingDeposit();
					createHousingDeposit.setAmount(deposits.get(i).getAmount());
					createHousingDeposit.setHousingDepositType(deposits.get(i).getHousingDepositTypeCode());
					createHousingDeposit.setIsTurnoverd(false);
					createHousingDeposit.setNote(deposits.get(i).getNote());
					createHousingDeposit.setActualPaymentTime(DateTimeUtility.parseYYYYMMDD(detail.getActualPaymentDate()));
					createHousingDeposit.setEffectiveDate(createTime);
					createHousingDeposit.setAttachmentUrl(deposits.get(i).getAttachmentUrl());
					createHousingDeposit.setOrigAttachmentUrl(deposits.get(i).getOrigAttachmentUrl());
					createHousingDeposit.setPaidByCode(paidByCode);
					createHousingDeposit.setPaidByName(paidByName);
					createHousingDeposit.setPayTime(payTime);
					createHousingDeposit.setCreatedByCode(createdByCode);
					createHousingDeposit.setCreatedByName(createdByName);
					createHousingDeposit.setCreateTime(createTime);
					
					createHousingDeposits.add(createHousingDeposit);
				}
				
				request.setHousingDeposits(createHousingDeposits);
			}
			
			// 调用delivery api
			housingContractHandler.createHousingContract(request);
		}
		
		messageHandler.notifyApplierPass(execution);
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		LOG.debug(">>>>HousingContractCheckin deny!");
		messageHandler.notifyApplierNotPass(execution);
		
	}

	@Override
	public String getProcessDefinitionKey() {
		return HousingContractCreationConstants.PROCESS_DEFINITION_KEY;
	}

}
