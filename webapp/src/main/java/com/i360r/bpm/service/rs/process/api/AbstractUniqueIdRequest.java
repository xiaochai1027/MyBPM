package com.i360r.bpm.service.rs.process.api;

import com.i360r.bpm.business.model.ProcessVariable;

public class AbstractUniqueIdRequest {

	@ProcessVariable(isUnique=true, showName="编号已存在！")
	private String uniqueId;
	
	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
}
