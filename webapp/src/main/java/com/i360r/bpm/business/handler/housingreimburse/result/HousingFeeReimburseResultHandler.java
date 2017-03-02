package com.i360r.bpm.business.handler.housingreimburse.result;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.housingreimburse.api.IHousingReimburseHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.housingfeereimburse.HousingFeeReimburseConstants;
import com.i360r.bpm.service.rs.process.housingfeereimburse.api.HousingFeeReimburseDetailVO;
import com.i360r.bpm.service.rs.process.housingfeereimburse.api.HousingFeeReimburseItemVO;
import com.i360r.cds.api.internal.rs.employee.EmployeeSO;
import com.i360r.framework.log.Log;
import com.i360r.oas.api.internal.rs.house.CreateHousingFeeReimburseRequest;
import com.i360r.oas.api.internal.rs.house.HousingFeeReimburseItemSO;

public class HousingFeeReimburseResultHandler extends AbstractEngineHandler  implements IProcessResultHandler {

	private static final Log LOG = Log.getLog(HousingFeeReimburseResultHandler.class);
	
	
	@Autowired
	private IHousingReimburseHandler housingReimburseHandler;
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@Autowired
	protected IEmployeeHandler employeeHandler;
	
	
	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		
		LOG.info(">>>HousingFeeReimburse Pass!");
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());

		HousingFeeReimburseDetailVO detail = ProcessUtility.getProcessObject(processVariables, HousingFeeReimburseDetailVO.class);
		
		CreateHousingFeeReimburseRequest request = new CreateHousingFeeReimburseRequest();
		
		request.setNote(detail.getNote());
		request.setActualPaymentDate(detail.getActualPaymentDate());
		request.setTotalAmount(detail.getTotalAmount());
		request.setBankAccountName(detail.getBankAccountName());
		request.setBankAccountNumber(detail.getBankAccountNumber());
		
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
		
		// 报销项
		List<HousingFeeReimburseItemSO> items = new ArrayList<HousingFeeReimburseItemSO>();
		Map<String, List<HousingFeeReimburseItemSO>> map = new HashMap<String, List<HousingFeeReimburseItemSO>>();
		for (HousingFeeReimburseItemVO reimburseItemVO : detail.getApprovedItems()) {
			HousingFeeReimburseItemSO itemSO = new HousingFeeReimburseItemSO();
			
			itemSO.setAmount(reimburseItemVO.getAmount());
			itemSO.setAttachmentUrl(reimburseItemVO.getAttachmentUrl());
			itemSO.setOrigAttachmentUrl(reimburseItemVO.getOrigAttachmentUrl());
			itemSO.setBeginDate(reimburseItemVO.getBeginDate());
			itemSO.setEndDate(reimburseItemVO.getEndDate());
			itemSO.setNote(reimburseItemVO.getNote());
			itemSO.setSubTypeCode(reimburseItemVO.getSubTypeCode());
			itemSO.setPayTypeCode(reimburseItemVO.getPayTypeCode());
			itemSO.setPayTypeName(reimburseItemVO.getPayTypeName());
			itemSO.setScale(reimburseItemVO.getScale());
			itemSO.setUnitPrice(reimburseItemVO.getUnitPrice());
			
			//房屋合同2.2兼容以前的版本 16年10月
			if (StringUtils.isNotBlank(detail.getContractId())) {
				reimburseItemVO.setContractId(detail.getContractId());
			}
							
			items = map.get(reimburseItemVO.getContractId());			
			if (CollectionUtils.isEmpty(items)) {
				items = new ArrayList<HousingFeeReimburseItemSO>();
				map.put(reimburseItemVO.getContractId(), items);
			}
			
			items.add(itemSO);
		}
		
		for (Map.Entry<String, List<HousingFeeReimburseItemSO>> entry : map.entrySet()) {
			request.setContractId(entry.getKey());
			request.setItmes(entry.getValue());
			housingReimburseHandler.createHousingFeeReimburse(request);
		}	
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		
		LOG.info(">>>HousingFeeReimburse deny!");
		
		messageHandler.notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		
		return HousingFeeReimburseConstants.PROCESS_DEFINITION_KEY;
	}

}
