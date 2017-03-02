package com.i360r.bpm.service.rs.process.housing.relet.api;

import java.io.Serializable;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class HousingContractAttachmentVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ProcessVariable
	@NotNull
	private int sequence;
	@ProcessVariable
	private String attachmentUrl;
	@ProcessVariable
	private String origAttachmentUrl;

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
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
