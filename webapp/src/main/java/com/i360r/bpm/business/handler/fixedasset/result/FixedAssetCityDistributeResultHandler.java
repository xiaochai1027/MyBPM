package com.i360r.bpm.business.handler.fixedasset.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.fixedasset.api.IFixedAssetHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.fixedasset.citydistribute.FixedAssetCityDistributeConstants;
import com.i360r.bpm.service.rs.process.fixedasset.citydistribute.api.FixedAssetCityDistributeDetailVO;
import com.i360r.bpm.service.rs.process.fixedasset.citydistribute.api.FixedAssetDistributeNumberDetail;
import com.i360r.framework.log.Log;
import com.i360r.framework.util.StringUtility;
import com.i360r.oas.api.internal.rs.inventory.DistributeCityInventoryFixedAssetItem;
import com.i360r.oas.api.internal.rs.inventory.DistributeCityInventoryFixedAssetRequest;
import com.i360r.oas.api.internal.rs.inventory.FrozenInventoryCountRequest;

public class FixedAssetCityDistributeResultHandler extends AbstractEngineHandler implements IProcessResultHandler {

	private static final Log LOG = Log.getLog(FixedAssetCityDistributeResultHandler.class);
	

	@Autowired
	private IFixedAssetHandler fixedAssetHandler;
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		LOG.debug(">>>>FixedAssetCityDistribute approved!");
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());

		FixedAssetCityDistributeDetailVO detail = ProcessUtility.getProcessObject(processVariables, FixedAssetCityDistributeDetailVO.class);
		
		DistributeCityInventoryFixedAssetRequest request = new DistributeCityInventoryFixedAssetRequest();
		request.setCityInventoryId(StringUtility.toInteger(detail.getCityInventoryId()));
		request.setCount(detail.getDistributedCount());
		request.setFromLocationCode(detail.getFromLocationCode());
		request.setFromLocationName(detail.getFromLocationName());
		//该流程的资产要检查铭牌号
		request.setCheckNumber(true);
		
		//如果详情里的集合不为空，就把详情里的集合处理后存入接口集合中
		if(detail.getFixedAssetDistributeNumberDetails().size() != 0){
			request.setItems(this.getItems(detail.getFixedAssetDistributeNumberDetails()));
			request.setInHasNumber(true);
		}else {
			if(detail.getNumber() != null){
				DistributeCityInventoryFixedAssetItem item =new DistributeCityInventoryFixedAssetItem();
				List<DistributeCityInventoryFixedAssetItem> items =new ArrayList<DistributeCityInventoryFixedAssetItem>();	
				item.setNumber(detail.getNumber());
				if(detail.getFixedAssetPictureUrl() != null){
					item.setFixedAssetPictureUrl(detail.getFixedAssetPictureUrl());
				}
				if(detail.getOriginFixedAssetPictureUrl() != null){
					item.setOriginFixedAssetPictureUrl(detail.getOriginFixedAssetPictureUrl());
				}
				if(detail.getRemark() != null ){
					item.setInRemark(detail.getRemark());
				}
				items.add(item);
				request.setItems(items);
				request.setInHasNumber(true);
			}
		}
		request.setToLocationCode(detail.getToLocationCode());
		request.setToLocationName(detail.getToLocationName());
		request.setResponsiblePersonCode(detail.getResponsiblePersonCode());
		request.setResponsiblePersonName(detail.getResponsiblePersonName());
		// 调用delivery api
		fixedAssetHandler.distributeCityInventoryFixedAsset(request);
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		//审批不通过，解除冻结的数量
		LOG.debug(">>>>FixedAssetCityDistribute deny!");
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());

		FixedAssetCityDistributeDetailVO detail = ProcessUtility.getProcessObject(processVariables, FixedAssetCityDistributeDetailVO.class);
		
		FrozenInventoryCountRequest frozenInventoryCountRequest = new FrozenInventoryCountRequest();
		frozenInventoryCountRequest.setFrozenCount(detail.getDistributedCount());
		frozenInventoryCountRequest.setFrozenResult(false);
		frozenInventoryCountRequest.setId(StringUtility.toInteger(detail.getCityInventoryId()));
		frozenInventoryCountRequest.setInventoryType(detail.getInventoryType());
		fixedAssetHandler.updateInventoryByFrozenCount(frozenInventoryCountRequest);
		
		messageHandler.notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		return FixedAssetCityDistributeConstants.PROCESS_DEFINITION_KEY;
	}
	
	//把详情里的集合处理存入接口集合中
	private List<DistributeCityInventoryFixedAssetItem> getItems(List<FixedAssetDistributeNumberDetail> FixedAssetDistributeNumberDetails){
		List<DistributeCityInventoryFixedAssetItem> items = new ArrayList<DistributeCityInventoryFixedAssetItem>();
		
		for (FixedAssetDistributeNumberDetail vo : FixedAssetDistributeNumberDetails){
			DistributeCityInventoryFixedAssetItem item= new DistributeCityInventoryFixedAssetItem();
			item.setFixedAssetPictureUrl(vo.getFixedAssetNumberPictureUrl());
			item.setInRemark(vo.getRemark());
			item.setNumber(vo.getNumber());
			item.setOriginFixedAssetPictureUrl(vo.getOrigFixedAssetNumberPictureUrl());
			items.add(item);
		}
		return items;
	}

}
