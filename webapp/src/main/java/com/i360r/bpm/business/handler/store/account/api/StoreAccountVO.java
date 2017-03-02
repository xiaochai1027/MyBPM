package com.i360r.bpm.business.handler.store.account.api;

import java.math.BigDecimal;

public class StoreAccountVO {

	private String storeAccountMobile;
	private BigDecimal useableWallet;
	private String storeAccountStatusCode;
	
	public String getStoreAccountMobile() {
		return storeAccountMobile;
	}
	public void setStoreAccountMobile(String storeAccountMobile) {
		this.storeAccountMobile = storeAccountMobile;
	}
	public BigDecimal getUseableWallet() {
		return useableWallet;
	}
	public void setUseableWallet(BigDecimal useableWallet) {
		this.useableWallet = useableWallet;
	}
	public String getStoreAccountStatusCode() {
		return storeAccountStatusCode;
	}
	public void setStoreAccountStatusCode(String storeAccountStatusCode) {
		this.storeAccountStatusCode = storeAccountStatusCode;
	}

}
