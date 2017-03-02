package com.i360r.bpm.business.handler.message.api;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import com.i360r.bpm.data.dao.model.RoleType;

public interface IMessageNotifyHandler {
	public void notifyApplierNotPass(DelegateExecution execution) throws Exception;
	
	public void notifyApplierPass(DelegateExecution execution) throws Exception;
	
	public void notifyTaskAssigned(TaskEntity entity);
	
	public void notifyTaskAssigned(TaskEntity entity, List<String> groupId);
	
	public void notifyTaskCreatorModified(String assigneeCode,ProcessInstance pi) throws Exception;
	
	public void notifyTaskDelete(String assigneeCode, Task task) throws Exception;
	
	public void sendMobileVerificationCode(String mobile,String verificationCode) throws Exception;
	
	public void sendAlertMessage(String name,String mobile,String Date,RoleType roleType);
	
	public void sendNoticeMessage(String mobile, String processInstanceId, String processInstanceName);
	
}
