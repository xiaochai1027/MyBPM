package com.i360r.bpm.business.handler.fixedasset.api;

import java.util.Map;

import com.i360r.oas.api.internal.rs.inventory.BusinessAreaInventoryScrapRequest;
import com.i360r.oas.api.internal.rs.inventory.BusinessAreaInventoryTransferOutRequest;
import com.i360r.oas.api.internal.rs.inventory.CityInventoryTransferOutRequest;
import com.i360r.oas.api.internal.rs.inventory.DistributeBusinessAreaInventoryRequest;
import com.i360r.oas.api.internal.rs.inventory.DistributeCityInventoryFixedAssetRequest;
import com.i360r.oas.api.internal.rs.inventory.DistributeNationalFixedAssetRequest;
import com.i360r.oas.api.internal.rs.inventory.FrozenInventoryCountRequest;


public interface IFixedAssetHandler {

	//配送员入职分发商圈库存资产
	public void distributeBusinessAreaInventory(DistributeBusinessAreaInventoryRequest reqeust) throws Exception;

	/*
	* 城市库存资产分配
	*/
	public void distributeCityInventoryFixedAsset(DistributeCityInventoryFixedAssetRequest request) throws Exception;
	
	/*
	* 全国库存资产分配
	*/
	public void distributeNationalInventoryFixedAsset(DistributeNationalFixedAssetRequest request) throws Exception;
	
	/*
	*城市库存出库 
	*/
	public void transferOutCityInventory(CityInventoryTransferOutRequest reqeust) throws Exception;
	
	/*
	*商圈库存出库 
	*/
	public void transferOutBusinessAreaInventory(BusinessAreaInventoryTransferOutRequest reqeust) throws Exception;
	
	/*
	*商圈库存报废 
	*/
	public void scrapBusinessAreaInventory(BusinessAreaInventoryScrapRequest reqeust) throws Exception;
	
	
	/*
	*流程发起成功后， 冻结数量
	*/
	public void updateInventoryByFrozenCount(FrozenInventoryCountRequest request) throws Exception;
	
	
	/*
	*验证冻结数量
	*/
	public void validateFrozenInventoryCount(int id,String inventoryType,int frozenCount) throws Exception;
	
	/*
	*查找责任人
	*/
	public Map<String, String> getEmployeePosition(String locationCode) throws Exception;
	
	/*
	*验证资产调拨
	*/
	public void validateFixedAssetTransferOut(String fromLocationCode, String toLocationCode, String processDefinitionKey);
	
}
