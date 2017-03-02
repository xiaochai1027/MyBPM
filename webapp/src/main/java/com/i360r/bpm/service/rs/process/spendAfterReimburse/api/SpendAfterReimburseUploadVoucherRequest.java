package com.i360r.bpm.service.rs.process.spendAfterReimburse.api;

import java.util.List;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.ITaskRequest;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class SpendAfterReimburseUploadVoucherRequest implements ITaskRequest {
	@NotNull
	private String taskId;
	
	@NotNull
	@ProcessVariable
	private List<SpendAfterReimburseUploadVoucherVO> uploadVoucherVOs;
	
	public List<SpendAfterReimburseUploadVoucherVO> getUploadVoucherVOs() {
		return uploadVoucherVOs;
	}

	public void setUploadVoucherVOs(
			List<SpendAfterReimburseUploadVoucherVO> uploadVoucherVOs) {
		this.uploadVoucherVOs = uploadVoucherVOs;
	}

	@Override
	public String getTaskId() {
		return taskId;
	}

	@Override
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
