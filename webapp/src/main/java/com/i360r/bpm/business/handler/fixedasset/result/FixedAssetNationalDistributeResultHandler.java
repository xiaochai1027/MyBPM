package com.i360r.bpm.business.handler.fixedasset.result;

import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.fixedasset.api.IFixedAssetHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.fixedasset.nationaldistribute.FixedAssetNationalDistributeConstants;
import com.i360r.bpm.service.rs.process.fixedasset.nationaldistribute.api.FixedAssetNationalDistributeDetailVO;
import com.i360r.framework.log.Log;
import com.i360r.framework.util.StringUtility;
import com.i360r.oas.api.internal.rs.inventory.DistributeNationalFixedAssetRequest;
import com.i360r.oas.api.internal.rs.inventory.FrozenInventoryCountRequest;

public class FixedAssetNationalDistributeResultHandler extends AbstractEngineHandler implements IProcessResultHandler {
	private static final Log LOG = Log.getLog(FixedAssetNationalDistributeResultHandler.class);

	@Autowired
	private IFixedAssetHandler fixedAssetHandler;
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		LOG.debug(">>>>FixedAssetNationalDistribute approved!");
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());

		FixedAssetNationalDistributeDetailVO detail = ProcessUtility.getProcessObject(processVariables, FixedAssetNationalDistributeDetailVO.class);
		
		DistributeNationalFixedAssetRequest request = new DistributeNationalFixedAssetRequest();
		request.setCount(detail.getDistributedCount());
		request.setFromLocationCode(detail.getFromLocationCode());
		request.setFromLocationName(detail.getFromLocationName());
		request.setNationalInventoryId(StringUtility.toInteger(detail.getNationalInventoryId()));
		if(detail.getNumber() != null){
			request.setNumber(detail.getNumber());
		}
		if(detail.getRemark() != null){
			request.setInRemark(detail.getRemark());
		}
		request.setToLocationCode(detail.getToLocationCode());
		request.setToLocationName(detail.getToLocationName());
		request.setResponsiblePersonCode(detail.getResponsiblePersonCode());
		request.setResponsiblePersonName(detail.getResponsiblePersonName());
		
		// 调用delivery api
		fixedAssetHandler.distributeNationalInventoryFixedAsset(request);
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		//审批不通过，解除冻结的数量
		LOG.debug(">>>>FixedAssetNationalDistribute deny!");
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());

		FixedAssetNationalDistributeDetailVO detail = ProcessUtility.getProcessObject(processVariables, FixedAssetNationalDistributeDetailVO.class);
		FrozenInventoryCountRequest frozenInventoryCountRequest = new FrozenInventoryCountRequest();
		
		frozenInventoryCountRequest.setFrozenCount(detail.getDistributedCount());
		frozenInventoryCountRequest.setFrozenResult(false);
		frozenInventoryCountRequest.setId(StringUtility.toInteger(detail.getNationalInventoryId()));
		frozenInventoryCountRequest.setInventoryType(detail.getInventoryType());
		
		fixedAssetHandler.updateInventoryByFrozenCount(frozenInventoryCountRequest);
		messageHandler.notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		return FixedAssetNationalDistributeConstants.PROCESS_DEFINITION_KEY;
	}

}
