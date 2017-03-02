package com.i360r.bpm.service.rs.process.fixedasset.nationaldistribute;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.fixedasset.api.IFixedAssetHandler;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.fixedasset.ResponsiblePersonResponse;
import com.i360r.bpm.service.rs.process.fixedasset.citytransferout.FixedAssetCityTransferOutConstants;
import com.i360r.bpm.service.rs.process.fixedasset.nationaldistribute.api.FixedAssetNationalDistributeDetailVO;
import com.i360r.bpm.service.rs.process.fixedasset.nationaldistribute.api.FixedAssetNationalDistributeRequest;
import com.i360r.bpm.service.rs.process.fixedasset.nationaldistribute.api.IFixedAssetNationalDistributeService;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.util.StringUtility;
import com.i360r.oas.api.internal.rs.inventory.FrozenInventoryCountRequest;

public class FixedAssetNationalDistributeService implements IFixedAssetNationalDistributeService {

	@Autowired
	private IEmployeeHandler employeeHandler;
	
	@Autowired
	private IProcessHandler processHandler;
	
	@Autowired
	private IFixedAssetHandler fixedAssetHandler;
	
	@Override
	public ServiceResponse startProcess(FixedAssetNationalDistributeRequest request) throws Exception {
		//验证资产是否非法调拨
		//全国库不能分配到全国库
		fixedAssetHandler.validateFixedAssetTransferOut(request.getFromLocationCode(), request.getToLocationCode(), FixedAssetNationalDistributeConstants.PROCESS_DEFINITION_KEY);
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		Map<String, Object> customVariables = new HashMap<String, Object>();
		
		customVariables.put(FixedAssetCityTransferOutConstants.FROM_LOCATION_CODE,request.getFromLocationCode());
		customVariables.put(FixedAssetCityTransferOutConstants.TO_LOCATION_CODE, request.getToLocationCode());
		
		//检查是否有责任人
		fixedAssetHandler.getEmployeePosition(request.getToLocationCode());
		//验证分配数量小于数据库可分配的数量
		fixedAssetHandler.validateFrozenInventoryCount(StringUtility.toInteger(request.getNationalInventoryId()), request.getInventoryType(), request.getDistributedCount());
		
		processHandler.startProcess(employee, request, FixedAssetNationalDistributeConstants.PROCESS_DEFINITION_KEY, customVariables, AccountType.EMPLOYEE);
 
		//流程发起成功后，冻结分配数量
		FrozenInventoryCountRequest frozenInventoryCountRequest = new FrozenInventoryCountRequest();
		
		frozenInventoryCountRequest.setFrozenCount(request.getDistributedCount());
		frozenInventoryCountRequest.setFrozenResult(true);
		frozenInventoryCountRequest.setId(StringUtility.toInteger(request.getNationalInventoryId()));
		frozenInventoryCountRequest.setInventoryType(request.getInventoryType());
		
		fixedAssetHandler.updateInventoryByFrozenCount(frozenInventoryCountRequest);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse adminSpecialistApprove(ApproveRequest request) {
		processHandler.completeTask(request, FixedAssetNationalDistributeConstants.KEY_ADMIN_SPECIALIST_APPROVED);
		
		return new ServiceResponse();
	}

	@Override
	public ProcessDetailResponse<FixedAssetNationalDistributeDetailVO> getDetail(
			String processInstanceId) {
		return processHandler.getProcessDetail(processInstanceId, FixedAssetNationalDistributeDetailVO.class);
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
