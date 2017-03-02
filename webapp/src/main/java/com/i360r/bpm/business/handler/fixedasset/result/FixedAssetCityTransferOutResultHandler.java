package com.i360r.bpm.business.handler.fixedasset.result;

import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.fixedasset.api.IFixedAssetHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.fixedasset.citytransferout.FixedAssetCityTransferOutConstants;
import com.i360r.bpm.service.rs.process.fixedasset.citytransferout.api.FixedAssetCitytTransferOutDetailVO;
import com.i360r.framework.log.Log;
import com.i360r.framework.util.StringUtility;
import com.i360r.oas.api.internal.rs.inventory.CityInventoryTransferOutRequest;
import com.i360r.oas.api.internal.rs.inventory.FrozenInventoryCountRequest;

public class FixedAssetCityTransferOutResultHandler extends AbstractEngineHandler implements IProcessResultHandler {

	private static final Log LOG = Log.getLog(FixedAssetCityTransferOutResultHandler.class);

	@Autowired
	private IFixedAssetHandler fixedAssetHandler;
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		LOG.debug(">>>>FixedAssetCityTransferOut approved!");
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());

		FixedAssetCitytTransferOutDetailVO detail = ProcessUtility.getProcessObject(
				processVariables, FixedAssetCitytTransferOutDetailVO.class);
		
		CityInventoryTransferOutRequest request = new CityInventoryTransferOutRequest();
		
		request.setCityInventoryId(StringUtility.toInteger(detail.getCityInventoryId()));
		request.setCount(StringUtility.toInteger(detail.getTransferOutCount()));
		request.setFromLocationCode(detail.getFromLocationCode());
		request.setFromLocationName(detail.getFromLocationName());
		if(detail.getNumber() != null){
			request.setNumber(detail.getNumber());
		}
		if(detail.getRemark() != null){
			request.setRemark(detail.getRemark());
		}
		request.setToLocationCode(detail.getToLocationCode());
		request.setToLocationName(detail.getToLocationName());
		request.setResponsiblePersonCode(detail.getResponsiblePersonCode());
		request.setResponsiblePersonName(detail.getResponsiblePersonName());
		
		// 调用delivery api
		fixedAssetHandler.transferOutCityInventory(request);
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		// 审批不通过解除冻结数量
		LOG.debug(">>>>FixedAssetCityTransferOut deny!");
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());

		FixedAssetCitytTransferOutDetailVO detail = ProcessUtility.getProcessObject(processVariables, FixedAssetCitytTransferOutDetailVO.class);
		
		FrozenInventoryCountRequest frozenInventoryCountRequest = new FrozenInventoryCountRequest();
		
		frozenInventoryCountRequest.setFrozenCount(StringUtility.toInteger(detail.getTransferOutCount()));
		frozenInventoryCountRequest.setFrozenResult(false);
		frozenInventoryCountRequest.setId(StringUtility.toInteger(detail.getCityInventoryId()));
		frozenInventoryCountRequest.setInventoryType(detail.getInventoryType());
		
		fixedAssetHandler.updateInventoryByFrozenCount(frozenInventoryCountRequest);
		messageHandler.notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		return FixedAssetCityTransferOutConstants.PROCESS_DEFINITION_KEY;
	}

}
