package com.i360r.bpm.service.rs.process.housing.contract.api;

import java.util.List;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.ITaskRequest;
import com.i360r.bpm.service.rs.process.api.ReapplyTaskRequest;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class NewHouseApplyUploadVoucherRequest extends ReapplyTaskRequest implements ITaskRequest {
	@NotNull
	private String taskId;
	@ProcessVariable
	private String latestPayStartDate;
	@ProcessVariable
	private String latestPayEndDate;
	@ProcessVariable
	private Boolean isLastPay;
	@ProcessVariable
	private String nextPayDate;
	@ProcessVariable
	private String letter;
	@ProcessVariable
	private String actualPayee;
	@ProcessVariable
	private String letterBankCardNumber;
	@ProcessVariable
	private String letterInterbankNumber;
	@ProcessVariable
	private String letterBankInstitution;
	@ProcessVariable
	private String effectiveStartDate;
	@ProcessVariable
	private String effectiveEndDate;
	@ProcessVariable
	private int livedNumber;
	@ProcessVariable
	private List<HousingContractAttachmentVO> housingContractAttachments;
	@ProcessVariable
	private List<HousingDepositVO> housingDeposits;

	private String processInstanceId;
	
	@Override
	public String getTaskId() {
		return taskId;
	}

	public String getLatestPayStartDate() {
		return latestPayStartDate;
	}

	public void setLatestPayStartDate(String latestPayStartDate) {
		this.latestPayStartDate = latestPayStartDate;
	}

	public String getLatestPayEndDate() {
		return latestPayEndDate;
	}

	public void setLatestPayEndDate(String latestPayEndDate) {
		this.latestPayEndDate = latestPayEndDate;
	}

	@Override
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Boolean getIsLastPay() {
		return isLastPay;
	}

	public void setIsLastPay(Boolean isLastPay) {
		this.isLastPay = isLastPay;
	}

	public String getNextPayDate() {
		return nextPayDate;
	}

	public void setNextPayDate(String nextPayDate) {
		this.nextPayDate = nextPayDate;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public String getLetterInterbankNumber() {
		return letterInterbankNumber;
	}

	public void setLetterInterbankNumber(String letterInterbankNumber) {
		this.letterInterbankNumber = letterInterbankNumber;
	}

	public String getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(String effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public String getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(String effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	public int getLivedNumber() {
		return livedNumber;
	}

	public void setLivedNumber(int livedNumber) {
		this.livedNumber = livedNumber;
	}

	public List<HousingContractAttachmentVO> getHousingContractAttachments() {
		return housingContractAttachments;
	}

	public void setHousingContractAttachments(
			List<HousingContractAttachmentVO> housingContractAttachments) {
		this.housingContractAttachments = housingContractAttachments;
	}

	public List<HousingDepositVO> getHousingDeposits() {
		return housingDeposits;
	}

	public void setHousingDeposits(List<HousingDepositVO> housingDeposits) {
		this.housingDeposits = housingDeposits;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getLetterBankInstitution() {
		return letterBankInstitution;
	}

	public void setLetterBankInstitution(String letterBankInstitution) {
		this.letterBankInstitution = letterBankInstitution;
	}

	public String getActualPayee() {
		return actualPayee;
	}

	public void setActualPayee(String actualPayee) {
		this.actualPayee = actualPayee;
	}

	public String getLetterBankCardNumber() {
		return letterBankCardNumber;
	}

	public void setLetterBankCardNumber(String letterBankCardNumber) {
		this.letterBankCardNumber = letterBankCardNumber;
	}

	
}
