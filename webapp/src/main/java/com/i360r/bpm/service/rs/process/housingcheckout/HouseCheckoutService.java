package com.i360r.bpm.service.rs.process.housingcheckout;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.TaskService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.housingcheckout.api.HousingCheckoutCashierConfirmRequest;
import com.i360r.bpm.service.rs.process.housingcheckout.api.HousingCheckoutModifyRequest;
import com.i360r.bpm.service.rs.process.housingcheckout.api.HousingCheckoutRequest;
import com.i360r.bpm.service.rs.process.housingcheckout.api.HousingCheckoutVO;
import com.i360r.bpm.service.rs.process.housingcheckout.api.IHouseCheckoutService;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;

public class HouseCheckoutService implements IHouseCheckoutService {

	@Autowired
	private IProcessHandler processHandler;
	
	@Autowired
	private TaskService taskService;
	
	@Override
	public ProcessDetailResponse<HousingCheckoutVO> getDetail(
			String processInstanceId) {
		return processHandler.getProcessDetail(processInstanceId, HousingCheckoutVO.class);
	}

	@Override
	public ServiceResponse startProcess(
			HousingCheckoutRequest housingCheckoutRequest) throws Exception {
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		Map<String, Object> customVariables = new HashMap<String, Object>();
		
		if (StringUtils.isBlank(housingCheckoutRequest.getBusinessAreaCode())) {
			customVariables.put(ProcessConstants.KEY_LOCATION_CODE, housingCheckoutRequest.getCityCode());
		}
		
		processHandler.startProcess(employee, housingCheckoutRequest, HousingCheckoutCreationConstants.PROCESS_DEFINITION_KEY, customVariables, AccountType.EMPLOYEE);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse cityCommissarApprove(ApproveRequest request)
			throws Exception {
		processHandler.completeTask(request, HousingCheckoutCreationConstants.TASK_CITY_COMMISSAR_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse directManagementCityManagerApprove(
			ApproveRequest request) throws Exception {
		processHandler.completeTask(request, HousingCheckoutCreationConstants.TASK_DIRECT_MANAGEMENT_CITY_MANAGER_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse accountantApprove(ApproveRequest request)
			throws Exception {
		processHandler.completeTask(request, HousingCheckoutCreationConstants.TASK_ACCOUNTANT_APPROVED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse cashierConfirm(HousingCheckoutCashierConfirmRequest request)
			throws Exception {
		processHandler.completeTask(request, HousingCheckoutCreationConstants.TASK_CASHIER_CONFIRMED);
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse housingCheckoutModify(HousingCheckoutModifyRequest request) throws Exception {
		
		processHandler.completeTask(request, HousingCheckoutCreationConstants.TASK_APPROVE_REAPPLY);
		return new ServiceResponse();
	}

	//以下为兼容老流程，员工账号统一1.2
	@Override
	public ServiceResponse adminManagerApprove(ApproveRequest request)
			throws Exception {
		processHandler.completeTask(request, HousingCheckoutCreationConstants.TASK_ADMIN_MANAGER_APPROVED);
		return new ServiceResponse();
	}

}
