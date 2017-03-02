package com.i360r.bpm.business.model;

import java.io.Serializable;
import java.util.Date;

public class VerificationCodeBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String verificationCode;
	private Date   updateTime;
	
	public VerificationCodeBean() {
		super();
	}

	public VerificationCodeBean(String verificationCode, Date updateTime) {
		super();
		this.verificationCode = verificationCode;
		this.updateTime = updateTime;
	}
	
	public String getVerificationCode() {
		return verificationCode;
	}
	
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
