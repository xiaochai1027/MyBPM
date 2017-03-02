package com.i360r.bpm.business.handler.employee;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.i360r.bpm.business.exception.RemoteServerException;
import com.i360r.bpm.business.handler.AbstractBusinessHandler;
import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.cds.api.internal.rs.employee.EmployeeResponse;
import com.i360r.cds.api.internal.rs.employee.EmployeeSO;
import com.i360r.cds.api.internal.rs.employee.EmployeesResponse;
import com.i360r.cds.api.internal.rs.employee.IEmployeeInternalService;
import com.i360r.cds.api.internal.rs.employee.SearchEmployeeRequest;
import com.i360r.cds.api.internal.rs.employee.position.EmployeePositionResponse;
import com.i360r.cds.api.internal.rs.employee.position.EmployeePositionSO;
import com.i360r.cds.api.internal.rs.employee.position.EmployeePositionsResponse;
import com.i360r.cds.api.internal.rs.employee.position.IEmployeePositionInternalService;
import com.i360r.cds.api.internal.rs.employee.position.PositionCodeSO;
import com.i360r.cds.api.internal.rs.employee.position.PositionSO;
import com.i360r.cds.api.internal.rs.employee.position.SearchEmployeePositionRequest;
import com.i360r.cds.api.internal.rs.employee.position.SearchSuperiorEmployeePositionRequest;
import com.i360r.framework.log.Log;
import com.i360r.framework.util.CollectionUtility;
import com.i360r.framework.util.StringUtility;

public class EmployeeHandler extends AbstractBusinessHandler implements IEmployeeHandler {
	private final static Log LOG = Log.getLog(EmployeeHandler.class);
	
	@Resource
	private IEmployeePositionInternalService employeePositionInternalServiceClient;
	
	@Resource
	private IEmployeeInternalService employeeInternalServiceClient;
	
	@Override
	public String getStationManagerCode(String businessAreaId) throws Exception {
		LOG.info("bpm-EmployeeHandler-getStationManagerCode-businessAreaId: {}", businessAreaId);
		
		SearchEmployeePositionRequest request = new SearchEmployeePositionRequest();
		request.setLocationId(businessAreaId);
		request.setPositionCode(PositionCodeSO.DELIVERY_STATION_MANAGER.getCode());
		request.setIncludeParentLocation(false);

		LOG.info("bpm-EmployeeHandler-getStationManagerCode-PositionCode: {}", request.getPositionCode());
		
		EmployeePositionsResponse response = employeePositionInternalServiceClient.searchSuperiorEmployeePosition(request);
		checkResponseSuccess(response);
		
		if (response != null && response.getEmployeePositons() != null 
				&& !CollectionUtility.isEmpty(response.getEmployeePositons())) {
			LOG.info("bpm-EmployeeHandler-getStationManagerCode-employeePositionCode:  {}", response.getEmployeePositons().get(0).getId());
			
			return response.getEmployeePositons().get(0).getId();
		}
		return null;
	}
	
	//TODO (老版流程) 
	@Override
	public boolean hasSuperiorWithRole(String employeePositionId, String positionCode) throws Exception {
		return hasSuperiorWithPositionCode(employeePositionId, positionCode);
	}
	
	//TODO (老版流程)
	@Override
	public String getSuperiorWithRole(String employeePositionId, String positionCode) throws Exception {
		return getSuperiorWithPositionCode(employeePositionId, positionCode);
	}
	
	//TODO (老版流程)
	@Override
	public String getSuperiorWithRole(String employeePositionId, String positionCode, boolean required) throws Exception {
		return getSuperiorWithPositionCode(employeePositionId, positionCode, required);
	}
	
	@Override
	public boolean hasSuperiorWithPositionCode(String employeePositionId, String positionCode) throws Exception {
		//兼容老流程，映射老流程岗位
		positionCode = changePositionCode(positionCode);
		
		return !StringUtils.isBlank(getSuperiorWithPositionCode(employeePositionId, positionCode));
	}
	
	@Override
	public String getSuperiorWithPositionCode(String employeePositionId, String positionCode) throws Exception {
		//兼容老流程，映射老流程岗位
		positionCode = changePositionCode(positionCode);
		
		return getSuperiorWithPositionCode(employeePositionId, positionCode, false);
	}
	
	@Override
	public String getSuperiorWithPositionCode(String employeePositionId, String positionCode, boolean required) throws Exception {
		//兼容老流程，映射老流程岗位
		positionCode = changePositionCode(positionCode);
		
		SearchSuperiorEmployeePositionRequest request = new SearchSuperiorEmployeePositionRequest();
		request.setEmployeePositionId(employeePositionId);
		request.setPositionCode(positionCode);
		request.setIncludeParentLocation(true);
		EmployeePositionsResponse response = employeePositionInternalServiceClient.searchSuperiorEmployeePosition(request);
		
		checkResponseSuccess(response);

		if (response != null && !CollectionUtility.isEmpty(response.getEmployeePositons())) {
			return response.getEmployeePositons().get(0).getId();
		}
		if (required) {
			throw new RemoteServerException("上级不存在");
		}
		
		return null;
	}
	
	@Override
	public boolean hasSuperiorWithLocationCode(String locationId, String positionCode) throws Exception {
		//兼容老流程，映射老流程岗位
		positionCode = changePositionCode(positionCode);
		
		return !StringUtils.isBlank(getSuperiorWithLocationCode(locationId, positionCode));
	}
	
	@Override
	public String getSuperiorWithLocationCode(String locationId, String positionCode) throws Exception {
		//兼容老流程，映射老流程岗位
		positionCode = changePositionCode(positionCode);
		
		return getSuperiorWithLocationCode(locationId, positionCode, false);
	}
	
	@Override
	public String getSuperiorWithLocationCode(String locationId, String positionCode, boolean required) throws Exception {
		
		//兼容老流程，映射老流程岗位
		positionCode = changePositionCode(positionCode);
		
		SearchEmployeePositionRequest request = new SearchEmployeePositionRequest();
		request.setLocationId(locationId);
		request.setPositionCode(positionCode);
		request.setIncludeParentLocation(true);
		EmployeePositionsResponse response = employeePositionInternalServiceClient.searchSuperiorEmployeePosition(request);
		
		checkResponseSuccess(response);
		if (response != null && !CollectionUtility.isEmpty(response.getEmployeePositons())) {
			return response.getEmployeePositons().get(0).getId();
		}
		if (required) {
			throw new RemoteServerException("上级不存在");
		}
		return null;
	}
	
	@Override
	public boolean hasPositionCode(String locationCode, String positionCode)
			throws Exception {
		//兼容老流程，映射老流程岗位
		positionCode = changePositionCode(positionCode);
		
		SearchEmployeePositionRequest request = new SearchEmployeePositionRequest();
		request.setIncludeParentLocation(true);
		request.setLocationId(locationCode);
		request.setMasterPosition(null);
		request.setPositionCode(positionCode);
		EmployeePositionsResponse response = employeePositionInternalServiceClient.searchEmployeePosition(request);
		checkResponseSuccess(response);

		boolean result = false;
		
		if(response != null && !CollectionUtility.isEmpty(response.getEmployeePositons()) && response.getEmployeePositons().get(0).getId() != null){
			result = true;
		}
		
		return result;
	}
	
	
	
	@Override
	public List<String> getGroupIdsByEmployee(String employeePositionId) {
		List<String> result = new ArrayList<String>();
		
		EmployeePositionSO employeePositionSO = getEmployeePositionByEmployeePositionCode(employeePositionId);
		if (employeePositionSO != null 
				&& employeePositionSO.getPosition() != null) {
			
			PositionSO position = employeePositionSO.getPosition();
			if (position != null) {
				result.add(position.getCode());
			}
		}
		return result;
	}
	
	@Override
	public String getAssigneeName(String employeePositionId) {
		if (!StringUtils.isBlank(employeePositionId)) {
			EmployeeResponse response = employeeInternalServiceClient.getEmployeeByEmployeePosition(employeePositionId);
			checkResponseSuccess(response);
			
			EmployeeSO employee = response.getEmployee();
			if (employee != null && StringUtils.isNotBlank(employee.getFullName())) {
				return employee.getFullName();
			}
			
			return String.format("CODE(%s)", employeePositionId);
		}
		return "";
	}
	
	@Override
	public List<Group> findGroupsByUser(String employeePositionId) {
		
		EmployeePositionSO employeePositionSO = getEmployeePositionByEmployeePositionCode(employeePositionId);
		
		List<Group> result = new ArrayList<Group>();
		if (employeePositionSO != null 
				&& employeePositionSO.getPosition() != null) {
			
			PositionSO position = employeePositionSO.getPosition();
			if (position != null) {
				GroupEntity group = new GroupEntity();
				group.setId(position.getCode());
				group.setName(position.getName());
				result.add(group);
			}
		}
		return result;
	}
	
	@Override
	public User findUserById(String employeePositionId) {

		EmployeePositionSO employeePositionSO = getEmployeePositionByEmployeePositionCode(employeePositionId);
		
		if (employeePositionSO != null 
				&& employeePositionSO.getEmployee() != null) {
			
			UserEntity user = new UserEntity();
			user.setId(employeePositionSO.getId());
			user.setFirstName("");
			user.setLastName(employeePositionSO.getEmployee().getFullName());
			return user;
		}
		
		return null;
	}

	@Override
	public String getEmployeeLoginNameByCode(String employeePositionId) {
		
		EmployeeResponse response = employeeInternalServiceClient.getEmployeeByEmployeePosition(employeePositionId);
		checkResponseSuccess(response);
		if (response != null && response.getEmployee() != null
				&& response.getEmployee().getEmployeePassport() != null) {
			EmployeeSO employee = response.getEmployee();
			return employee.getEmployeePassport().getMobile();
		}
		return null;
	}


	@Override
	public List<String> getEmployeePositionCodesByName(String employeeName) throws Exception {
		
		SearchEmployeeRequest request = new SearchEmployeeRequest();
		//设置查询的页长为1000
		int pageSize =1000;
		int pageNumber = 1;
		request.setName("%" + employeeName + "%");
		request.setOnlyEmployee(true);
		request.setPaged(true);
		request.setPageNumber(pageNumber);
		request.setPageSize(pageSize);
		EmployeesResponse response = employeeInternalServiceClient.searchEmployee(request);
		checkResponseSuccess(response);
		int recordNumber = response.getRecordNumber();
		int totalPage = recordNumber / pageSize;
		if (recordNumber % pageSize != 0) {
			totalPage = totalPage + 1;
		}
		
		checkResponseSuccess(response);
		List<String> employeePositionIds = getEmployeePositionIds(response.getEmployees());
		
		if (totalPage > 1) {
			//循环查询数据
			pageNumber = 2;
			while (pageNumber <=  totalPage) {
				request.setPageNumber(pageNumber);
				EmployeesResponse employee = employeeInternalServiceClient.searchEmployee(request);
				checkResponseSuccess(response);
				employeePositionIds.addAll(getEmployeePositionIds(employee.getEmployees()));
				pageNumber++;
			}
		}
		
		return employeePositionIds;
	}
	
	private List<String> getEmployeePositionIds(List<EmployeeSO> employees) {
		List <String> employeePositionIds = new ArrayList<String>();
		if (!CollectionUtils.isEmpty(employees)) {
			for (EmployeeSO so : employees) {
				if (!CollectionUtility.isEmpty(so.getEmployeePositions())) {
					for (EmployeePositionSO employeePositionSO : so.getEmployeePositions()) {
						employeePositionIds.add(employeePositionSO.getId());
					}
				}
					
			}
		}
		return employeePositionIds;
	}

	@Override
	public String getStationManagerMobile(String businessAreaId) throws Exception {
		
		SearchEmployeePositionRequest request = new SearchEmployeePositionRequest();
		request.setLocationId(businessAreaId);
		request.setPositionCode(PositionCodeSO.DELIVERY_STATION_MANAGER.getCode());
		request.setIncludeParentLocation(false);
		EmployeePositionsResponse response = employeePositionInternalServiceClient.searchSuperiorEmployeePosition(request);
		checkResponseSuccess(response);
		
		
		if (response != null && !CollectionUtility.isEmpty(response.getEmployeePositons()) 
				&& response.getEmployeePositons().get(0).getEmployee() != null && response.getEmployeePositons().get(0).getEmployee().getEmployeePassport() != null) {
			
			String mobile = response.getEmployeePositons().get(0).getEmployee().getEmployeePassport().getMobile();
			
			return mobile;
		}
		return null;
	}
	
	@Override
	public EmployeeSO getEmployeeByCode(String employeePositionId) {
		
		EmployeeResponse response = employeeInternalServiceClient.getEmployeeByEmployeePosition(employeePositionId);
		checkResponseSuccess(response);
		
		return response.getEmployee();
	}
	
	@Override
	public String getPositionByEmployeePositionCode(String employeePositionId) {
		
		EmployeePositionResponse response = employeePositionInternalServiceClient.getEmployeePositionById(employeePositionId);
		checkResponseSuccess(response);
		if (response != null && response.getEmployeePositionSO() != null 
				&& response.getEmployeePositionSO().getPosition() != null) {
			PositionSO position = response.getEmployeePositionSO().getPosition();
			if (position != null) {
				return position.getName();
			}
		}
		return null;
	}
	
	/**
	 * 判断人岗是否是城市经理岗位
	 * @param employeePositionCode
	 * @return
	 */
	@Override
	public boolean isDirectManagementCityManager(String employeePositionCode) {
		
		EmployeePositionSO employeePositionSO = getEmployeePositionByEmployeePositionCode(employeePositionCode);
		
		if (employeePositionSO != null) {
			PositionSO position = employeePositionSO.getPosition();
			if (position.getCode().equals(PositionCodeSO.DIRECT_MANAGEMENT_CITY_MANAGER.getCode())) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 判断人岗是否是城市政委岗位
	 * @param employeePositionCode
	 * @return
	 */
	@Override
	public boolean isCityCommissar(String employeePositionCode) {
		
		EmployeePositionSO employeePositionSO = getEmployeePositionByEmployeePositionCode(employeePositionCode);
		
		if (employeePositionSO != null) {
			PositionSO position = employeePositionSO.getPosition();
			if (position.getCode().equals(PositionCodeSO.CITY_COMMISSAR.getCode())) {
				return true;
			}
		}
		
		return false;
	}
	@Override
	public EmployeePositionSO getEmployeePositionByEmployeePositionCode(String employeePositionCode) {
		
		EmployeePositionResponse response = employeePositionInternalServiceClient.getEmployeePositionById(employeePositionCode);
		checkResponseSuccess(response);

		return response.getEmployeePositionSO();
	}

	@Override
	public String getEmployeeMobileById(String employeeId) {
		
		EmployeeResponse response = employeeInternalServiceClient.getEmployeeById(employeeId);
		checkResponseSuccess(response);
		
		return response.getEmployee().getEmployeePassport().getMobile();
	}

	@Override
	public String getInnerMessageSuperiorPositionCode(String employeePositionId) {
		EmployeePositionsResponse response = employeePositionInternalServiceClient.searchSuperiorEmployeePosition(employeePositionId);
		checkResponseSuccess(response);
		if (response != null && !CollectionUtility.isEmpty(response.getEmployeePositons())) {
			String superiorPositionCode = response.getEmployeePositons().get(0).getPosition().getCode();
			return superiorPositionCode;
		}		
		return null;
	}
	

	@Override
	public String getEmployeePositionByPositionCodeAndLocationId(String positionCode, String locationId) {
		SearchEmployeePositionRequest request = new SearchEmployeePositionRequest();
		request.setPositionCode(positionCode);
		request.setLocationId(locationId);
		request.setIncludeParentLocation(true);
		EmployeePositionsResponse response = employeePositionInternalServiceClient.searchEmployeePosition(request);

		if (response == null || CollectionUtility.isEmpty(response.getEmployeePositons())) {
			throw new RemoteServerException("EmployeePositons is null");
		}
		return response.getEmployeePositons().get(0).getId();
	}
	
	//兼容老流程，员工账统一1.2,老岗位映射新岗位
	private String changePositionCode(String positionCode){
		
		if(StringUtility.equals(positionCode, "DIRECT_MANAGEMENT_LARGE_AREA_DIRECTOR")){
			positionCode = "OPERATION_MANAGEMENT_VICE_DIRECTOR";
		}else if(StringUtility.equals(positionCode, "LOGISTICS_CHIEF_MANAGER")){
			positionCode = "OPERATION_MANAGEMENT_DIRECTOR";
		}else if(StringUtility.equals(positionCode, "COMPENSATION_BENEFITS_SPECIALIST")){
			positionCode = "COMPENSATION_SPECIALIST";
		}
		
		return positionCode; 
	}
	
	@Override
	public EmployeeResponse getEmployeeById(String employeeId) {
		
		EmployeeResponse response = employeeInternalServiceClient.getEmployeeById(employeeId);
		checkResponseSuccess(response);
		
		return response;
	}

}
