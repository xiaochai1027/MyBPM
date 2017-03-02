package com.i360r.bpm.service.rs.process.store.account.wallet.withdraw.api;

import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessVariable;

public class StoreAccountWalletWithdrawVO {
	@ProcessVariable
	private String storeAccountId;
	@ProcessVariable
	private String storeAccountMobile;
	@ProcessVariable
	private BigDecimal withdrawAmount;
	@ProcessVariable
	private BigDecimal storeAccountWalletAmount;
	@ProcessVariable
	private String withdrawAccount;
	@ProcessVariable
	private String failedReason;
	@ProcessVariable
	private String payee;
	@ProcessVariable
	private String payChannelCode;
	@ProcessVariable
	private String payChannelName;
	
	public String getStoreAccountId() {
		return storeAccountId;
	}
	public void setStoreAccountId(String storeAccountId) {
		this.storeAccountId = storeAccountId;
	}
	public String getStoreAccountMobile() {
		return storeAccountMobile;
	}
	public void setStoreAccountMobile(String storeAccountMobile) {
		this.storeAccountMobile = storeAccountMobile;
	}
	public BigDecimal getWithdrawAmount() {
		return withdrawAmount;
	}
	public void setWithdrawAmount(BigDecimal withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	public BigDecimal getStoreAccountWalletAmount() {
		return storeAccountWalletAmount;
	}
	public void setStoreAccountWalletAmount(BigDecimal storeAccountWalletAmount) {
		this.storeAccountWalletAmount = storeAccountWalletAmount;
	}
	public String getWithdrawAccount() {
		return withdrawAccount;
	}
	public void setWithdrawAccount(String withdrawAccount) {
		this.withdrawAccount = withdrawAccount;
	}
	public String getFailedReason() {
		return failedReason;
	}
	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String getPayChannelCode() {
		return payChannelCode;
	}
	public void setPayChannelCode(String payChannelCode) {
		this.payChannelCode = payChannelCode;
	}
	public String getPayChannelName() {
		return payChannelName;
	}
	public void setPayChannelName(String payChannelName) {
		this.payChannelName = payChannelName;
	}

}
