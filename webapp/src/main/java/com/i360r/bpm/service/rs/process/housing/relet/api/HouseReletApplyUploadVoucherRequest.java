package com.i360r.bpm.service.rs.process.housing.relet.api;

import java.util.List;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.ITaskRequest;
import com.i360r.bpm.service.rs.process.api.TaskRequest;

public class HouseReletApplyUploadVoucherRequest extends TaskRequest implements ITaskRequest {

	@ProcessVariable
	private List<HousingContractAttachmentVO> housingContractAttachments;

	public List<HousingContractAttachmentVO> getHousingContractAttachments() {
		return housingContractAttachments;
	}

	public void setHousingContractAttachments(
			List<HousingContractAttachmentVO> housingContractAttachments) {
		this.housingContractAttachments = housingContractAttachments;
	}
	
}
