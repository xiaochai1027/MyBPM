package com.i360r.bpm.business.handler.fixedasset;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.i360r.bpm.business.exception.EntityNotExistException;
import com.i360r.bpm.business.exception.FixedAssetTransferOutException;
import com.i360r.bpm.business.handler.AbstractBusinessHandler;
import com.i360r.bpm.business.handler.fixedasset.api.IFixedAssetHandler;
import com.i360r.bpm.business.util.ServiceClientUtility;
import com.i360r.bpm.service.rs.process.fixedasset.businessareatransferout.FixedAssetBusinessAreaTransferOutConstants;
import com.i360r.bpm.service.rs.process.fixedasset.citydistribute.FixedAssetCityDistributeConstants;
import com.i360r.cds.api.internal.rs.employee.position.EmployeePositionsResponse;
import com.i360r.cds.api.internal.rs.employee.position.PositionCodeSO;
import com.i360r.cds.api.internal.rs.employee.position.SearchEmployeePositionRequest;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.log.Log;
import com.i360r.framework.util.CollectionUtility;
import com.i360r.framework.util.StringUtility;
import com.i360r.oas.api.internal.rs.inventory.BusinessAreaInventoryScrapRequest;
import com.i360r.oas.api.internal.rs.inventory.BusinessAreaInventoryTransferOutRequest;
import com.i360r.oas.api.internal.rs.inventory.CityInventoryTransferOutRequest;
import com.i360r.oas.api.internal.rs.inventory.DistributeBusinessAreaInventoryRequest;
import com.i360r.oas.api.internal.rs.inventory.DistributeCityInventoryFixedAssetRequest;
import com.i360r.oas.api.internal.rs.inventory.DistributeNationalFixedAssetRequest;
import com.i360r.oas.api.internal.rs.inventory.FrozenInventoryCountRequest;
import com.i360r.oas.api.internal.rs.inventory.IInventoryInternalService;


public class FixedAssetHandler extends AbstractBusinessHandler implements IFixedAssetHandler {

	private static final Log LOG = Log.getLog(FixedAssetHandler.class);
	
	private static final Integer NATIONAL_LOCATION_CODE_LENGTH = 2;
	private static final Integer CITY_LOCATION_CODE_LENGTH = 6;
	private static final Integer BUSINESS_AREA_LOCATION_CODE_LENGTH = 10;
	
	@Resource
	private IInventoryInternalService inventoryInternalServiceClient;
	
	@Override
	public void distributeBusinessAreaInventory(
			DistributeBusinessAreaInventoryRequest request) throws Exception {
		ServiceResponse response = null;
		try {
			response = inventoryInternalServiceClient.distributeBusinessAreaInventory(request);
		} catch (Exception e) {
			LOG.error("Distribute businessAreaInventory failed!", e);
			throw new Exception("Distribute businessAreaInventory failed!", e);
		}
		checkResponseSuccess(response);
	}

	@Override
	public void distributeCityInventoryFixedAsset(
			DistributeCityInventoryFixedAssetRequest request) throws Exception {
		ServiceResponse response = null;
		try {
			response = inventoryInternalServiceClient.distributeCityInventoryFixedAsset(request);
		} catch (Exception e) {
			LOG.error("Distribute CityInventoryFixedAsset failed!", e);
			throw new Exception("Distribute CityInventoryFixedAsset failed!", e);
		}
		checkResponseSuccess(response);
	}

	@Override
	public void distributeNationalInventoryFixedAsset(
			DistributeNationalFixedAssetRequest request) throws Exception {
		ServiceResponse response = null;
		try {
			response = inventoryInternalServiceClient.distributeNationalInventoryFixedAsset(request);
		} catch (Exception e) {
			LOG.error("Distribute NationalInventoryFixedAsset failed!", e);
			throw new Exception("Distribute NationalInventoryFixedAsset failed!", e);
		}
		checkResponseSuccess(response);		
	}

	@Override
	public void transferOutCityInventory(CityInventoryTransferOutRequest reqeust)
			throws Exception {
		ServiceResponse response = null;
		try {
			response = inventoryInternalServiceClient.transferOutCityInventory(reqeust);
		} catch (Exception e) {
			LOG.error(" transferOutCityInventory failed!", e);
			throw new Exception(" transferOutCityInventory failed!", e);
		}
		checkResponseSuccess(response);		
	}

	@Override
	public void transferOutBusinessAreaInventory(
			BusinessAreaInventoryTransferOutRequest request) throws Exception {
		ServiceResponse response = null;
		try {
			response = inventoryInternalServiceClient.transferOutBusinessAreaInventory(request);
		} catch (Exception e) {
			LOG.error(" transferOutBusinessAreaInventory failed!", e);
			throw new Exception(" transferOutBusinessAreaInventory failed!", e);
		}
		checkResponseSuccess(response);		
	}

	@Override
	public void scrapBusinessAreaInventory(
			BusinessAreaInventoryScrapRequest request) throws Exception {
		ServiceResponse response = null;
		try {
			response = inventoryInternalServiceClient.scrapBusinessAreaInventory(request);
		} catch (Exception e) {
			LOG.error("scrapBusinessAreaInventory failed!", e);
			throw new Exception("scrapBusinessAreaInventory failed!", e);
		}
		checkResponseSuccess(response);		
	}

	@Override
	public void updateInventoryByFrozenCount(FrozenInventoryCountRequest request)
			throws Exception {
		try {
			ServiceResponse response = inventoryInternalServiceClient.updateInventoryByFrozenCount(request);
			checkResponseSuccess(response);	
		} catch (Exception e) {
			LOG.error("Update InventoryByFrozenCount failed!", e);
			throw new Exception("Update InventoryByFrozenCount failed!", e);
		}
	
	}

	@Override
	public void validateFrozenInventoryCount(int id, String inventoryType, int frozenCount) throws Exception {
			ServiceResponse response = inventoryInternalServiceClient.validateFrozenInventoryCount(id, inventoryType, frozenCount);
			checkResponseSuccess(response);	
	}
	
	@Override
	public Map<String, String> getEmployeePosition(String locationCode) throws Exception{
		PositionCodeSO positionCodeSO = null;
		
		//如果locationCode.length() 为2则要查的是全国责任人 ，为6要查它的城市责任人 ，为10则要查的是商圈责任人。
		if(locationCode.length() == NATIONAL_LOCATION_CODE_LENGTH){
			positionCodeSO = PositionCodeSO.DIRECT_MANAGEMENT_ADMIN_MANAGER;
			
		}else if(locationCode.length() == CITY_LOCATION_CODE_LENGTH){
			positionCodeSO = PositionCodeSO.ADMIN_SPECIALIST;
			
		}else if(locationCode.length() == BUSINESS_AREA_LOCATION_CODE_LENGTH){
			positionCodeSO = PositionCodeSO.DELIVERY_STATION_MANAGER;
			
		}else {
			throw new EntityNotExistException(locationCode, "locationCode应该为2，6，10位，请核对，否者locationCode所对应的人员");
		}
		
		SearchEmployeePositionRequest request = new SearchEmployeePositionRequest();
		
		request.setLocationId(locationCode);
		request.setIncludeParentLocation(true);
		request.setPositionCode(positionCodeSO.getCode());
		request.setMasterPosition(true);
		EmployeePositionsResponse response = ServiceClientUtility.getEmployeePositionInternalService().searchSuperiorEmployeePosition(request);
		if (response == null || (response != null && CollectionUtility.isEmpty(response.getEmployeePositons()))) {
			if(positionCodeSO.equals(PositionCodeSO.DIRECT_MANAGEMENT_ADMIN_MANAGER)){
				throw new EntityNotExistException("全国：" + request.getLocationId(), "行政经理或他的上级");
			}else if(positionCodeSO.equals(PositionCodeSO.ADMIN_SPECIALIST)){
				throw new EntityNotExistException("城市：" + request.getLocationId(), "行政专员或他的上级");
			}else if(positionCodeSO.equals(PositionCodeSO.DELIVERY_STATION_MANAGER)){
				throw new EntityNotExistException("商圈：" + request.getLocationId(), "站长或他的上级");
			}
		}
		String employeePositionCode = response.getEmployeePositons().get(0).getId();
		String employeePositionName = response.getEmployeePositons().get(0).getEmployee().getFullName();
		Map<String, String> employeePosition = new HashMap<String, String>();
		employeePosition.put(employeePositionCode, employeePositionName);
		return employeePosition;
	}

	@Override
	public void validateFixedAssetTransferOut(String fromLocationCode,
			String toLocationCode, String type) {
		//验证同一站点库调拨
		if (StringUtility.equals(fromLocationCode, toLocationCode)) {
			throw new FixedAssetTransferOutException("fromLocationCode：" + fromLocationCode + "---toLocationCode:" + toLocationCode , "同库间不能调拨,");
		}
		//验证城市库分配，城市库只能分配到该城市下的站点库
		if(FixedAssetCityDistributeConstants.PROCESS_DEFINITION_KEY.equals(type)){
			if( fromLocationCode.length() != CITY_LOCATION_CODE_LENGTH){
				throw new FixedAssetTransferOutException("fromLocationCode：" + fromLocationCode + "---toLocationCode:" + toLocationCode , "fromLocationCode必须为城市Code,");
			}
			if(!fromLocationCode.equals(toLocationCode.substring(0, CITY_LOCATION_CODE_LENGTH))){
				throw new FixedAssetTransferOutException("fromLocationCode：" + fromLocationCode + "---toLocationCode:" + toLocationCode , "城市库只能分配到该城市下的站点库,");
			}
		}
		//验证站点库调出
		//1.站点只能调出到该站点所在的城市
		//2.站点只能调出到该站点所在的城市下的其他站点
		else if(FixedAssetBusinessAreaTransferOutConstants.PROCESS_DEFINITION_KEY.equals(type)){
			if(fromLocationCode.length() <= CITY_LOCATION_CODE_LENGTH){
				throw new FixedAssetTransferOutException("fromLocationCode：" + fromLocationCode + "---toLocationCode:" + toLocationCode , "fromLocationCode必须为站点Code,");
			}
			if(!fromLocationCode.substring(0, CITY_LOCATION_CODE_LENGTH).equals(toLocationCode.substring(0, CITY_LOCATION_CODE_LENGTH))){
				throw new FixedAssetTransferOutException("fromLocationCode：" + fromLocationCode + "---toLocationCode:" + toLocationCode , "站点只能调出到该站点所在的城市,以及所在城市的下的站点,");
			}
		}
	}
	
}
