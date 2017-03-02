package com.i360r.bpm.data.dao.impl.history;

import java.util.Date;

import org.activiti.engine.impl.cfg.IdGenerator;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.history.DefaultHistoryManager;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.HistoricActivityInstanceEntity;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;

public class CustomHistoryManager extends DefaultHistoryManager {

	@Override
	public void recordProcessInstanceStart(ExecutionEntity processInstance) {
	    if(isHistoryLevelAtLeast(HistoryLevel.ACTIVITY)) {
	      HistoricProcessInstanceEntity historicProcessInstance = new HistoricProcessInstanceEntity(processInstance);
	      
	      historicProcessInstance.setName(processInstance.getProcessDefinition().getName());
	      
	      // Insert historic process-instance
	      getDbSqlSession().insert(historicProcessInstance);
	  
	      // Also record the start-event manually, as there is no "start" activity history listener for this
	      IdGenerator idGenerator = Context.getProcessEngineConfiguration().getIdGenerator();
	      
	      String processDefinitionId = processInstance.getProcessDefinitionId();
	      String processInstanceId = processInstance.getProcessInstanceId();
	      String executionId = processInstance.getId();
	  
	      HistoricActivityInstanceEntity historicActivityInstance = new HistoricActivityInstanceEntity();
	      historicActivityInstance.setId(idGenerator.getNextId());
	      historicActivityInstance.setProcessDefinitionId(processDefinitionId);
	      historicActivityInstance.setProcessInstanceId(processInstanceId);
	      historicActivityInstance.setExecutionId(executionId);
	      historicActivityInstance.setActivityId(processInstance.getActivityId());
	      historicActivityInstance.setActivityName((String) processInstance.getActivity().getProperty("name"));
	      historicActivityInstance.setActivityType((String) processInstance.getActivity().getProperty("type"));
	      Date now = Context.getProcessEngineConfiguration().getClock().getCurrentTime();
	      historicActivityInstance.setStartTime(now);
	      
	      // Inherit tenant id (if applicable)
	      if (processInstance.getTenantId() != null) {
	      	historicActivityInstance.setTenantId(processInstance.getTenantId());
	      }
	      
	      getDbSqlSession().insert(historicActivityInstance);
	    }
	  }
	
}
