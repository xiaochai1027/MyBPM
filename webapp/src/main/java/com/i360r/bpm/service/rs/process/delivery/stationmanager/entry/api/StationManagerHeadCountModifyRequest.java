package com.i360r.bpm.service.rs.process.delivery.stationmanager.entry.api;

import com.i360r.bpm.business.model.ProcessUniqueScope;
import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.ReapplyTaskRequest;
import com.i360r.framework.service.aop.validate.field.annotation.MobileFormat;

import java.math.BigDecimal;

public class StationManagerHeadCountModifyRequest extends ReapplyTaskRequest {

	@ProcessVariable
	private String businessAreaCode;
	@ProcessVariable(isKeyword=true,keywordOrder=2)
	private String businessAreaName;
	@ProcessVariable
	private Boolean directManagement;
	@ProcessVariable(isKeyword=true,keywordOrder=3)
	private String fullName;
	@ProcessVariable(isUnique=true, showName="身份证", uniqueScope = ProcessUniqueScope.UNFINISHED)
	private String identityCard;
	@ProcessVariable
	private String identityCardPictureUrl1;
	@ProcessVariable
	private String origIdentityCardPictureUrl1;
	@ProcessVariable
	private String identityCardPictureUrl2;
	@ProcessVariable
	private String origIdentityCardPictureUrl2;
	@ProcessVariable
	private String avatarPictureUrl;
	@ProcessVariable
	private String origAvatarPictureUrl;
	@ProcessVariable
	private String genderCode;
	@ProcessVariable
	private String positionCode;
	@ProcessVariable
	private String positionName;
	@MobileFormat(required = false)
	@ProcessVariable(isKeyword=true, isUnique=true, showName="手机号", uniqueScope = ProcessUniqueScope.UNFINISHED,keywordOrder=4)
	private String mobile;

	@ProcessVariable
	private BigDecimal royaltiesPerOrder;
	@ProcessVariable
	private BigDecimal basicSalary;
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
	@ProcessVariable
	private String healthCertificatePictureUrl1;
	@ProcessVariable
	private String origHealthCertificatePictureUrl1;
	@ProcessVariable
	private String healthCertificatePictureUrl2;
	@ProcessVariable
	private String origHealthCertificatePictureUrl2;
	@ProcessVariable
	private String healthCertificateBeginDate;
	@ProcessVariable
	private String healthCertificateEndDate;
	@ProcessVariable
	private String educationBackgroundCode;
	@ProcessVariable
	private String recruitmentChannelCode;
	@ProcessVariable
	private String educationBackgroundName;
	@ProcessVariable
	private String recruitmentChannelName;
	@ProcessVariable
	private String entryDate;
	@ProcessVariable
	private String jobTransferDate;
	@ProcessVariable
	private Boolean companyVehiclesUsed;
	@ProcessVariable
	private String physicalExaminationChargeSheetUrl;
	@ProcessVariable
	private String origPhysicalExaminationChargeSheetUrl;
	
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
	public String getPhysicalExaminationChargeSheetUrl() {
		return physicalExaminationChargeSheetUrl;
	}
	public void setPhysicalExaminationChargeSheetUrl(
			String physicalExaminationChargeSheetUrl) {
		this.physicalExaminationChargeSheetUrl = physicalExaminationChargeSheetUrl;
	}
	public String getOrigPhysicalExaminationChargeSheetUrl() {
		return origPhysicalExaminationChargeSheetUrl;
	}
	public void setOrigPhysicalExaminationChargeSheetUrl(
			String origPhysicalExaminationChargeSheetUrl) {
		this.origPhysicalExaminationChargeSheetUrl = origPhysicalExaminationChargeSheetUrl;
	}
	public Boolean getCompanyVehiclesUsed() {
		return companyVehiclesUsed;
	}
	public void setCompanyVehiclesUsed(Boolean companyVehiclesUsed) {
		this.companyVehiclesUsed = companyVehiclesUsed;
	}
	public String getBusinessAreaCode() {
		return businessAreaCode;
	}
	public void setBusinessAreaCode(String businessAreaCode) {
		this.businessAreaCode = businessAreaCode;
	}
	public String getBusinessAreaName() {
		return businessAreaName;
	}
	public void setBusinessAreaName(String businessAreaName) {
		this.businessAreaName = businessAreaName;
	}
	public Boolean getDirectManagement() {
		return directManagement;
	}
	public void setDirectManagement(Boolean directManagement) {
		this.directManagement = directManagement;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getIdentityCardPictureUrl1() {
		return identityCardPictureUrl1;
	}
	public void setIdentityCardPictureUrl1(String identityCardPictureUrl1) {
		this.identityCardPictureUrl1 = identityCardPictureUrl1;
	}
	public String getIdentityCardPictureUrl2() {
		return identityCardPictureUrl2;
	}
	public void setIdentityCardPictureUrl2(String identityCardPictureUrl2) {
		this.identityCardPictureUrl2 = identityCardPictureUrl2;
	}
	public String getAvatarPictureUrl() {
		return avatarPictureUrl;
	}
	public void setAvatarPictureUrl(String avatarPictureUrl) {
		this.avatarPictureUrl = avatarPictureUrl;
	}
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public BigDecimal getRoyaltiesPerOrder() {
		return royaltiesPerOrder;
	}
	public void setRoyaltiesPerOrder(BigDecimal royaltiesPerOrder) {
		this.royaltiesPerOrder = royaltiesPerOrder;
	}
	public BigDecimal getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(BigDecimal basicSalary) {
		this.basicSalary = basicSalary;
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
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
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
	public String getContractTypeCode() {
		return contractTypeCode;
	}
	public void setContractTypeCode(String contractTypeCode) {
		this.contractTypeCode = contractTypeCode;
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
	public String getOrigIdentityCardPictureUrl1() {
		return origIdentityCardPictureUrl1;
	}
	public void setOrigIdentityCardPictureUrl1(String origIdentityCardPictureUrl1) {
		this.origIdentityCardPictureUrl1 = origIdentityCardPictureUrl1;
	}
	public String getOrigIdentityCardPictureUrl2() {
		return origIdentityCardPictureUrl2;
	}
	public void setOrigIdentityCardPictureUrl2(String origIdentityCardPictureUrl2) {
		this.origIdentityCardPictureUrl2 = origIdentityCardPictureUrl2;
	}
	public String getOrigAvatarPictureUrl() {
		return origAvatarPictureUrl;
	}
	public void setOrigAvatarPictureUrl(String origAvatarPictureUrl) {
		this.origAvatarPictureUrl = origAvatarPictureUrl;
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
