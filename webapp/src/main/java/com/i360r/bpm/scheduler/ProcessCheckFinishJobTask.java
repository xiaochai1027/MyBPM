package com.i360r.bpm.scheduler;

import java.util.Date;
import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.apache.cxf.common.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.handler.process.ProcessResultHandlerManager;
import com.i360r.bpm.business.util.BeanUtility;
import com.i360r.bpm.business.util.DateTimeUtility;
import com.i360r.bpm.service.rs.process.deliverystaffentry.DeliveryStaffEntryConstants;
import com.i360r.framework.log.Log;
import com.i360r.framework.scheduler.AbstractStatefulJobTask;

public class ProcessCheckFinishJobTask extends AbstractStatefulJobTask {
	private static final long serialVersionUID = 6697533888949383874L;
	private static final Log LOG = Log.getLog(ProcessCheckFinishJobTask.class);
	
	@Autowired
	private ProcessResultHandlerManager resultManager;
	
	@Override
	public void run() {
		LOG.info("ProcessCheckFinishJobTask Start ....");
		HistoryService historyService = BeanUtility.getHistoryService();
		RuntimeService runtimeService = BeanUtility.getRuntimeService();
		TaskService taskService = BeanUtility.getTaskService();
		HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
		query = query.processDefinitionKey(DeliveryStaffEntryConstants.PROCESS_DEFINITION_KEY);
		query = query.variableValueEquals(DeliveryStaffEntryConstants.KEY_JOB_TRANSFER,Boolean.FALSE);
		query.unfinished();
		List<HistoricProcessInstance>  historicProcessInstances = query.list();
		if (!CollectionUtils.isEmpty(historicProcessInstances)) {
			for (HistoricProcessInstance hisPI : historicProcessInstances) {
				int interval = DateTimeUtility.daysBetween(hisPI.getStartTime(), new Date());
				String processInstanceId = hisPI.getId();
			    if (interval >= 31) {
						try {
							Task task = taskService.createTaskQuery().processInstanceId(hisPI.getId()).active().singleResult();
							List<Execution> executions = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).list();
							if (!CollectionUtils.isEmpty(executions)) {
								runtimeService.setVariable(((ExecutionEntity)executions.get(0)).getId(), ProcessConstants.KEY_PASS, false);
								runtimeService.deleteProcessInstance(processInstanceId,"30天入职流程未走完自动结束流程");
							} else {
								LOG.error("execution not exist : " + processInstanceId);
							}
						} catch (Exception e) {
							LOG.error("Process({" + processInstanceId + "}) finish failed !", e);
						}
				}
			}
		}
		LOG.info("ProcessCheckFinishJobTask Completed !");
	}
	
}
