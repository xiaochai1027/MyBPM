package com.i360r.bpm.business.handler.employee.api;

import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;

import com.i360r.cds.api.internal.rs.employee.EmployeeResponse;
import com.i360r.cds.api.internal.rs.employee.EmployeeSO;
import com.i360r.cds.api.internal.rs.employee.position.EmployeePositionSO;


public interface IEmployeeHandler {
	
	public String getStationManagerCode(String businessAreaId) throws Exception;
	
	public String getStationManagerMobile(String businessAreaId) throws Exception;
	
	// hasSuperiorWithLocation, getSuperiorWithLocation, getSuperiorWithLocation 是配送员入职使用的
	public boolean hasSuperiorWithLocationCode(String locationCode, String positionCode) throws Exception;
	
	public String getSuperiorWithLocationCode(String locationCode, String positionCode) throws Exception;
	
	public String getSuperiorWithLocationCode(String locationCode, String positionCode, boolean required) throws Exception;
	
	public boolean hasSuperiorWithPositionCode(String employeeCode, String positionCode) throws Exception;
	
	public boolean hasPositionCode(String employeeCode, String positionCode) throws Exception;
	
	public String getSuperiorWithPositionCode(String employeeCode, String positionCode) throws Exception;
	
	public String getSuperiorWithPositionCode(String employeeCode, String positionCode, boolean required) throws Exception;
	
	public boolean hasSuperiorWithRole(String employeePositionCode, String positionCode) throws Exception;
	
	public String getSuperiorWithRole(String employeePositionCode, String positionCode) throws Exception;
	
	public String getSuperiorWithRole(String employeePositionCode, String positionCode, boolean required) throws Exception;
	
	public List<Group> findGroupsByUser(String employeePositionId);
	
	public User findUserById(String employeePositionId);
	
	public List<String> getGroupIdsByEmployee(String employeePositionId);
	
	public String getAssigneeName(String employeePositionId);
	
	public EmployeeSO getEmployeeByCode(String employeePositionId);
	
	public String getEmployeeLoginNameByCode(String employeePositionId);
	
	public List<String> getEmployeePositionCodesByName(String employeeName) throws Exception;
	
	public String getPositionByEmployeePositionCode(String employeePositionId);
	
	public boolean isDirectManagementCityManager(String employeePositionCode);
	
	public boolean isCityCommissar(String employeePositionCode);
	
	public EmployeePositionSO getEmployeePositionByEmployeePositionCode(String employeePositionCode);

	public String getEmployeeMobileById(String employeeId);
	
	public String getInnerMessageSuperiorPositionCode(String employeePositionId);

	public String getEmployeePositionByPositionCodeAndLocationId(String positionCode, String locationId);
	
	public EmployeeResponse getEmployeeById(String employeeId);
}
