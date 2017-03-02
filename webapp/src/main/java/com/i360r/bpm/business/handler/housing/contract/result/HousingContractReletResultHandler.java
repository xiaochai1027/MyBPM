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
import com.i360r.bpm.service.rs.process.housing.relet.api.HousingContractAttachmentVO;
import com.i360r.bpm.service.rs.process.housing.relet.api.HousingContractReletConstants;
import com.i360r.bpm.service.rs.process.housing.relet.api.HousingContractReletVO;
import com.i360r.framework.log.Log;
import com.i360r.oas.api.internal.rs.house.CreateHousingContractAttachment;
import com.i360r.oas.api.internal.rs.house.ReletHousingContractRequest;

public class HousingContractReletResultHandler extends AbstractEngineHandler implements IProcessResultHandler {

	private static final Log LOG = Log.getLog(HousingContractReletResultHandler.class);
	
	@Autowired
	private IHousingContractHandler housingContractHandler;
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@Autowired
	protected IEmployeeHandler employeeHandler;
	
	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		LOG.debug(">>>>HousingContract Relet approved!");
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		Boolean houseRentConfirmed =  (Boolean)processVariables.get(HousingContractReletConstants.TASK_HOUSE_RENT_CONFIRMED);

		if (houseRentConfirmed) {
			HousingContractReletVO detail = ProcessUtility.getProcessObject(processVariables, HousingContractReletVO.class);
			
			ReletHousingContractRequest request = new ReletHousingContractRequest();
			
			request.setOldContractId(detail.getOldContractId());
			request.setAddress(detail.getAddress());
			request.setAgentCommissionFee(detail.getAgentCommissionFee());
			request.setAreaSize(detail.getAreaSize());
			request.setBusinessAreaCode(detail.getBusinessAreaCode());
			request.setBusinessAreaName(detail.getBusinessAreaName());
			request.setCityCode(detail.getCityCode());
			request.setCityName(detail.getCityName());
			request.setEffectiveEndDate(DateTimeUtility.parseYYYYMMDD(detail.getEffectiveEndDate()));
			request.setEffectiveStartDate(DateTimeUtility.parseYYYYMMDD(detail.getEffectiveStartDate()));
			request.setIsLastPay(detail.getLastPay());
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

			Integer reimburseId = (Integer)processVariables.get(ProcessConstants.KEY_REIMBURSE_ID);
			request.setReimburseId(reimburseId);

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
			
			// 调用oas api
			housingContractHandler.reletHousingContract(request);
		}
		
		messageHandler.notifyApplierPass(execution);
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		LOG.debug(">>>>HousingContractRelet deny!");
		messageHandler.notifyApplierNotPass(execution);
		
	}

	@Override
	public String getProcessDefinitionKey() {
		return HousingContractReletConstants.PROCESS_DEFINITION_KEY;
	}

}
