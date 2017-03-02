package com.i360r.bpm.business.handler.employee.salary;

import javax.annotation.Resource;

import com.i360r.bpm.business.exception.RemoteServerException;
import com.i360r.bpm.business.handler.AbstractBusinessHandler;
import com.i360r.bpm.business.handler.employee.salary.api.ISalaryHandler;
import com.i360r.bpm.business.util.ServiceClientUtility;
import com.i360r.cds.api.internal.rs.employee.position.EmployeePositionsResponse;
import com.i360r.cds.api.internal.rs.employee.position.IEmployeePositionInternalService;
import com.i360r.cds.api.internal.rs.employee.position.SearchEmployeePositionRequest;
import com.i360r.cds.api.internal.rs.employee.position.SearchSuperiorEmployeePositionRequest;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.util.CollectionUtility;
import com.i360r.oas.api.internal.rs.employee.salary.UpdateCitySalaryAuditStatusRequest;
import com.i360r.oas.api.internal.rs.employee.salary.UpdateGlobalSalaryAuditStatusRequest;

/**
 * 
 * @ClassName: SalaryHandler
 * @Description: 调用delivery 的IEmployeeSalaryInternalService接口
 * @author 柴方晨
 * @date 2016年7月4日 上午11:30:06
 *
 */
public class SalaryHandler extends AbstractBusinessHandler implements ISalaryHandler {

	@Resource
	private IEmployeePositionInternalService employeePositionInternalServiceClient;

	@Override
	public String getSuperiorWithPosition(String employeePositionId, String positionCode, String positionName, boolean required) throws Exception {
		if ("COMPENSATION_BENEFITS_SPECIALIST".equals(positionCode)) { //作用：兼容老流程
			positionCode = "COMPENSATION_SPECIALIST";
		}
		SearchSuperiorEmployeePositionRequest request = new SearchSuperiorEmployeePositionRequest();
		request.setPositionCode(positionCode);
		request.setEmployeePositionId(employeePositionId);
		request.setIncludeParentLocation(required);
		EmployeePositionsResponse response = employeePositionInternalServiceClient.searchSuperiorEmployeePosition(request);
		checkResponseSuccess(response);

		if (response != null && !CollectionUtility.isEmpty(response.getEmployeePositons())) {
			return response.getEmployeePositons().get(0).getId();
		}
		if (required) {
			throw new RemoteServerException(positionName + "不存在");
		}
		return null;
	}

	@Override
	public ServiceResponse updateCitySalaryAuditStatus(
			UpdateCitySalaryAuditStatusRequest request) throws Exception {
		ServiceResponse response = ServiceClientUtility.getEmployeeSalaryInternalService().updateCitySalaryAuditStatus(request);
		checkResponseSuccess(response);
		return response;
	}

	@Override
	public ServiceResponse createCitySalaryAudit(Integer citySalaryId)
			throws Exception {
		ServiceResponse response = ServiceClientUtility.getEmployeeSalaryInternalService().createCitySalaryAudit(citySalaryId);
		checkResponseSuccess(response);
		return null;
	}

	@Override
	public ServiceResponse updateGlobalSalaryAuditStatus(UpdateGlobalSalaryAuditStatusRequest request) throws Exception {
		ServiceResponse response = ServiceClientUtility.getEmployeeSalaryInternalService().updateGlobalSalaryAuditStatus(request);
		checkResponseSuccess(response);
		return null;
	}

	@Override
	public ServiceResponse createGlobalSalaryAudit(Integer globalSalaryId)
			throws Exception {
		ServiceResponse response = ServiceClientUtility.getEmployeeSalaryInternalService().createGlobalSalaryAudit(globalSalaryId);
		checkResponseSuccess(response);
		return null;
	}

	@Override
	public String getCPO() {
		SearchEmployeePositionRequest request = new SearchEmployeePositionRequest();
		request.setPositionCode("CPO");
		EmployeePositionsResponse response = employeePositionInternalServiceClient.searchEmployeePosition(request);

		if (response == null || CollectionUtility.isEmpty(response.getEmployeePositons())) {
			throw new RemoteServerException("CPO不存在");
		}
		return response.getEmployeePositons().get(0).getId();
	}

	@Override
	public String getCOO() {
		SearchEmployeePositionRequest request = new SearchEmployeePositionRequest();
		request.setPositionCode("COO");
		EmployeePositionsResponse response = employeePositionInternalServiceClient.searchEmployeePosition(request);

		if (response == null || CollectionUtility.isEmpty(response.getEmployeePositons())) {
			throw new RemoteServerException("COO不存在");
		}
		return response.getEmployeePositons().get(0).getId();
	}


}
