package com.i360r.bpm.business.handler.businessarea.fund.result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;

import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.service.rs.process.cityReimburse.CityReimburseConstants;
import com.i360r.bpm.service.rs.process.cityReimburse.api.CityReimburseDetailVO;
import com.i360r.bpm.service.rs.process.cityReimburse.api.CityReimburseItemVO;
import com.i360r.oas.api.internal.rs.businessarea.fund.AbstractBusinessAreaFundRequest;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateReimburseItem;
import com.i360r.oas.api.internal.rs.businessarea.fund.CreateReimburseRequest;

public class CityReimburseResultHandler extends AbstractBusinessAreaFundResultHandler implements IProcessResultHandler {
	
	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		CityReimburseDetailVO detail = ProcessUtility.getProcessObject(processVariables, CityReimburseDetailVO.class);
		
		CreateReimburseRequest request = new CreateReimburseRequest();
		request = setCreateOrPay(request, execution, processVariables);
		
		request.setBusinessAreaCode(detail.getCityCode());
		request.setNote(detail.getNote());
		request.setReimburseItems(toVOFromCityReimburse(detail.getApprovedItems()));
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
	
	private List<CreateReimburseItem> toVOFromCityReimburse(List<CityReimburseItemVO> reimburseItemVOs) {
		List<CreateReimburseItem> items = new ArrayList<CreateReimburseItem>();
		
		if (reimburseItemVOs != null
				&& reimburseItemVOs.size() > 0) {
			for (CityReimburseItemVO reimburseItemVO : reimburseItemVOs) {
				items.add(toVO(reimburseItemVO));
			}
		}
		
		return items;
	}
	
	private CreateReimburseItem toVO(CityReimburseItemVO reimburseItemVO) {
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
		return CityReimburseConstants.PROCESS_DEFINITION_KEY;
	}

}
