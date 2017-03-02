package com.i360r.bpm.business.handler.deliverystaff;

import com.i360r.bpm.business.handler.AbstractBusinessHandler;
import com.i360r.bpm.business.handler.deliverystaff.api.DeliveryStaffVO;
import com.i360r.bpm.business.handler.deliverystaff.api.IDeliveryStaffHandler;
import com.i360r.bpm.business.handler.fixedasset.api.IFixedAssetHandler;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.AccountType;
import com.i360r.bpm.business.util.DateTimeUtility;
import com.i360r.bpm.business.util.ServiceClientUtility;
import com.i360r.bpm.service.rs.process.deliverystaff.workovertime.DeliveryStaffWorkOvertimeConstants;
import com.i360r.bpm.service.rs.process.deliverystaff.workovertime.api.DeliveryStaffWorkOvertimeRequest;
import com.i360r.bpm.service.rs.process.deliverystaffcancelleave.api.HalfDate;
import com.i360r.bpm.service.rs.process.deliverystaffentry.DeliveryStaffEntryConstants;
import com.i360r.bpm.service.rs.process.deliverystaffentry.api.DeliveryStaffHeadCountRequest;
import com.i360r.bpm.service.rs.process.deliverystaffentry.api.InventoryVO;
import com.i360r.cds.api.internal.rs.deliverystaff.*;
import com.i360r.cds.api.internal.rs.employee.passport.EmployeePassportResponse;
import com.i360r.cds.api.internal.rs.employee.position.EmployeePositionSO;
import com.i360r.cds.api.internal.rs.location.BusinessAreaSO;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.service.api.exception.IllegalRequestException;
import com.i360r.oas.api.internal.rs.deliverystaff.leave.*;
import com.i360r.oas.api.internal.rs.inventory.DistributeBusinessAreaInventoryRequest;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 
 * @ClassName: DeliveryStaffHandler
 * @Description: 增加配送员入职分发商圈库存资产,存入责任人的code和name，还有绑定的资产
 * @author 柴方晨
 * @date 2016年7月19日 下午1:46:01
 *
 */
public class DeliveryStaffHandler extends AbstractBusinessHandler implements IDeliveryStaffHandler {

	@Resource
	private IDeliveryStaffLeaveInternalService leaveInternalServiceClient;
	
	@Resource
	private IFixedAssetHandler fixedAssetHandler;
	
	@Resource
	private IDeliveryStaffInternalService cdStaffInternalService;
	
	@Autowired
	private IProcessHandler processHandler;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Override
	public DeliveryStaffVO getDeliveryStaffByCode(String code) throws Exception {
		DeliveryStaffSO so = getDeliveryStaffById(code);
		return toVO(so);
	}
	
	@Override
	public DeliveryStaffSO getDeliveryStaffById(String id) throws Exception {
		DeliveryStaffDetailResponse response = cdStaffInternalService.getDeliveryStaffById(id);
		checkResponseSuccess(response);
		return response.getDeliveryStaff();
	}
	
	private DeliveryStaffVO toVO (DeliveryStaffSO so) {
		DeliveryStaffVO vo = new DeliveryStaffVO();
		vo.setCode(so.getDeliveryStaffId());
		vo.setDeliveryStaffGradeTypeCode(so.getDeliveryStaffGradeTypeCode());
		
		EmployeePositionSO employeePosition = so.getEmployeePosition();
		vo.setEntryDate(employeePosition.getEntryDate());
		vo.setFullName(employeePosition.getEmployee().getFullName());
		vo.setPositonCode(employeePosition.getPosition().getCode());
		vo.setMobile(employeePosition.getEmployee().getEmployeePassport().getMobile());
		vo.setStatusCode(employeePosition.getStatusCode());
		vo.setEmployeeId(employeePosition.getEmployee().getId());
		vo.setIdentityCardNumber(employeePosition.getEmployee().getIdentityCardNumber());
		vo.setEmployeeEntryDate(employeePosition.getEmployee().getEntryDate());
		vo.setEmployeePositionEntryDate(employeePosition.getEntryDate());
		
		BusinessAreaSO businessArea = so.getBusinessArea();
		vo.setBusinessAreaCode(businessArea.getId());
		vo.setBusinessAreaName(businessArea.getName());
		vo.setCityName(businessArea.getCity().getName());
		vo.setCityCode(businessArea.getCity().getId());
		
		return vo;
	}
	
	@Override
	public void deliveryStaffDimission(DimissDeliveryStaffRequest request) throws Exception {		
		ServiceResponse response = cdStaffInternalService.dimissDeliveryStaff(request);
		checkResponseSuccess(response);	
	}
	
	@Override
	public void checkLeave(String employeePositionCode, 
			Date fromDateHour, Date toDateHour, String leaveTypeCode) throws Exception {
		
		LeaveApplyRequest request = new LeaveApplyRequest();
		request.setEmployeePositionCode(employeePositionCode);
		request.setFromDateHour(DateTimeUtility.formatYYYYMMDDHHMMSS(fromDateHour));
		request.setToDateHour(DateTimeUtility.formatYYYYMMDDHHMMSS(toDateHour));
		request.setLeaveTypeCode(leaveTypeCode);
		
		ServiceResponse response = leaveInternalServiceClient.leaveCheck(request);
		checkResponseSuccess(response);
	}
	
	@Override
	public void leaveSuccess(LeaveSuccessRequest request) throws Exception {		
		ServiceResponse response = leaveInternalServiceClient.leaveSuccess(request);
		checkResponseSuccess(response);
	}
	
	@Override
	public boolean isExistingDingdingMobile(String employeeCode, String dingdingMobile) throws Exception {
		EmployeePassportResponse response = ServiceClientUtility.getEmployeePassportInternalService()
				.getEmployeePassportByDingdingMobile(dingdingMobile);
		checkResponseSuccess(response);
		if (response != null 
				&& response.getEmployeePassport() != null
				&& response.getEmployeePassport().getEmployee() != null
				&& StringUtils.isNotBlank(response.getEmployeePassport().getEmployee().getId())
				&& !response.getEmployeePassport().getEmployee().getId().equals(employeeCode)) {
			return true;
		}
		return false;
		
	}
	
	@Override
	public void createOfficialAccount(CreateOfficialAccountRequest request)
			throws Exception {
		
		ServiceResponse response = cdStaffInternalService.createOfficialAccount(request);
		checkResponseSuccess(response);
		
	}
	
	@Override
	public void transferPosition(DeliveryStaffTransformationRequest request)
			throws Exception {
		ServiceResponse response = cdStaffInternalService.transferPosition(request);
		checkResponseSuccess(response);
	}

	@Override
	public String createTrialAccount(CreateTrialAccountRequest request)
			throws Exception {
		
		TrialAccountResponse response = cdStaffInternalService.createTrialAccount(request);
		checkResponseSuccess(response);
		
		String deliveryStaffCode = response.getDeliveryStaffId();
		return deliveryStaffCode;
	}

	@Override
	public void checkCancelLeave(String employeePositionCode, int deliveryStaffLeaveId, List<HalfDate> originCancelLeaves)
			throws Exception {
		
		Set<HalfDate> halfDates = new HashSet<HalfDate>();
		if (originCancelLeaves != null && originCancelLeaves.size() > 0) {
			for (HalfDate halfDate : originCancelLeaves) {
				if (!halfDates.contains(halfDate)) {
					halfDates.add(halfDate);
				} else {
					throw new IllegalRequestException("cancel leaves time override");
				}
			}
		}
		
		CancelLeaveRequest  request = new CancelLeaveRequest ();
		request.setEmployeePositionCode(employeePositionCode);
		request.setLeaveId(deliveryStaffLeaveId);
		request.setCancelLeaves(HalfDate.toTimeRangeVOList(originCancelLeaves));
		
		ServiceResponse response = leaveInternalServiceClient.cancelLeaveCheck(request);
		checkResponseSuccess(response);
	}

	@Override
	public void cancelLeaveSuccess(CancelLeaveRequest request) throws Exception {
		ServiceResponse response = leaveInternalServiceClient.cancelLeaveSuccess(request);
		checkResponseSuccess(response);
	}

	@Override
	public void updateWorkOvertime(UpdateWorkOvertimeRequest request) throws Exception {
		ServiceResponse response = leaveInternalServiceClient.approvingWorkOvertime(request);
		checkResponseSuccess(response);
	}

	@Override
	public int createApprovingWorkOvertime(CreateWorkOvertimeRequest request) throws Exception {
		ApprovingWorkOvertimeResponse response = leaveInternalServiceClient.createWorkOvertime(request);
		checkResponseSuccess(response);
		return response.getWorkOvertimeId();
	}

	@Override
	public void deleteApprovingWorkOvertime(int deliveryStaffWorkOvertimed) throws Exception {
		ServiceResponse response = leaveInternalServiceClient.deleteWorkOvertime(deliveryStaffWorkOvertimed);
		checkResponseSuccess(response);
	}

	@Override
	@Transactional
	public void startEntryProcess(EmployeeCO employee, DeliveryStaffHeadCountRequest request) throws Exception {
		Map<String, Object> customVariables = new HashMap<String, Object>();
		customVariables.put(DeliveryStaffEntryConstants.KEY_DELIVERYSTAFFENTRY_CREATE_BY_CODE, employee.getCode());
		ProcessInstance processInstance = processHandler.startProcess(employee, request, 
				DeliveryStaffEntryConstants.PROCESS_DEFINITION_KEY, customVariables, AccountType.EMPLOYEE);
		
		//如果不是转岗，就是入职
		if (!request.getJobTransfer()) {
			
			//在delivery添加一条试用账号记录
			CreateTrialAccountRequest createTrialAccountRequest = new CreateTrialAccountRequest();
			
			createTrialAccountRequest.setCreatedById(employee.getCode());
			createTrialAccountRequest.setCreatedByName(employee.getName());
			
			createTrialAccountRequest.setBusinessAreaId(request.getBusinessAreaCode());
			createTrialAccountRequest.setFullName(request.getFullName());
			createTrialAccountRequest.setIdentityCard(request.getIdentityCard());
			createTrialAccountRequest.setIdentityCardPictureUrl1(request.getIdentityCardPictureUrl1());
			createTrialAccountRequest.setOrigIdentityCardPictureUrl1(request.getOrigIdentityCardPictureUrl1());
			createTrialAccountRequest.setIdentityCardPictureUrl2(request.getIdentityCardPictureUrl2());
			createTrialAccountRequest.setOrigIdentityCardPictureUrl2(request.getOrigIdentityCardPictureUrl2());
			createTrialAccountRequest.setAvatarPictureUrl(request.getAvatarPictureUrl());
			createTrialAccountRequest.setOrigAvatarPictureUrl(request.getOrigAvatarPictureUrl());
			createTrialAccountRequest.setGenderCode(request.getGenderCode());
			createTrialAccountRequest.setPositionCode(request.getPositionCode());
			createTrialAccountRequest.setMobile(request.getMobile());
			createTrialAccountRequest.setDingdingMobile(request.getDingdingMobile());
			createTrialAccountRequest.setEntryDate(request.getEntryDate());
			createTrialAccountRequest.setCompanyVehiclesUsed(request.getCompanyVehiclesUsed());
			createTrialAccountRequest.setBasicSalary(request.getBasicSalary());
			createTrialAccountRequest.setEducationBackgroundCode(request.getEducationBackgroundCode());
			createTrialAccountRequest.setRecruitmentChannelCode(request.getRecruitmentChannelCode());
			createTrialAccountRequest.setBankCardNumber(request.getBankCard());
			createTrialAccountRequest.setBankCardPictureUrl(request.getBankCardPictureUrl());
			createTrialAccountRequest.setOrigBankCardPictureUrl(request.getOrigBankCardPictureUrl());
			createTrialAccountRequest.setContractBeginDate(request.getContractBeginDate());
			createTrialAccountRequest.setContractEndDate(request.getContractEndDate());
			createTrialAccountRequest.setContractTypeCode(request.getContractTypeCode());
			createTrialAccountRequest.setDeliveryStaffGradeTypeCode(request.getDeliveryStaffGradeTypeCode());
			
			//试用账号配送员id
			String deliveryStaffCode = createTrialAccount(createTrialAccountRequest);
			
			//配送员入职分发商圈库存资产,存入责任人的code和name，还有绑定的资产
			DeliveryStaffDetailResponse deliveryStaffDetailResponse = cdStaffInternalService.getDeliveryStaffById(deliveryStaffCode);
			String employeePositionCode = deliveryStaffDetailResponse.getDeliveryStaff().getEmployeePosition().getId();
			DistributeBusinessAreaInventoryRequest distributeBusinessAreaInventoryRequest = new DistributeBusinessAreaInventoryRequest();
			distributeBusinessAreaInventoryRequest.setUsedByCode(employeePositionCode);
			distributeBusinessAreaInventoryRequest.setUsedByName(request.getFullName());
			Map<Integer, Integer> inventoryIdAndCounts = new HashMap<Integer, Integer>();
			List<InventoryVO> inventoryVos = request.getInventories();
			//存入所有的库存id和数量
			for(InventoryVO inventoryVo : inventoryVos){
				inventoryIdAndCounts.put(inventoryVo.getInventoryId(), inventoryVo.getCount());
			}
			distributeBusinessAreaInventoryRequest.setInventoryIdAndCounts(inventoryIdAndCounts);
			fixedAssetHandler.distributeBusinessAreaInventory(distributeBusinessAreaInventoryRequest);
			
			Execution execution = runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).singleResult();
			runtimeService.setVariable(execution.getId(), 
					DeliveryStaffEntryConstants.KEY_DELIVERY_STAFF_CODE, deliveryStaffCode);
			
		}
		
	}
	
	@Override
	@Transactional
	public void startWorkOvertimeProcess(EmployeeCO employee, DeliveryStaffWorkOvertimeRequest request, Map<String, Object> customVariables) throws Exception {
		
		ProcessInstance processInstance = processHandler.startProcess(employee, request, 
				DeliveryStaffWorkOvertimeConstants.PROCESS_DEFINITION_KEY, customVariables, AccountType.EMPLOYEE);
		
		CreateWorkOvertimeRequest createWorkOvertimeRequest = new CreateWorkOvertimeRequest();
		
		createWorkOvertimeRequest.setOvertimeDays(request.getOvertimeDays());
		createWorkOvertimeRequest.setEmployeePositionCode(request.getEmployeePositionCode());
		createWorkOvertimeRequest.setOvertimeMonth(DateTimeUtility.formatYYYYMMDD(new Date()));
		createWorkOvertimeRequest.setReason(request.getReason());

		int deliveryStaffWorkOvertimeId = createApprovingWorkOvertime(createWorkOvertimeRequest);
		
		List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).list();
		for (Execution exe : executions) {
			runtimeService.setVariable(exe.getId(), 
					DeliveryStaffWorkOvertimeConstants.KEY_DELIVERY_STAFF_WORK_OVETTIME_ID, deliveryStaffWorkOvertimeId);
		}
	}

	@Override
	public void changeDeliveryStaffGrade(String deliveryStaffId, String deliveryStaffGradeTypeCode, String changeGradeDate) throws Exception {
		ChangeDeliveryStaffGradeRequest request = new ChangeDeliveryStaffGradeRequest();
		request.setDeliveryStaffId(deliveryStaffId);
		request.setNewDeliveryStaffGradeTypeCode(deliveryStaffGradeTypeCode);
		request.setChangeGradeDate(changeGradeDate);
		
		ServiceResponse response = cdStaffInternalService.changeDeliveryStaffGrade(request);
		checkResponseSuccess(response);
	}

}
