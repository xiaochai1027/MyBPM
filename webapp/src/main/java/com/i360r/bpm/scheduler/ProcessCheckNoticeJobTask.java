package com.i360r.bpm.scheduler;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.employee.api.IEmployeeHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.util.BeanUtility;
import com.i360r.bpm.business.util.DateTimeUtility;
import com.i360r.framework.log.Log;
import com.i360r.framework.scheduler.AbstractStatefulJobTask;
import com.i360r.framework.util.CollectionUtility;

public class ProcessCheckNoticeJobTask extends AbstractStatefulJobTask{
	private static final long serialVersionUID = 1L;
	private static final Log LOG = Log.getLog(ProcessCheckNoticeJobTask.class);
	
	@Autowired
	private IEmployeeHandler employeeHandler;
	
	@Override
	public void run() throws Exception {
		LOG.info("ProcessCheckNoticeJobTask start...");
		
		HistoryService historyService = BeanUtility.getHistoryService();
		HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
		IMessageNotifyHandler messageHandler = BeanUtility.getMessageNotifyHandler();
		query.unfinished();
		List<HistoricProcessInstance> hips = query.list();
		if (!CollectionUtility.isEmpty(hips)) {
			for (HistoricProcessInstance hip : hips) {
				List<Integer> interval = Arrays.asList(35,45);
				int days = DateTimeUtility.daysBetween(hip.getStartTime(), new Date());
				if (interval.contains(days)) {
					TaskService taskService = BeanUtility.getTaskService();
					Task task = taskService.createTaskQuery().processInstanceId(hip.getId()).active().singleResult();
					String assignee = task.getAssignee();
					String startUserId = hip.getStartUserId();
					
					if (!StringUtils.isEmpty(assignee) || !StringUtils.isEmpty(startUserId)) {
						String employeePositionCode = null;
						
						if (!StringUtils.isEmpty(startUserId)) {
							employeePositionCode = startUserId;
						}
						if (!StringUtils.isEmpty(assignee)) {
							employeePositionCode = assignee;
						}
						messageHandler.sendNoticeMessage(employeePositionCode, hip.getId(), hip.getName());
						
					}
				}
			}
		}
		LOG.info("ProcessCheckNoticeJobTask end...");
	}

}
