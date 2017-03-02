package com.i360r.bpm.service.rs.process.fixedasset.scrap;

import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.fixedasset.api.IFixedAssetHandler;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.fixedasset.scrap.api.FixedAssetScrapDetailVO;
import com.i360r.bpm.service.rs.process.fixedasset.scrap.api.FixedAssetScrapRequest;
import com.i360r.bpm.service.rs.process.fixedasset.scrap.api.IFixedAssetScrapService;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.util.StringUtility;
import com.i360r.oas.api.internal.rs.inventory.FrozenInventoryCountRequest;

public class FixedAssetScrapService implements IFixedAssetScrapService {
	
	@Autowired
	private IProcessHandler processHandler;
	
	@Autowired
	private IFixedAssetHandler fixedAssetHandler;
	
	@Override
	public ServiceResponse startProcess(FixedAssetScrapRequest request) throws Exception {
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		
		//验证出库数量是否大于现有的数据库的数量
		fixedAssetHandler.validateFrozenInventoryCount(Integer.parseInt(request.getBusinessAreaInventoryId()),request.getInventoryType(), Integer.parseInt(request.getScrapCount()));
				
		processHandler.startProcess(employee, request, FixedAssetScrapConstants.PROCESS_DEFINITION_KEY, AccountType.EMPLOYEE);
		
		//流程发起成功后，冻结出库数量
		FrozenInventoryCountRequest frozenInventoryCountRequest = new FrozenInventoryCountRequest();
		
		frozenInventoryCountRequest.setFrozenCount(StringUtility.toInteger(request.getScrapCount()));
		frozenInventoryCountRequest.setFrozenResult(true);
		frozenInventoryCountRequest.setId(StringUtility.toInteger(request.getBusinessAreaInventoryId()));
		frozenInventoryCountRequest.setInventoryType(request.getInventoryType());
		
		fixedAssetHandler.updateInventoryByFrozenCount(frozenInventoryCountRequest);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse adminSpecialistApprove(ApproveRequest request) {
		processHandler.completeTask(request, FixedAssetScrapConstants.KEY_ADMIN_SPECIALIST_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ProcessDetailResponse<FixedAssetScrapDetailVO> getDetail(String processInstanceId) {
		return processHandler.getProcessDetail(processInstanceId, FixedAssetScrapDetailVO.class);
	}

}
