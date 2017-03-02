package com.i360r.bpm.business.handler.employee.salary.api;

import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.oas.api.internal.rs.employee.salary.UpdateCitySalaryAuditStatusRequest;
import com.i360r.oas.api.internal.rs.employee.salary.UpdateGlobalSalaryAuditStatusRequest;


public interface ISalaryHandler {

	public String getSuperiorWithPosition(String employeePositionId, String positionCode, String positionName, boolean required) throws Exception;

	public String getCPO();

	public String getCOO();

	public ServiceResponse updateCitySalaryAuditStatus(UpdateCitySalaryAuditStatusRequest request) throws Exception;

	public ServiceResponse createCitySalaryAudit(Integer citySalaryId) throws Exception;

	public ServiceResponse updateGlobalSalaryAuditStatus(UpdateGlobalSalaryAuditStatusRequest request) throws Exception;

	public ServiceResponse createGlobalSalaryAudit(Integer globalSalaryId) throws Exception;

}
