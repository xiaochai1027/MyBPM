package com.i360r.bpm.service.rs.process.store.account.wallet.withdraw.api;

import java.math.BigDecimal;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class StoreAccountWalletWithdrawRequest {

	@ProcessVariable(isKeyword=true, keywordOrder=2)
	@NotNull
	private BigDecimal withdrawAmount;
	@ProcessVariable
	@NotNull
	private String withdrawAccount;
	@ProcessVariable
	@NotNull
	private String payee;
	@ProcessVariable
	@NotNull
	private String payChannelCode;
	@ProcessVariable
	@NotNull
	private String payChannelName;
	@ProcessVariable(isKeyword=true, keywordOrder=1)
	private String storeAccountId;
	
	public BigDecimal getWithdrawAmount() {
		return withdrawAmount;
	}
	public void setWithdrawAmount(BigDecimal withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	public String getWithdrawAccount() {
		return withdrawAccount;
	}
	public void setWithdrawAccount(String withdrawAccount) {
		this.withdrawAccount = withdrawAccount;
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
	public String getStoreAccountId() {
		return storeAccountId;
	}
	public void setStoreAccountId(String storeAccountId) {
		this.storeAccountId = storeAccountId;
	}
}
