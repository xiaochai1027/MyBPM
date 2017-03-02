package com.i360r.bpm.engine;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.BaseEntityEventListener;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.repository.ProcessDefinition;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.util.BeanUtility;
import com.i360r.bpm.service.rs.process.housing.contract.api.HousingContractCreationConstants;
import com.i360r.bpm.service.rs.process.housingrentreimburse.api.HousingRentReimburseConstants;

public class TaskAssignedEventListener extends BaseEntityEventListener {

    public TaskAssignedEventListener() {
        super(false, TaskEntity.class);
    }

    @Override
    protected void onEntityEvent(ActivitiEvent event) {
        if (event.getType() == ActivitiEventType.TASK_ASSIGNED) {
            // 直接注入有循环引用问题，改成这样获取
            TaskEntity entity = (TaskEntity) ((ActivitiEntityEvent) event).getEntity();

            Map<String, Object> variables = new HashMap<String, Object>();

            // 查询流程定义
            String processDefinitionId = ((ActivitiEntityEvent) event).getProcessDefinitionId();
            ProcessDefinition pd = BeanUtility.getRepositoryService().createProcessDefinitionQuery()
                    .processDefinitionId(processDefinitionId).singleResult();

            //房屋新租流程和房租申请流程
            //大区总监根据城市经理查找负责人而不是根据行政经理
            if ((pd.getKey().equals(HousingRentReimburseConstants.PROCESS_DEFINITION_KEY)
                    || pd.getKey().equals(HousingContractCreationConstants.PROCESS_DEFINITION_KEY))
                    && (entity.getTaskDefinitionKey().equals(HousingRentReimburseConstants.TASK_DIRECT_MANAGEMENT_ADMIN_MANAGER_APPROVE)
                    || entity.getTaskDefinitionKey().equals(HousingContractCreationConstants.TASK_DIRECT_MANAGEMENT_ADMIN_MANAGER_APPROVE))) {

                // 查询流程变量
                String executionId = ((ActivitiEntityEvent) event).getExecutionId();
                Map<String, Object> processVariables = BeanUtility.getRuntimeService().getVariables(executionId);

                variables.put(ProcessConstants.KEY_LAST_PROCESSOR_CODE,
                        processVariables.get(ProcessConstants.KEY_LAST_PROCESSOR_CODE));
            } else {
                variables.put(ProcessConstants.KEY_LAST_PROCESSOR_CODE, entity.getAssignee());
            }

            BeanUtility.getRuntimeService().setVariables(entity.getExecutionId(), variables);
            BeanUtility.getMessageNotifyHandler().notifyTaskAssigned(entity);
        }
    }
}
