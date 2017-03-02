package com.i360r.bpm.business.handler.businessarea.fund.result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;

import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.SpendBeforeReimburseConstants;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.api.SpendBeforeReimburseDetailVO;
import com.i360r.bpm.service.rs.process.spendBeforeReimburse.api.SpendBeforeReimburseItemVO;
import com.i360r.oas.api.internal.rs.businessarea.fund.AbstractBusinessAreaFundRequest;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateReimburseItem;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateReimburseRequest;

public class SpendBeforeReimburseResultHandler extends AbstractBusinessAreaFundResultHandler implements IProcessResultHandler {
	
	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		SpendBeforeReimburseDetailVO detail = ProcessUtility.getProcessObject(processVariables, SpendBeforeReimburseDetailVO.class);
		
		CreateReimburseRequest request = new CreateReimburseRequest();
		request = setCreateOrPay(request, execution, processVariables);
		
		request.setBusinessAreaCode(detail.getBusinessAreaCode());
		request.setNote(detail.getNote());
		request.setReimburseItems(toVOFromSpendBeforeReimburse(detail.getApprovedItems()));
		request.setTotalAmount(detail.getTotalAmount());
		request.setActualPaymentTime(detail.getActualPaymentTime());
		request.setBankAccountName(detail.getBankAccountName());
		request.setBankAccountNumber(detail.getBankAccountNumber());
		
		getBusinessAreaFundManageHandler().createReimburse(request);
		
		getMessageHandler().notifyApplierPass(execution);
	}
	
	@Override
	public <T extends AbstractBusinessAreaFundRequest> T setCreateOrPay(T request, DelegateExecution execution, Map<String, Object> processVariables) {
		
		request = super.setCreateOrPay(request, execution, processVariables);
		
		request.setCreateTime(new Date()); // 取系统当前时间
		
		return request;
	}
	
	private List<CreateReimburseItem> toVOFromSpendBeforeReimburse(List<SpendBeforeReimburseItemVO> reimburseItemVOs) {
		List<CreateReimburseItem> items = new ArrayList<CreateReimburseItem>();
		
		if (reimburseItemVOs != null
				&& reimburseItemVOs.size() > 0) {
			for (SpendBeforeReimburseItemVO reimburseItemVO : reimburseItemVOs) {
				items.add(toVO(reimburseItemVO));
			}
		}
		
		return items;
	}
	
	private CreateReimburseItem toVO(SpendBeforeReimburseItemVO reimburseItemVO) {
		CreateReimburseItem item = new CreateReimburseItem();
		
		item.setAmount(reimburseItemVO.getAmount());
		item.setAttachmentUrl(reimburseItemVO.getAttachmentUrl());
		item.setOrigAttachmentUrl(reimburseItemVO.getOrigAttachmentUrl());
		item.setExcelAttachmentUrl(reimburseItemVO.getExcelAttachmentUrl());
		item.setBeginTime(reimburseItemVO.getBeginTime());
		item.setEndTime(reimburseItemVO.getEndTime());
		item.setNote(reimburseItemVO.getNote());
		item.setReimburseSubTypeCode(reimburseItemVO.getReimburseSubTypeCode());
		
		return item;
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		getMessageHandler().notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		return SpendBeforeReimburseConstants.PROCESS_DEFINITION_KEY;
	}

}
