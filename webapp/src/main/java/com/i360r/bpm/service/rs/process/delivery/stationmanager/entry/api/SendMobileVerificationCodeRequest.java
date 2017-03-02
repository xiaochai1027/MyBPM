package com.i360r.bpm.service.rs.process.delivery.stationmanager.entry.api;

import com.i360r.framework.service.aop.validate.field.annotation.MobileFormat;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;


public class SendMobileVerificationCodeRequest {
	
	@MobileFormat(required = true)
	@NotNull
	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
