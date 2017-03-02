package com.i360r.bpm.service.rs.process.fixedasset.citydistributeunchecked.api;

import java.io.Serializable;

public class FixedAssetDistributeUncheckedNumberDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private String number;

	private String fixedAssetNumberPictureUrl;

	private String origFixedAssetNumberPictureUrl;

	private String remark;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getFixedAssetNumberPictureUrl() {
		return fixedAssetNumberPictureUrl;
	}

	public void setFixedAssetNumberPictureUrl(String fixedAssetNumberPictureUrl) {
		this.fixedAssetNumberPictureUrl = fixedAssetNumberPictureUrl;
	}

	public String getOrigFixedAssetNumberPictureUrl() {
		return origFixedAssetNumberPictureUrl;
	}

	public void setOrigFixedAssetNumberPictureUrl(
			String origFixedAssetNumberPictureUrl) {
		this.origFixedAssetNumberPictureUrl = origFixedAssetNumberPictureUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
