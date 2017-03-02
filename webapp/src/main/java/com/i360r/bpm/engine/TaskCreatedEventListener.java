package com.i360r.bpm.engine;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.BaseEntityEventListener;
import org.activiti.engine.impl.persistence.entity.IdentityLinkEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.springframework.util.CollectionUtils;

import com.i360r.bpm.business.util.BeanUtility;


public class TaskCreatedEventListener extends BaseEntityEventListener {

	public TaskCreatedEventListener() {
		super(false, TaskEntity.class);
	}
	
	@Override
	protected void onEntityEvent(ActivitiEvent event) {
		if (event.getType() == ActivitiEventType.TASK_CREATED) {
			
			TaskEntity entity = (TaskEntity)((ActivitiEntityEvent)event).getEntity();
			
			List<IdentityLinkEntity> groups = entity.getIdentityLinks();
			if (!CollectionUtils.isEmpty(groups)) {
				List<String> groupIds = new ArrayList<String>();
				for (IdentityLinkEntity group: groups) {
					groupIds.add(group.getGroupId());
				}
				// 直接注入有循环引用问题，改成这样获取
				BeanUtility.getMessageNotifyHandler().notifyTaskAssigned(entity, groupIds);	
			}			
		}
	}			
}
