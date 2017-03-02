package com.i360r.bpm.service.rs.process.spendAfterReimburse.api;

import java.util.List;

import com.i360r.framework.common.service.rs.api.ServiceResponse;

public class SpendAfterReimburseVoucherDetailResponse extends ServiceResponse {
	private static final long serialVersionUID = 1L;
	
	private List<SpendAfterReimburseUploadVoucherVO> uploadVoucherVOs;

	public List<SpendAfterReimburseUploadVoucherVO> getUploadVoucherVOs() {
		return uploadVoucherVOs;
	}

	public void setUploadVoucherVOs(
			List<SpendAfterReimburseUploadVoucherVO> uploadVoucherVOs) {
		this.uploadVoucherVOs = uploadVoucherVOs;
	}
}
