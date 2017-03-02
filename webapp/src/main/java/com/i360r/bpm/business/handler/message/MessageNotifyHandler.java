package com.i360r.bpm.business.handler.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.exception.EntityNotExistException;
import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.util.ProcessNotifyMessageEventSendUtility;
import com.i360r.bpm.business.util.ProcessNotifyMessageEventSendUtility.Receiver;
import com.i360r.bpm.business.util.ServiceClientUtility;
import com.i360r.bpm.data.dao.model.RoleType;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.cds.api.internal.rs.employee.EmployeeResponse;
import com.i360r.cds.api.internal.rs.employee.EmployeeSO;
import com.i360r.cds.api.internal.rs.employee.EmployeesResponse;
import com.i360r.cds.api.internal.rs.employee.IEmployeeInternalService;
import com.i360r.cds.api.internal.rs.employee.SearchEmployeeRequest;
import com.i360r.cds.api.internal.rs.employee.position.EmployeePositionSO;
import com.i360r.cds.api.internal.rs.employee.position.EmployeePositionStatusSO;
import com.i360r.cds.api.internal.rs.employee.position.PositionSO;
import com.i360r.cds.api.internal.rs.location.LocationSO;
import com.i360r.framework.context.SessionContextAccessor;
import com.i360r.framework.extension.context.model.EmployeeCO;
import com.i360r.framework.i18n.MessageSourceManager;
import com.i360r.framework.log.Log;
import com.i360r.framework.model.Location;
import com.i360r.framework.model.util.LocationUtility;
import com.i360r.framework.service.StatusCodeManager;
import com.i360r.framework.util.CollectionUtility;
import com.i360r.module.message.api.IMessager;
import com.i360r.module.message.api.TemplateMessageMO;
import com.i360r.module.message.sms.SmsTypeMO;

public class MessageNotifyHandler extends AbstractEngineHandler implements IMessageNotifyHandler {
	
	private static Log LOG = Log.getLog(MessageNotifyHandler.class);
	
	@Resource
	private IEmployeeInternalService employeeInternalServiceClient;
	
	@Autowired
	private IMessager messager;
	
	@Autowired
	private IEmployeeHandler employeeHandler;
	
	// 您提交的{0}申请被驳回，编号：{1}，详情请查看后台我的任务。
	@Override
	public void notifyApplierNotPass(DelegateExecution execution) throws Exception {
		
		ProcessDefinition pd = getRepositoryService().createProcessDefinitionQuery()
				.processDefinitionId(execution.getProcessDefinitionId()).singleResult();
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		
		String createdByCode = (String)processVariables.get(ProcessConstants.KEY_CREATED_BY_CODE);

        String noPass = MessageSourceManager.getMessage("msg.task.nopass");
		
		sendMessageEventByEmployeePositionId(createdByCode, "biz.flow.apply.result", Arrays.asList(pd.getName(), noPass, execution.getProcessInstanceId()));
		
	}
	
	// 您提交的{0}申请已批准，编号：{1}。
	@Override
	public void notifyApplierPass(DelegateExecution execution) throws Exception {
		
		ProcessDefinition pd = getRepositoryService().createProcessDefinitionQuery()
				.processDefinitionId(execution.getProcessDefinitionId()).singleResult();
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());
		
		String createdByCode = (String)processVariables.get(ProcessConstants.KEY_CREATED_BY_CODE);

		String pass = MessageSourceManager.getMessage("msg.task.pass");
		
		sendMessageEventByEmployeePositionId(createdByCode, "biz.flow.apply.result", Arrays.asList(pd.getName(), pass, execution.getProcessInstanceId()));
		
	}
	
	@Override
	public void notifyTaskAssigned(TaskEntity entity) {
		
		ProcessDefinition pd = getRepositoryService().createProcessDefinitionQuery()
				.processDefinitionId(entity.getProcessDefinitionId()).singleResult();
		
		String assignee = entity.getAssignee();
		
		EmployeePositionSO employeePositionsSO = employeeHandler.getEmployeePositionByEmployeePositionCode(assignee);
		String employeeId = employeePositionsSO.getEmployee().getId();
		String assigneeMobile = employeePositionsSO.getEmployee().getEmployeePassport().getMobile();
		
		Receiver receiver = new Receiver();
		receiver.setMobile(assigneeMobile);
		receiver.setEmployeeId(employeeId);
		
		String positionName = employeePositionsSO.getPosition().getName();
		String taskType = MessageSourceManager.getMessage("msg.task.type.todo");
		ProcessNotifyMessageEventSendUtility.sendTemplateNotifyMessage("biz.flow.task.assigned", Arrays.asList(positionName, taskType, pd.getName()), receiver);
		
	}
	
	@Override
	public void notifyTaskAssigned(TaskEntity entity, List<String> groupIds) {
		
		Set<String> employees = new HashSet<>();
		String taskType = MessageSourceManager.getMessage("msg.task.type.candidate");
		ProcessDefinition pd = getRepositoryService().createProcessDefinitionQuery().processDefinitionId(entity.getProcessDefinitionId()).singleResult();
		
		for (String groupId: groupIds) {
			SearchEmployeeRequest  request = new SearchEmployeeRequest();
			List<String> positionCodes = new ArrayList<String>();
			positionCodes.add(groupId);
			request.setPositionCodes(positionCodes);
			request.setEmployeePositionStatusCode(EmployeePositionStatusSO.IN_POSITION.getCode());
			request.setPaged(false);
			
			EmployeesResponse response = employeeInternalServiceClient.searchEmployee(request);
			List<EmployeeSO> employeeSOs = response.getEmployees();
			LOG.info("bpm - MessageHandler - notifyTaskAssigned: employeeSOInfo={}", employeeSOs);
			
			Map<String, Object> processVariables = getRuntimeService().getVariables(entity.getExecutionId());
			String processLocationCode = (String)processVariables.get(ProcessConstants.KEY_LOCATION_CODE);
			List<String> processLocationCodes = null;
			if (!StringUtils.isEmpty(processLocationCode)) {
				Location location = new Location(processLocationCode);
				processLocationCodes = LocationUtility.getParentCodes(location.getCode(), true);
			} 
			
			for (EmployeeSO employeeSO : employeeSOs) {
				EmployeeSO so = getEmployeeSOByPositionCode(employeeSO, groupId, processLocationCodes);
				if (so == null || CollectionUtility.isEmpty(so.getEmployeePositions())) {
					continue;
				}
				String positionName = groupId;
				String mobile = "";
				if (so.getEmployeePassport() != null) {
					mobile = so.getEmployeePassport().getMobile();
				} else {
					throw new EntityNotExistException("employeeId = " + so.getId(), "该员工[" + so.getFullName() + "]手机号");
				}
				
				if (!CollectionUtility.isEmpty(so.getEmployeePositions())
						&& so.getEmployeePositions().get(0).getPosition() != null) {
					positionName = so.getEmployeePositions().get(0).getPosition().getName();
				}
				
				LOG.info("bpm - MessageHandler - notifyTaskAssigned: employeeSOId={}, positionName={}, mobile={}", so.getId(), positionName, mobile);
				
				if (!employees.contains(so.getId())) {
					LOG.info("bpm - MessageHandler - notifyTaskAssigned: processLocationCodeInfos: processLocationCode={}", processLocationCode);
					if (StringUtils.isBlank(processLocationCode)) {
						employees.add(so.getId());
						Receiver rece = new Receiver();
						rece.setMobile(mobile);
						rece.setEmployeeId(so.getId());
						ProcessNotifyMessageEventSendUtility.sendTemplateNotifyMessage("biz.flow.task.assigned", Arrays.asList(positionName, taskType, pd.getName()), rece);
					} else {
                        List<String> employeeLocationCodes = new ArrayList<>();
						
						if (!CollectionUtility.isEmpty(so.getEmployeePositions())
								&& !CollectionUtility.isEmpty(so.getEmployeePositions().get(0).getLocations())) {
							for (LocationSO locationSO : so.getEmployeePositions().get(0).getLocations()) {
								employeeLocationCodes.add(locationSO.getId());
							}
						}
						//员工管辖范围对流程范围取交集,如果取完交集有值,则该员工可以收到处理这个流程的通知
						employeeLocationCodes.retainAll(processLocationCodes);
						if (employeeLocationCodes != null && !employeeLocationCodes.isEmpty()) {
							employees.add(so.getId());
							Receiver rece = new Receiver();
							rece.setMobile(mobile);
							rece.setEmployeeId(so.getId());
							ProcessNotifyMessageEventSendUtility.sendTemplateNotifyMessage("biz.flow.task.assigned", Arrays.asList(positionName, taskType, pd.getName()), rece);
						} 
						
						LOG.info("bpm - MessageHandler - notifyTaskAssigned: locationInfos:employeeLocationCodes={}, processLocationCodes={}", employeeLocationCodes, processLocationCodes);
					}
				}
			}				
		}
		
		
		LOG.info("Task assigned GroupIds={}, employees{}, isTodoTask={}.", groupIds, employees, false);
		
		if (employees.isEmpty()) {
			try {
				TemplateMessageMO message = new TemplateMessageMO();
				message.setParams(Arrays.asList(entity.getProcessInstanceId(), StringUtils.join(groupIds.toArray(), ","), pd.getName()));
				message.setTemplateCode("biz.flow.position.error");
				
				messager.sendSms(message, SmsTypeMO.ALERT, Arrays.asList("18910130726", "13717933493"));
			} catch (Exception e) {
				LOG.error("send sms error.", e);
			}
		} 
	}
	
	private EmployeeSO getEmployeeSOByPositionCode(EmployeeSO so, String positionCode, List<String> locations) {
		//过滤该员工的岗位信息(过滤出：在岗，固定positionCode信息， 固定商圈的locations)
		EmployeeSO employeeSO = new EmployeeSO();
		
		List<EmployeePositionSO> employeePositionSOs = so.getEmployeePositions();
		if (CollectionUtility.isEmpty(employeePositionSOs)) {
			return null;
		}
		
		for (EmployeePositionSO employeePositionSO : employeePositionSOs) {
			List<LocationSO> locationSOs = employeePositionSO.getLocations();
			
			List<String> employeeLocationCodes = new ArrayList<String>();
			for (LocationSO locationSO : locationSOs) {
				employeeLocationCodes.add(locationSO.getId());
			}
			employeeLocationCodes.retainAll(locations);
			
			PositionSO position = employeePositionSO.getPosition();
			if (position != null && StringUtils.isEmpty(employeePositionSO.getQuitDate())) {
				LOG.info("MessageHandler - getEmployeeSOByPositionCode : isposition={}, isemptyForLocation={}, isemptyForEmployeeLocation={}", 
						position.getCode().equals(positionCode), CollectionUtility.isEmpty(locations), !CollectionUtility.isEmpty(employeeLocationCodes));
				
				if (position.getCode().equals(positionCode) && (CollectionUtility.isEmpty(locations) || (!CollectionUtility.isEmpty(employeeLocationCodes)))) {
					List<EmployeePositionSO> employeePositions = new ArrayList<EmployeePositionSO>();
					employeePositions.add(employeePositionSO);
					employeeSO.setEmployeePositions(employeePositions);
					employeeSO.setEmployeePassport(so.getEmployeePassport());
					employeeSO.setId(so.getId());
					employeeSO.setFullName(so.getFullName());
				}
			}
		}
		
		return employeeSO;
	}
	
	@Override
	public void notifyTaskCreatorModified(String assigneeCode,ProcessInstance pi) throws Exception {
		// If the task is set up to myself, don't send messge !
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		if (employee.getEmployeePositionCode().equals(assigneeCode)) {
			return;
		}
		sendMessageEventByEmployeePositionId(assigneeCode, "biz.flow.owner.changed", Arrays.asList(pi.getId(),pi.getProcessDefinitionName()));
	}

	@Override
	public void notifyTaskDelete(String assigneeCode, Task task) throws Exception {
		// If the task is set up to myself, don't send messge !
		EmployeeCO employee = SessionContextAccessor.getCurrentAccount();
		if (employee.getEmployeePositionCode().equals(assigneeCode)) {
			return;
		}		
		sendMessageEventByEmployeePositionId(assigneeCode, "biz.flow.task.deleted", Arrays.asList(task.getId(), task.getName()));
	}
	
	private void sendMessageEventByEmployeePositionId(String employeePositionId, String templateCode, List<String> params){
	    try {
	    	EmployeeResponse response = ServiceClientUtility.getEmployeeInternalService().getEmployeeByEmployeePosition(employeePositionId);
	    	if (response == null || !response.getResultCode().equals(StatusCodeManager.getSucceedCode())) {
				LOG.error("MessageHandler.sendMessageEventByEmployeePositionId call cds response error. "
						+ "templateCode:{}, params:{}, employeePositionId:{}, response:{}", templateCode, params, employeePositionId, response);
				return;
			}
	    	
	    	Receiver receiver = new Receiver();
	    	receiver.setEmployeeId(response.getEmployee().getId());
	    	receiver.setMobile(response.getEmployee().getEmployeePassport().getMobile());
	    	ProcessNotifyMessageEventSendUtility.sendTemplateNotifyMessage(templateCode, params, receiver);
		} catch (Exception e) {
			LOG.error("MessageHandler.sendMessageEventByEmployeePositionId error.", e);
		}
	}

	@Override
	public void sendMobileVerificationCode(String mobile, String verificationCode) throws Exception {
		TemplateMessageMO message = new TemplateMessageMO();
		message.setParams(Arrays.asList(verificationCode, String.valueOf(30)));
		message.setTemplateCode("biz.mob.verify.code");

		messager.sendSms(message, SmsTypeMO.OTHER, Arrays.asList(mobile));
	}

	@Override
	public void sendAlertMessage(String name,String mobile, String endDate, RoleType roleType) {
		String templateCode = null;
		List<String> params = new ArrayList<String>();
        switch (roleType) {
		case DELIVERY_STAFF:
			templateCode = "biz.flow.entry.timeout.tmp1";
			params = Arrays.asList(endDate);
			break;
		case STATION_MANAGER:
			templateCode = "biz.flow.entry.timeout.tmp2";
			params = Arrays.asList(name, endDate);
			break;
		default:
			break;
		}
		
        try {
        	TemplateMessageMO message = new TemplateMessageMO();
    		message.setParams(params);
    		message.setTemplateCode(templateCode);

    		messager.sendSms(message, SmsTypeMO.OTHER, Arrays.asList(mobile));
		} catch (Exception e) {
			LOG.error("MessageHandler.sendAlertMessage error.", e);
		}
	}
	
	@Override
	public void sendNoticeMessage(String employeePositionCode, String processInstanceId, String processInstanceName) {
		
		String processInstance = processInstanceName + "," + processInstanceId;
		
		sendMessageEventByEmployeePositionId(employeePositionCode, "biz.flow.timeout", Arrays.asList(processInstance));
	}
	
}