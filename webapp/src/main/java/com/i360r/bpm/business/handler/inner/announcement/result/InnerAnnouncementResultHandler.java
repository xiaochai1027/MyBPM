package com.i360r.bpm.business.handler.inner.announcement.result;

import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.i360r.bpm.business.handler.inner.announcement.api.IInnerAnnouncementHandler;
import com.i360r.bpm.business.handler.message.api.IMessageNotifyHandler;
import com.i360r.bpm.business.handler.process.api.IProcessResultHandler;
import com.i360r.bpm.engine.AbstractEngineHandler;
import com.i360r.bpm.service.rs.process.inner.announcement.InnerAnnouncementConstants;
import com.i360r.framework.log.Log;

public class InnerAnnouncementResultHandler extends AbstractEngineHandler implements IProcessResultHandler {
	
	private static final Log LOG = Log.getLog(InnerAnnouncementResultHandler.class);
	
	@Autowired
	private IMessageNotifyHandler messageHandler;
	
	@Autowired
	private IInnerAnnouncementHandler innerAnnouncementyHandler;

	@Override
	public void onProcessPass(DelegateExecution execution) throws Exception {
		LOG.info("Inner Announcement approved");
		
		Map<String, Object> processVariables = getRuntimeService().getVariables(execution.getId());	
		innerAnnouncementyHandler.createInnerAnnouncement(processVariables);
		messageHandler.notifyApplierPass(execution);
		
	}

	@Override
	public void onProcessNotPass(DelegateExecution execution) throws Exception {
		LOG.info("Inner Announcement Not Approved");
		messageHandler.notifyApplierNotPass(execution);
	}

	@Override
	public String getProcessDefinitionKey() {
		return InnerAnnouncementConstants.PROCESS_DEFINITION_KEY;
	}

}
