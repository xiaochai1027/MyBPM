package com.i360r.bpm.service.rs.process.deliverystaffentry.api;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.ContinueTaskRequest;


public class DeliveryStaffEntryRequest extends ContinueTaskRequest {
	
	@ProcessVariable
	private String bankCard;
	@ProcessVariable
	private String bankCardPictureUrl;
	@ProcessVariable
	private String origBankCardPictureUrl;
	@ProcessVariable
	private String contractNumber;
	@ProcessVariable
	private String contractBeginDate;
	@ProcessVariable
	private String contractEndDate;
	@ProcessVariable
	private String contractTypeCode;
	@ProcessVariable(required=true)
	private String healthCertificatePictureUrl1;
	@ProcessVariable(required=true)
	private String origHealthCertificatePictureUrl1;
	@ProcessVariable(required=true)
	private String healthCertificatePictureUrl2;
	@ProcessVariable(required=true)
	private String origHealthCertificatePictureUrl2;
	@ProcessVariable(required=true)
	private String entryDate;
	@ProcessVariable
	private String jobTransferDate;
	@ProcessVariable
	private String educationBackgroundCode;
	@ProcessVariable
	private String recruitmentChannelCode;
	@ProcessVariable
	private String educationBackgroundName;
	@ProcessVariable
	private String recruitmentChannelName;
	@ProcessVariable
	private String healthCertificateBeginDate;
	@ProcessVariable
	private String healthCertificateEndDate;
	
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getJobTransferDate() {
		return jobTransferDate;
	}
	public void setJobTransferDate(String jobTransferDate) {
		this.jobTransferDate = jobTransferDate;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getContractTypeCode() {
		return contractTypeCode;
	}
	public void setContractTypeCode(String contractTypeCode) {
		this.contractTypeCode = contractTypeCode;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	public String getBankCardPictureUrl() {
		return bankCardPictureUrl;
	}
	public void setBankCardPictureUrl(String bankCardPictureUrl) {
		this.bankCardPictureUrl = bankCardPictureUrl;
	}
	public String getHealthCertificatePictureUrl1() {
		return healthCertificatePictureUrl1;
	}
	public void setHealthCertificatePictureUrl1(String healthCertificatePictureUrl1) {
		this.healthCertificatePictureUrl1 = healthCertificatePictureUrl1;
	}
	public String getHealthCertificatePictureUrl2() {
		return healthCertificatePictureUrl2;
	}
	public void setHealthCertificatePictureUrl2(String healthCertificatePictureUrl2) {
		this.healthCertificatePictureUrl2 = healthCertificatePictureUrl2;
	}
	public String getContractBeginDate() {
		return contractBeginDate;
	}
	public void setContractBeginDate(String contractBeginDate) {
		this.contractBeginDate = contractBeginDate;
	}
	public String getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	public String getOrigBankCardPictureUrl() {
		return origBankCardPictureUrl;
	}
	public void setOrigBankCardPictureUrl(String origBankCardPictureUrl) {
		this.origBankCardPictureUrl = origBankCardPictureUrl;
	}
	public String getOrigHealthCertificatePictureUrl1() {
		return origHealthCertificatePictureUrl1;
	}
	public void setOrigHealthCertificatePictureUrl1(
			String origHealthCertificatePictureUrl1) {
		this.origHealthCertificatePictureUrl1 = origHealthCertificatePictureUrl1;
	}
	public String getOrigHealthCertificatePictureUrl2() {
		return origHealthCertificatePictureUrl2;
	}
	public void setOrigHealthCertificatePictureUrl2(
			String origHealthCertificatePictureUrl2) {
		this.origHealthCertificatePictureUrl2 = origHealthCertificatePictureUrl2;
	}
	public String getEducationBackgroundCode() {
		return educationBackgroundCode;
	}
	public void setEducationBackgroundCode(String educationBackgroundCode) {
		this.educationBackgroundCode = educationBackgroundCode;
	}
	public String getRecruitmentChannelCode() {
		return recruitmentChannelCode;
	}
	public void setRecruitmentChannelCode(String recruitmentChannelCode) {
		this.recruitmentChannelCode = recruitmentChannelCode;
	}
	public String getHealthCertificateBeginDate() {
		return healthCertificateBeginDate;
	}
	public void setHealthCertificateBeginDate(String healthCertificateBeginDate) {
		this.healthCertificateBeginDate = healthCertificateBeginDate;
	}
	public String getHealthCertificateEndDate() {
		return healthCertificateEndDate;
	}
	public void setHealthCertificateEndDate(String healthCertificateEndDate) {
		this.healthCertificateEndDate = healthCertificateEndDate;
	}
	public String getEducationBackgroundName() {
		return educationBackgroundName;
	}
	public void setEducationBackgroundName(String educationBackgroundName) {
		this.educationBackgroundName = educationBackgroundName;
	}
	public String getRecruitmentChannelName() {
		return recruitmentChannelName;
	}
	public void setRecruitmentChannelName(String recruitmentChannelName) {
		this.recruitmentChannelName = recruitmentChannelName;
	}
	
}
