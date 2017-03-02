package com.i360r.bpm.service.rs.process.fixedasset.citydistributeunchecked;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.fixedasset.api.IFixedAssetHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.fixedasset.ResponsiblePersonResponse;
import com.i360r.bpm.service.rs.process.fixedasset.businessareatransferout.FixedAssetBusinessAreaTransferOutConstants;
import com.i360r.bpm.service.rs.process.fixedasset.citydistributeunchecked.api.FixedAssetCityDistributeUncheckedDetailVO;
import com.i360r.bpm.service.rs.process.fixedasset.citydistributeunchecked.api.FixedAssetCityDistributeUncheckedNumbers;
import com.i360r.bpm.service.rs.process.fixedasset.citydistributeunchecked.api.FixedAssetCityDistributeUncheckedRequest;
import com.i360r.bpm.service.rs.process.fixedasset.citydistributeunchecked.api.IFixedAssetCityDistributeUncheckedService;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.util.StringUtility;
import com.i360r.oas.api.internal.rs.inventory.FrozenInventoryCountRequest;

public class FixedAssetCityDistributeUncheckedService implements
		IFixedAssetCityDistributeUncheckedService {

	@Autowired
	private IEmployeeHandler employeeHandler;
	
	@Autowired
	private IProcessHandler processHandler;
	
	@Autowired
	private IFixedAssetHandler fixedAssetHandler;
	
	@Override
	public ServiceResponse startProcess(FixedAssetCityDistributeUncheckedRequest request) throws Exception {
		//验证资产是否非法调拨
		//城市库只能分配到该城市下的站点库
		fixedAssetHandler.validateFixedAssetTransferOut(request.getFromLocationCode(), request.getToLocationCode(), FixedAssetCityDistributeUncheckedConstants.PROCESS_DEFINITION_KEY);
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		Map<String, Object> customVariables = new HashMap<String, Object>();

		//判断是否可以改变编号
		boolean changeNumber = false;
		if(	StringUtils.isBlank(request.getNumber())){
			changeNumber = true;
		}
		customVariables.put("changeNumber",changeNumber);
		customVariables.put(FixedAssetBusinessAreaTransferOutConstants.FROM_LOCATION_CODE,request.getFromLocationCode());
		customVariables.put(FixedAssetBusinessAreaTransferOutConstants.TO_LOCATION_CODE, request.getToLocationCode());
		customVariables.put(ProcessConstants.KEY_LOCATION_CODE, request.getToLocationCode());
		
		
		//检查是否有责任人
		fixedAssetHandler.getEmployeePosition(request.getToLocationCode());
		//验证分配数量小于数据库可分配的数量
		fixedAssetHandler.validateFrozenInventoryCount(StringUtility.toInteger(request.getCityInventoryId()), request.getInventoryType(), request.getDistributedCount());
		
		processHandler.startProcess(employee, request, FixedAssetCityDistributeUncheckedConstants.PROCESS_DEFINITION_KEY, customVariables, AccountType.EMPLOYEE);
 
		//流程发起成功后，冻结出库数量
		FrozenInventoryCountRequest frozenInventoryCountRequest = new FrozenInventoryCountRequest();
		
		frozenInventoryCountRequest.setFrozenCount(request.getDistributedCount());
		frozenInventoryCountRequest.setFrozenResult(true);
		frozenInventoryCountRequest.setId(StringUtility.toInteger(request.getCityInventoryId()));
		frozenInventoryCountRequest.setInventoryType(request.getInventoryType());
		
		fixedAssetHandler.updateInventoryByFrozenCount(frozenInventoryCountRequest);
		return new ServiceResponse();
	}

	
	@Override
	public ServiceResponse stationManagerApprove(
			FixedAssetCityDistributeUncheckedNumbers request) {
		
		processHandler.completeTask(request, FixedAssetCityDistributeUncheckedConstants.KEY_DELIVERY_STATION_MANAGER_APPROVED);
		
		return new ServiceResponse();
	}

	@Override
	public ProcessDetailResponse<FixedAssetCityDistributeUncheckedDetailVO> getDetail(
			String processInstanceId) {
		return processHandler.getProcessDetail(processInstanceId, FixedAssetCityDistributeUncheckedDetailVO.class);
	}

	@Override
	public ResponsiblePersonResponse getResponsiblePerson(String locationCode) throws Exception {
		Map<String, String > employeePosition = fixedAssetHandler.getEmployeePosition(locationCode);
		ResponsiblePersonResponse response = new ResponsiblePersonResponse();
		Entry<String, String> entry = employeePosition.entrySet().iterator().next();
		response.setResponsiblePersonCode(entry.getKey());
		response.setResponsiblePersonName(entry.getValue());
		return response;
	}
	
}
