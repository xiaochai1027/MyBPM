package com.i360r.bpm.service.rs.process.fixedasset.businessareatransferout;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.fixedasset.api.IFixedAssetHandler;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.fixedasset.ResponsiblePersonResponse;
import com.i360r.bpm.service.rs.process.fixedasset.businessareatransferout.api.FixedAssetBusinessAreaTransferOutDetailVO;
import com.i360r.bpm.service.rs.process.fixedasset.businessareatransferout.api.FixedAssetBusinessAreaTransferOutRequest;
import com.i360r.bpm.service.rs.process.fixedasset.businessareatransferout.api.IFixedAssetBusinessAreaTransferOutService;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.util.StringUtility;
import com.i360r.oas.api.internal.rs.inventory.FrozenInventoryCountRequest;

public class FixedAssetBusinessAreaTransferOutService implements
		IFixedAssetBusinessAreaTransferOutService {

	@Autowired
	private IProcessHandler processHandler;
	
	@Autowired
	private IFixedAssetHandler fixedAssetHandler;	
	
	@Override
	public ServiceResponse startProcess(FixedAssetBusinessAreaTransferOutRequest request)
			throws Exception {
		//验证资产是否非法调拨
		//1.站点只能调出到该站点所在的城市
		//2.站点只能调出到该站点所在的城市下的其他站点
		fixedAssetHandler.validateFixedAssetTransferOut(request.getFromLocationCode(), request.getToLocationCode(), FixedAssetBusinessAreaTransferOutConstants.PROCESS_DEFINITION_KEY);
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		Map<String, Object> customVariables = new HashMap<String, Object>();
		customVariables.put(FixedAssetBusinessAreaTransferOutConstants.KEY_IS_TO_BUSINESS_AREA, request.getIsBusinessAreaToBusinessArea());
		customVariables.put(FixedAssetBusinessAreaTransferOutConstants.FROM_LOCATION_CODE,request.getFromLocationCode());
		customVariables.put(FixedAssetBusinessAreaTransferOutConstants.TO_LOCATION_CODE, request.getToLocationCode());
		
		//检查是否有责任人
		fixedAssetHandler.getEmployeePosition(request.getToLocationCode());
		//验证出库数量是否大于现有的数据库的数量,验证冻结数量
		fixedAssetHandler.validateFrozenInventoryCount(Integer.parseInt(request.getBusinessAreaInventoryId()), request.getInventoryType(),Integer.parseInt(request.getTransferOutCount()));
		
		processHandler.startProcess(employee, request, FixedAssetBusinessAreaTransferOutConstants.PROCESS_DEFINITION_KEY, AccountType.EMPLOYEE);
		
		//流程发起成功后，冻结出库数量
		FrozenInventoryCountRequest frozenInventoryCountRequest = new FrozenInventoryCountRequest();
		frozenInventoryCountRequest.setFrozenCount(StringUtility.toInteger(request.getTransferOutCount()));
		frozenInventoryCountRequest.setFrozenResult(true);
		frozenInventoryCountRequest.setId(StringUtility.toInteger(request.getBusinessAreaInventoryId()));
		frozenInventoryCountRequest.setInventoryType(request.getInventoryType());
		fixedAssetHandler.updateInventoryByFrozenCount(frozenInventoryCountRequest);
	
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse adminSpecialistApprove(ApproveRequest request) {

		processHandler.completeTask(request, FixedAssetBusinessAreaTransferOutConstants.KEY_ADMIN_SPECIALIST_APPROVED);
		
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse goalAdminSpecialistApprove(ApproveRequest request) {
		
		processHandler.completeTask(request, FixedAssetBusinessAreaTransferOutConstants.KEY_GOAL_ADMIN_SPECIALIST_APPROVED);
		
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse goalCityStationManagerApprove(ApproveRequest request) {
		
		processHandler.completeTask(request, FixedAssetBusinessAreaTransferOutConstants.KEY_GOAL_CITY_STATION_MANAGER_APPROVED);
		
		return new ServiceResponse();
	}

	@Override
	public ProcessDetailResponse<FixedAssetBusinessAreaTransferOutDetailVO> getDetail(
			String processInstanceId) {
		
		return processHandler.getProcessDetail(processInstanceId, FixedAssetBusinessAreaTransferOutDetailVO.class);
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
