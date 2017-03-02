package com.i360r.bpm.service.rs.process.delivery.stationmanager.entry;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.cache.session.GeneralSessionContextAccessor;
import com.i360r.bpm.business.exception.DuplicatedDingdingMobileException;
import com.i360r.bpm.business.exception.DuplicatedIdentityCardNumberException;
import com.i360r.bpm.business.exception.EntityNotExistException;
import com.i360r.bpm.business.exception.IllegalOperationException;
import com.i360r.bpm.business.exception.ProcessApproveErrorException;
import com.i360r.bpm.business.exception.RequestParameterMissingException;
import com.i360r.bpm.business.exception.TransferForbidenException;
import com.i360r.bpm.business.exception.VerificationCodeTimeoutException;
import com.i360r.bpm.business.exception.VerificationCodeValidateFailedException;
import com.i360r.bpm.business.handler.delivery.stationmanager.entry.api.IStationManagerHandler;
import com.i360r.bpm.business.handler.deliverystaff.api.IDeliveryStaffHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.api.IProcessHandler;
import com.i360r.bpm.business.model.ContractType;
import com.i360r.bpm.business.model.VerificationCodeBean;
import com.i360r.bpm.business.util.ProcessUtility;
import com.i360r.bpm.business.util.SessionKeyConstants;
import com.i360r.bpm.business.util.ValidateUtility;
import com.i360r.bpm.service.rs.process.api.ApproveRequest;
import com.i360r.bpm.service.rs.process.api.ProcessDetailResponse;
import com.i360r.bpm.service.rs.process.delivery.stationmanager.entry.api.IStationManagerEntryService;
import com.i360r.bpm.service.rs.process.delivery.stationmanager.entry.api.SendMobileVerificationCodeRequest;
import com.i360r.bpm.service.rs.process.delivery.stationmanager.entry.api.StationManagerEntryDetailVO;
import com.i360r.bpm.service.rs.process.delivery.stationmanager.entry.api.StationManagerEntryModifyRequest;
import com.i360r.bpm.service.rs.process.delivery.stationmanager.entry.api.StationManagerEntryRequest;
import com.i360r.bpm.service.rs.process.delivery.stationmanager.entry.api.StationManagerHeadCountModifyRequest;
import com.i360r.bpm.service.rs.process.delivery.stationmanager.entry.api.StationManagerHeadCountRequest;
import com.i360r.bpm.service.rs.process.util.FlowUtils;
import com.i360r.cds.api.internal.rs.deliverystaff.DeliveryStaffSO;
import com.i360r.cds.api.internal.rs.deliverystaff.IDeliveryStaffInternalService;
import com.i360r.cds.api.internal.rs.deliverystaff.ValidateParamResponse;
import com.i360r.cds.api.internal.rs.employee.EmployeeSO;
import com.i360r.cds.api.internal.rs.employee.position.EmployeePositionStatusSO;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.i18n.MessageSourceManager;
import com.i360r.framework.service.api.exception.IllegalRequestException;
import com.i360r.framework.util.DateTimeUtility;
import com.i360r.framework.util.RandomGenerator;

/**
 * 
 * @ClassName: StationManagerEntryService
 * @Description: 站长入职流程前端接口实现
 * @author 柴方晨
 * @date 2016年7月18日 下午3:25:58
 *
 */
public class StationManagerEntryService implements IStationManagerEntryService {

	@Autowired
	private IStationManagerHandler stationManagerHandler;
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@Resource
	private IDeliveryStaffInternalService cdStaffInternalService;
		
	@Autowired
	private IProcessHandler processHandler;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private IDeliveryStaffHandler deliveryStaffHandler;
	
	@Override
	public ProcessDetailResponse<StationManagerEntryDetailVO> getDetail(String processInstanceId) {
		//老接口做兼容
		ProcessDetailResponse<StationManagerEntryDetailVO> response = processHandler.getProcessDetail(processInstanceId, StationManagerEntryDetailVO.class);
		if (response.getDetail() != null) {
			if (response.getDetail().getPositionCode().equals("FULL_TIME")) {
				response.getDetail().setPositionCode("DIRECT_MANAGEMENT_FULL_TIME_DELIVERY_STAFF");
			} else if (response.getDetail().getPositionCode().equals("PART_TIME")) {
				response.getDetail().setPositionCode("DIRECT_MANAGEMENT_PART_TIME_DELIVERY_STAFF");
			} else if (response.getDetail().getPositionCode().equals("STATION_MANAGER")) {
				response.getDetail().setPositionCode("DELIVERY_STATION_MANAGER");
			} 
		}
		if (response.getDetail() != null 
				&& (StringUtils.isBlank(response.getDetail().getPositionName()) || StringUtils.isBlank(response.getDetail().getOriginPositionName()))) {
			response.getDetail().initOtherProperties();
		}
		if (!StringUtils.isBlank(response.getDetail().getContractTypeCode())
				&& StringUtils.isBlank(response.getDetail().getContractTypeName())) {
			String contractTypeName = ContractType.fromCode(response.getDetail().getContractTypeCode()).getName();
			response.getDetail().setContractTypeName(contractTypeName);
		}
		return response;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ServiceResponse startProcess(StationManagerHeadCountRequest request) throws Exception {
		
		//如果是转岗，必须传原始配送员id、原始配送员jobCategoryCode
		//转岗前配送员jobCategoryCode不能和流程中jobCategoryCode相同
		if (request.getJobTransfer()) {
			//不能在入职月发起本月转岗请求
			Date now = new Date();
			if (DateTimeUtility.isTheSameMonth(DateTimeUtility.parseYYYYMMDD(request.getEntryDate()), now)
					&& DateTimeUtility.isTheSameMonth(DateTimeUtility.parseYYYYMMDD(request.getJobTransferDate()), now)) {
				throw new TransferForbidenException();
			}
			
			if (request.getOriginPositionCode() == null
					|| (request.getOriginPositionCode() != null && request.getOriginPositionCode().equals(request.getPositionCode()))) {
				throw new IllegalRequestException();
			}
			if (StringUtils.isNotBlank(request.getDingdingMobile())) {
				if (stationManagerHandler.isExistingDingdingMobile(request.getEmployeeCode(), request.getDingdingMobile())) {
					throw new DuplicatedDingdingMobileException();
				}
			}
			
		}
		
		/*
		 * 校验转岗条件是否满足
		 * 1.原始账号必须是在职配送员，试用、离职均不能转岗
		 * 2.转岗必须有工作性质变化（工作性质包括：全职、兼职、实习、干部），如果原始账号与流程中账号工作性质相同，不允许
		 * 
		 */
		
		if (!request.getJobTransfer()) {
			if (StringUtils.isBlank(request.getVerificationCode())) {
				throw new RequestParameterMissingException("验证码");
			}
			HashMap<String,VerificationCodeBean> mobileVerificationCodeMap =(HashMap<String,VerificationCodeBean>)GeneralSessionContextAccessor.getAttribute(SessionKeyConstants.DELIVERY_STAFF_ENTRY_MOBILE_VERIFICATION_CODE);
			VerificationCodeBean verificationCodeBean = null;
			if (mobileVerificationCodeMap != null && (verificationCodeBean = mobileVerificationCodeMap.get(request.getMobile())) != null) {
				//验证码超时
				if (DateTimeUtility.addMinutes(verificationCodeBean.getUpdateTime(),30).before(new Date())) {
					mobileVerificationCodeMap.put(request.getMobile(), null);
					GeneralSessionContextAccessor.setAttribute(SessionKeyConstants.DELIVERY_STAFF_ENTRY_MOBILE_VERIFICATION_CODE, mobileVerificationCodeMap);
					throw new VerificationCodeTimeoutException();
				}
				//验证码不对
				if (!verificationCodeBean.getVerificationCode().equals(request.getVerificationCode())) {
					throw new VerificationCodeValidateFailedException();
				}
			}else {
				throw new IllegalOperationException();
			}
		}else {
			//检查是否存在未完成的离职流程
			String deliveryStaffName = deliveryStaffHandler.getDeliveryStaffByCode(request.getOriginDeliveryStaffCode()).getFullName();
			FlowUtils.checkdeliveryStaffFlow(runtimeService, request.getOriginDeliveryStaffCode(), deliveryStaffName);
			
			//检查是否存在流程已通过，但未生效的转岗或者离职或者调级流程
			ValidateUtility.validateHistoricStationManagerEntryProcess(historyService, request.getOriginDeliveryStaffCode());
			ValidateUtility.validateHistoricDeliveryStaffDismissionProcess(historyService, request.getOriginDeliveryStaffCode());
			ValidateUtility.validateHistoricDeliveryStaffGradeProcess(historyService, request.getOriginDeliveryStaffCode());
		}
		
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		
		stationManagerHandler.startEntryProcess(employee, request);
		
		return new ServiceResponse();
	}
	

	
	

	
	@Override
	public ServiceResponse humanResourceApprove(ApproveRequest request) {
		
		processHandler.completeTask(request, StationManagerEntryConstants.KEY_HUMAN_RESOURCE_APPROVED);
		
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse humanResourceEntryApprove(ApproveRequest request) throws Exception {
		
		onCheck(request);
		
		Map<String, Object> processVariables = new HashMap<>();
		processVariables.put(StationManagerEntryConstants.KEY_ENTRY_MODIFY, true);
		
		processHandler.completeTask(request, processVariables, StationManagerEntryConstants.KEY_HUMAN_RESOURCE_ENTRY_APPROVED);
		
		return new ServiceResponse();

	}
	
	@Override
	public ServiceResponse modifyHeadCountApplication(StationManagerHeadCountModifyRequest request) throws Exception {
		
		Task task = taskService.createTaskQuery().taskId(request.getTaskId()).singleResult();
		if (task == null) {
			throw new EntityNotExistException("taskId=" + request.getTaskId(), "任务");
		}
		
		Map<String, Object> variables = runtimeService.getVariables(task.getExecutionId());
		
		Boolean jobTransfer = (Boolean) variables.get(StationManagerEntryConstants.KEY_JOB_TRANSFER);
		//如果是历史流程，不含jobTranser变量，设定初始值为false，即不是转岗，按照入职流程对待
		if (jobTransfer == null) {
			jobTransfer = false;
		}
		//如果选择通过，校验身份证
		if(request.getReapply()){
			//如果是转岗，通过originDeliveryStaffCode得到deliveryStaffId,如果不是转岗，通过deliveryStaffCode得到deliveryStaffId
			String deliveryStaffId = null;
			if(jobTransfer){
				deliveryStaffId = (String) variables.get("originDeliveryStaffCode");
			}else{
				deliveryStaffId = (String) variables.get("deliveryStaffCode");
			}
			String identityCard = request.getIdentityCard();
			ValidateParamResponse validateParamResponse = cdStaffInternalService.validateDeliveryStaffByIdentityCardNumber(deliveryStaffId, identityCard);
			Boolean success = validateParamResponse.getSuccess();
			if (!success){
				throw new DuplicatedIdentityCardNumberException();
			}
		}
		processHandler.completeTask(request, StationManagerEntryConstants.KEY_HEAD_COUNT_REAPPLY);
		
		return new ServiceResponse();
	}
	
	@Override
	public ServiceResponse modifyEntryApplication(StationManagerEntryModifyRequest request) throws Exception {
		
		Task task = taskService.createTaskQuery().taskId(request.getTaskId()).singleResult();
		if (task == null) {
			throw new EntityNotExistException("taskId=" + request.getTaskId(), "任务");
		}
		
		Map<String, Object> variables = runtimeService.getVariables(task.getExecutionId());
		
		Boolean jobTransfer = (Boolean) variables.get(StationManagerEntryConstants.KEY_JOB_TRANSFER);
		
		//如果是历史流程，不含jobTranser变量，设定初始值为false，即不是转岗，按照入职流程对待
		if (jobTransfer == null) {
			jobTransfer = false;
		}
		
		processHandler.completeTask(request, StationManagerEntryConstants.KEY_ENTRY_REAPPLY);
		
		return new ServiceResponse();
	}

	@Override
	public ServiceResponse entryApplication(StationManagerEntryRequest request) {
		
		processHandler.completeTask(request, StationManagerEntryConstants.KEY_ENTRY_REAPPLY);
		
		return new ServiceResponse();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServiceResponse sendMobileVerificationCode(SendMobileVerificationCodeRequest verificationRequest) throws Exception {
		String mobile = verificationRequest.getMobile();
		String verificationCode = RandomGenerator.getSixDigits() ;
		//发送验证码
		messageHandler.sendMobileVerificationCode(mobile, verificationCode);
		//存入tomcat缓存
		HashMap<String,VerificationCodeBean> mobileVerificationCodeMap = null;
		if ((mobileVerificationCodeMap  = (HashMap<String,VerificationCodeBean>)GeneralSessionContextAccessor.getAttribute(SessionKeyConstants.DELIVERY_STAFF_ENTRY_MOBILE_VERIFICATION_CODE)) == null) {
			mobileVerificationCodeMap = new HashMap<String,VerificationCodeBean>();
		}
		mobileVerificationCodeMap.put(mobile,new VerificationCodeBean(verificationCode, new Date()));
		GeneralSessionContextAccessor.setAttribute(SessionKeyConstants.DELIVERY_STAFF_ENTRY_MOBILE_VERIFICATION_CODE, mobileVerificationCodeMap);
		return new ServiceResponse();
	}
	
	private void onCheck(ApproveRequest request) throws Exception {
		Task task = taskService.createTaskQuery().taskId(request.getTaskId()).singleResult();
		Map<String, Object> variables = runtimeService.getVariables(task.getExecutionId());
		StationManagerEntryDetailVO detail = ProcessUtility.getProcessObject(variables, StationManagerEntryDetailVO.class);
		
		if (detail.getJobTransfer()) {
			DeliveryStaffSO deliveryStaff = deliveryStaffHandler.getDeliveryStaffById(detail.getOriginDeliveryStaffCode());
			EmployeeSO employee = deliveryStaff.getEmployeePosition().getEmployee();
			// 判断离岗
			if (EmployeePositionStatusSO.OUT_OF_POSITION.getCode().equals(deliveryStaff.getEmployeePosition().getStatusCode())) {
				String messageKey = request.getApproved() ? "process.deliverystaff.judge.dimission.pass" : "process.deliverystaff.judge.dimission.notpass";
				String dialogMsg = MessageSourceManager.getMessage(messageKey);
				
				if (request.getApproved()) {
					request.setApproved(false);
				}
				
				Map<String, Object> processVariables = new HashMap<>();
				processVariables.put(StationManagerEntryConstants.KEY_ENTRY_MODIFY, false);
				processHandler.completeTask(request, processVariables, StationManagerEntryConstants.KEY_HUMAN_RESOURCE_ENTRY_APPROVED);
				
				String errorMsg = String.format("Delivery staff %s current is dismission。", employee.getFullName());
				throw new ProcessApproveErrorException(errorMsg, dialogMsg);
			}
		}
	}

}
