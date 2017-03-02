package com.i360r.bpm.business.handler.fixedasset.result;

import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.fixedasset.api.IFixedAssetHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.fixedasset.scrap.FixedAssetScrapConstants;
import com.i360r.bpm.service.rs.process.fixedasset.scrap.api.FixedAssetScrapDetailVO;
import com.i360r.framework.log.Log;
import com.i360r.framework.util.StringUtility;
import com.i360r.oas.api.internal.rs.inventory.BusinessAreaInventoryScrapRequest;
import com.i360r.oas.api.internal.rs.inventory.FrozenInventoryCountRequest;

public class FixedAssetScrapResultHandler extends AbstractEngineHandler implements IProcessResultHandler {

	private static final Log LOG = Log.getLog(FixedAssetScrapResultHandler.class);
	
	@Autowired
	private IFixedAssetHandler fixedAssetHandler;
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		LOG.debug(">>>>FixedAssetScrap approved!");
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());

		FixedAssetScrapDetailVO detail = ProcessUtility.getProcessObject(
				processVariables, FixedAssetScrapDetailVO.class);
		
		BusinessAreaInventoryScrapRequest request = new BusinessAreaInventoryScrapRequest();
		request.setBusinessAreaInventoryId(StringUtility.toInteger(detail.getBusinessAreaInventoryId()));
		request.setCount(StringUtility.toInteger(detail.getScrapCount()));
		request.setOrigScrapPictureUrl(detail.getOrigScrapPictureUrl());
		request.setScrapPictureUrl(detail.getScrapPictureUrl());
		request.setScrapPrice(detail.getScrapPrice());
		if(detail.getRemark() !=null){
			request.setScrapRemark(detail.getRemark());
		}
		request.setScrapTypeCode(detail.getScrapTypeCode());

		// 调用delivery api
		fixedAssetHandler.scrapBusinessAreaInventory(request);
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		// 审批不通过解除冻结数量
		LOG.debug(">>>>FixedAssetScrap deny!");
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());

		FixedAssetScrapDetailVO detail = ProcessUtility.getProcessObject(processVariables, FixedAssetScrapDetailVO.class);
		FrozenInventoryCountRequest frozenInventoryCountRequest = new FrozenInventoryCountRequest();
		
		frozenInventoryCountRequest.setFrozenCount(StringUtility.toInteger(detail.getScrapCount()));
		frozenInventoryCountRequest.setFrozenResult(false);
		frozenInventoryCountRequest.setId(StringUtility.toInteger(detail.getBusinessAreaInventoryId()));
		frozenInventoryCountRequest.setInventoryType(detail.getInventoryType());
		
		fixedAssetHandler.updateInventoryByFrozenCount(frozenInventoryCountRequest);
		messageHandler.notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		return FixedAssetScrapConstants.PROCESS_DEFINITION_KEY;
	}

}
