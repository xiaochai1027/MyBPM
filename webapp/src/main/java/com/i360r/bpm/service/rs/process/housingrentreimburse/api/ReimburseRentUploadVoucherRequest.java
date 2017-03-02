package com.i360r.bpm.service.rs.process.housingrentreimburse.api;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.ITaskRequest;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class ReimburseRentUploadVoucherRequest implements ITaskRequest {

	@NotNull
	private String taskId;
	
	@ProcessVariable
	@NotNull
	private String attachmentUrl;
	
	@ProcessVariable
	@NotNull
	private String origAttachmentUrl;

	@Override
	public String getTaskId() {
		return taskId;
	}

	@Override
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	public String getOrigAttachmentUrl() {
		return origAttachmentUrl;
	}

	public void setOrigAttachmentUrl(String origAttachmentUrl) {
		this.origAttachmentUrl = origAttachmentUrl;
	}

}
