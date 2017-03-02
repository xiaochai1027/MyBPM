package com.i360r.bpm.service.api.internal.rs.deliverystaff;

import java.util.List;

import org.activiti.engine.ManagementService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.NativeTaskQuery;
import org.activiti.engine.task.Task;
import org.apache.cxf.common.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.api.internal.rs.deliverystaffentry.DeleteDeliveryStaffEntryRequest;
import com.i360r.bpm.api.internal.rs.deliverystaffentry.IDeliveryStaffEntryTaskInternalService;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.ProcessResultHandlerManager;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.log.Log;


public class DeliveryStaffEntryTaskInternalService implements IDeliveryStaffEntryTaskInternalService {
	private static final Log LOG = Log.getLog(DeliveryStaffEntryTaskInternalService.class);

	@Autowired
	private ManagementService managementService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private ProcessResultHandlerManager processResultHandlerManager;
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@Override
	public ServiceResponse deleteDeliveryStaffEntry(DeleteDeliveryStaffEntryRequest request) throws Exception {
		
		NativeTaskQuery query = taskService.createNativeTaskQuery();
		
		StringBuilder baseSql = new StringBuilder("select distinct T.* from "+managementService.getTableName(TaskEntity.class)+" T "
				+ "inner join "+managementService.getTableName(HistoricProcessInstanceEntity.class)+	" HP on T.PROC_INST_ID_ = HP.PROC_INST_ID_ "
				+ " inner join " + managementService.getTableName(VariableInstanceEntity.class) + " VI on T.PROC_INST_ID_ = VI.PROC_INST_ID_"
				+ " where VI.NAME_ = " + "'identityCard'" + " and VI.TEXT_ like " + "'%" + request.getIdentityCard() + "%'");
		
		query.sql(baseSql.toString());
		LOG.debug(baseSql.toString());
		
		List<Task> tasks = query.list();
		if (tasks != null && tasks.size() > 0) {
			for (Task task : tasks) {
				
				List<Execution> executions = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).list();
				
				if (!CollectionUtils.isEmpty(executions)) {
					processResultHandlerManager.onProcessNotPass((ExecutionEntity)executions.get(0));
					runtimeService.deleteProcessInstance(task.getProcessInstanceId(), request.getDeleteReason());
					
					if (task.getAssignee() != null) {
					   messageHandler.notifyTaskDelete(task.getAssignee(), task);	
					}
				} else {
					LOG.error("execution not exist : " + task.getProcessInstanceId());
				}
			}
		}
		
		return new ServiceResponse();
	}
	
}
