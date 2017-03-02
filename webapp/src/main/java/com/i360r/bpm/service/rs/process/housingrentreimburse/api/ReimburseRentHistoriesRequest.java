package com.i360r.bpm.service.rs.process.housingrentreimburse.api;

import com.i360r.framework.common.service.rs.api.PagingRequest;

public class ReimburseRentHistoriesRequest extends PagingRequest {

	private String processInstanceId;

	private String businessAreaCode;
	private String cityCode;

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getBusinessAreaCode() {
		return businessAreaCode;
	}

	public void setBusinessAreaCode(String businessAreaCode) {
		this.businessAreaCode = businessAreaCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

}
