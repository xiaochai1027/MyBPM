package com.i360r.bpm.service.rs.process.cityReimburse.api;

import com.i360r.framework.common.service.rs.api.PagingRequest;

public class CityReimburseHistoryRequest extends PagingRequest{
	private String cityCode;
	private String processInstanceId;

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	
}
