package com.i360r.bpm.webapp.service.jms.topic.employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.i360r.bpm.business.exception.EntityNotExistException;
import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.util.BeanUtility;
import com.i360r.cds.api.internal.rs.employee.position.EmployeePositionsResponse;
import com.i360r.cds.api.internal.rs.employee.position.IEmployeePositionInternalService;
import com.i360r.framework.log.Log;
import com.i360r.jms.api.internal.topic.employee.AbstractEmployeeTopicListener;
import com.i360r.jms.api.internal.topic.employee.DimissEmployeeMO;
import com.i360r.jms.api.internal.topic.employee.ModifyEmployeePositionMO;

public class EmployeeTopicListener extends AbstractEmployeeTopicListener{
	
	private static Log LOG = Log.getLog(EmployeeTopicListener.class);
	
	@Resource
	private IEmployeePositionInternalService employeePositionInternalServiceClient;
	
	@Override
	public void onDimission(DimissEmployeeMO dimissEmployeeMO) {
		
		try{
			List<String> employeePositionCodes = new ArrayList<String>();
			employeePositionCodes = dimissEmployeeMO.getEmployeePositionIds();
			if (CollectionUtils.isEmpty(employeePositionCodes)) {
				throw new IllegalArgumentException();
			}
			
			for (String employeePositionCode : employeePositionCodes) {
				
				String superEmployeePositionCode = getSuperEmployeePositionCode(employeePositionCode);
				if (StringUtils.isEmpty(superEmployeePositionCode)) {
					throw new EntityNotExistException("employeePositionCode = " + employeePositionCode, "该岗位无上级");
				}
				
				modifyTaskAssignee(employeePositionCode, superEmployeePositionCode);
				addTaskResponsiblePerson(employeePositionCode, superEmployeePositionCode);
			}
		} catch (Exception e) {
			LOG.error("notify bpm after dismissing deliverystaff failed!"+e.getMessage());
		}
	}
	
	@Override
	public void onModifyPosition(ModifyEmployeePositionMO modifyEmployeePositionMO) {
		
		try {
			if (StringUtils.isBlank(modifyEmployeePositionMO.getOldEmployeePositionProperty().getEmployeePositionId())
					|| StringUtils.isBlank(modifyEmployeePositionMO.getOldEmployeePositionProperty().getEmployeePositionQuitDate())) {
				return;
			}
			String employeePositionId = modifyEmployeePositionMO.getOldEmployeePositionProperty().getEmployeePositionId();

			String superEmployeePositionCode = getSuperEmployeePositionCode(employeePositionId);
			if (StringUtils.isEmpty(superEmployeePositionCode)) {
				throw new EntityNotExistException("employeePositionCode = " + employeePositionId, "该岗位无上级");
			}
			
			modifyTaskAssignee(employeePositionId, superEmployeePositionCode);
			addTaskResponsiblePerson(employeePositionId, superEmployeePositionCode);

		} catch (Exception e) {
			LOG.error("bpm update task assignee and pending task to super failed", e);
		}
	}
	
	private String getSuperEmployeePositionCode(String employeePositionCode) {
		EmployeePositionsResponse response = employeePositionInternalServiceClient.searchSuperiorEmployeePosition(employeePositionCode);
		
		if (!CollectionUtils.isEmpty(response.getEmployeePositons()) && StringUtils.isNotBlank(response.getEmployeePositons().get(0).getId())) {
			return response.getEmployeePositons().get(0).getId();
		}
		return null;
	}
	
	private void modifyTaskAssignee(String employeePositionCode, String superEmployeePositionCode) {
		TaskService service = BeanUtility.getTaskService();
		TaskQuery query = service.createTaskQuery().taskAssignee(employeePositionCode).active();
		List<Task> tasks = query.list();
		for (Task task : tasks) {
			service.setAssignee(task.getId(), superEmployeePositionCode);
		}
	}
	
	private void addTaskResponsiblePerson(String employeePositionCode, String responsible) {
		HistoryService service = BeanUtility.getHistoryService();
		HistoricProcessInstanceQuery query = service.createHistoricProcessInstanceQuery().startedBy(employeePositionCode);
		List<HistoricProcessInstance> hips = query.unfinished().list();
		
		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put(ProcessConstants.KEY_RESPONSIBLE, responsible);
		RuntimeService runtimeService = BeanUtility.getRuntimeService();
		
		for (HistoricProcessInstance hip : hips) {
			String excutionId = BeanUtility.getTaskService().createTaskQuery().processInstanceId(hip.getId()).singleResult().getExecutionId();
			runtimeService.setVariables(excutionId, variable);
		}
	}

}
