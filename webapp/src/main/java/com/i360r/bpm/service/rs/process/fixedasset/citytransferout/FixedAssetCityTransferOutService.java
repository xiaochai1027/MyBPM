package com.i360r.bpm.service.rs.process.fixedasset.citytransferout;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.employee.EmployeeHandler;
import com.i360r.bpm.business.handler.fixedasset.api.IFixedAssetHandler;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.fixedasset.ResponsiblePersonResponse;
import com.i360r.bpm.service.rs.process.fixedasset.citytransferout.api.FixedAssetCitytTransferOutDetailVO;
import com.i360r.bpm.service.rs.process.fixedasset.citytransferout.api.FixedAssetCitytTransferOutRequest;
import com.i360r.bpm.service.rs.process.fixedasset.citytransferout.api.IFixedAssetCityTransferOutService;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.util.StringUtility;
import com.i360r.oas.api.internal.rs.inventory.FrozenInventoryCountRequest;

public class FixedAssetCityTransferOutService implements
		IFixedAssetCityTransferOutService {

	@Autowired
	private EmployeeHandler employeeHandler;
	
	@Autowired
	private IProcessHandler processHandler;
	
	@Autowired
	private IFixedAssetHandler fixedAssetHandler;
	
	@Override
	public ServiceResponse startProcess(
			FixedAssetCitytTransferOutRequest request) throws Exception {
		
		//验证资产是否非法调拨
		//城市库不能调出到自己的城市
		fixedAssetHandler.validateFixedAssetTransferOut(request.getFromLocationCode(), request.getToLocationCode(), FixedAssetCityTransferOutConstants.PROCESS_DEFINITION_KEY);
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		Map<String, Object> customVariables = new HashMap<String, Object>();
		
		customVariables.put(FixedAssetCityTransferOutConstants.FROM_LOCATION_CODE,request.getFromLocationCode());
		customVariables.put(FixedAssetCityTransferOutConstants.TO_LOCATION_CODE, request.getToLocationCode());
		customVariables.put(FixedAssetCityTransferOutConstants.KEY_IS_CITY_TO_CITY, request.getIsCityToCity());

		//检查是否有责任人
		fixedAssetHandler.getEmployeePosition(request.getToLocationCode());
		//验证出库数量是否大于现有的数据库的数量
		fixedAssetHandler.validateFrozenInventoryCount(Integer.parseInt(request.getCityInventoryId()),request.getInventoryType(), Integer.parseInt(request.getTransferOutCount()));
		
		processHandler.startProcess(employee, request, FixedAssetCityTransferOutConstants.PROCESS_DEFINITION_KEY, customVariables, AccountType.EMPLOYEE);
		
		//流程发起成功后，冻结出库数量
		FrozenInventoryCountRequest frozenInventoryCountRequest = new FrozenInventoryCountRequest();
		
		frozenInventoryCountRequest.setFrozenCount(StringUtility.toInteger(request.getTransferOutCount()));
		frozenInventoryCountRequest.setFrozenResult(true);
		frozenInventoryCountRequest.setId(StringUtility.toInteger(request.getCityInventoryId()));
		frozenInventoryCountRequest.setInventoryType(request.getInventoryType());
		
		fixedAssetHandler.updateInventoryByFrozenCount(frozenInventoryCountRequest);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse directManagementCityManagerApprove(
			ApproveRequest request) {
		processHandler.completeTask(request, FixedAssetCityTransferOutConstants.KEY_DIRECT_MANAGERMENT_CITY_MANAGER_APPROVED);
		return new ServiceResponse();
	}


	@Override
	public ServiceResponse goalDirectManagementCityManagerApprove(
			ApproveRequest request) {
		processHandler.completeTask(request, FixedAssetCityTransferOutConstants.KEY_DIRECT_MANAGERMENT_CITY_MANAGER_APPROVED);
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse directManagementAdminManagerApprove(
			ApproveRequest request) {
		processHandler.completeTask(request, FixedAssetCityTransferOutConstants.KEY_DIRECT_MANAGERMENT_ADMIN_MANAGER_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse adminSpecialistApprove(ApproveRequest request) {
		processHandler.completeTask(request, FixedAssetCityTransferOutConstants.KEY_GOAL_ADMIN_SPECIALIST_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ProcessDetailResponse<FixedAssetCitytTransferOutDetailVO> getDetail(
			String processInstanceId) {
		return processHandler.getProcessDetail(processInstanceId, FixedAssetCitytTransferOutDetailVO.class);
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
