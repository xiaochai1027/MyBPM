package com.i360r.bpm.service.rs.process.deliverystaffentry.api;

import com.i360r.bpm.business.model.ProcessUniqueScope;
import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.BusinessAreaBasedRequest;
import com.i360r.framework.service.aop.validate.field.annotation.MobileFormat;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

import java.math.BigDecimal;
import java.util.List;


public class DeliveryStaffHeadCountRequest extends BusinessAreaBasedRequest {
	
	@NotNull
	@ProcessVariable(isKeyword=true,keywordOrder=3)
	private String fullName;
	@NotNull
	@ProcessVariable(isKeyword=true,keywordOrder=1)
	private String cityName;
	@NotNull
	@ProcessVariable(isUnique=true, showName="身份证", uniqueScope = ProcessUniqueScope.UNFINISHED)
	private String identityCard;
	@ProcessVariable
	private String employeeCode;
	@ProcessVariable
	@NotNull
	private String identityCardPictureUrl1;
	@ProcessVariable
	@NotNull
	private String origIdentityCardPictureUrl1;
	@ProcessVariable
	@NotNull
	private String identityCardPictureUrl2;
	@ProcessVariable
	@NotNull
	private String origIdentityCardPictureUrl2;
	@ProcessVariable
	@NotNull
	private String avatarPictureUrl;
	@ProcessVariable
	@NotNull
	private String origAvatarPictureUrl;
	@ProcessVariable
	@NotNull
	private String genderCode;
	@ProcessVariable
	@NotNull
	private String positionCode;
	@MobileFormat(required = true)
	@ProcessVariable(isKeyword=true, isUnique=true, showName="手机号", uniqueScope = ProcessUniqueScope.UNFINISHED, keywordOrder=4)
	private String mobile;
	@ProcessVariable(isUnique=true, showName="钉钉手机号", uniqueScope = ProcessUniqueScope.UNFINISHED)
	private String dingdingMobile;
	@ProcessVariable
	@NotNull
	private Boolean companyVehiclesUsed;
	//判断是入职 还是转岗的字段
	@ProcessVariable
	@NotNull
	private Boolean jobTransfer;
	
	@ProcessVariable
	@NotNull
	private String entryDate;
	
	@ProcessVariable
	private String jobTransferDate;
	
	//如果是转岗， 传入 该配送员原始code
	@ProcessVariable(isUnique=true, showName="配送员转岗流程", uniqueScope = ProcessUniqueScope.UNFINISHED)
	private String originDeliveryStaffCode;
	
	//如果是转岗， 传入 该配送员 原始originPositionCode
	@ProcessVariable
	private String originPositionCode;

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
	
	private String verificationCode;
	@ProcessVariable
	private String positionName;
	@ProcessVariable
	private String originPositionName;
	
	@ProcessVariable
	private String educationBackgroundCode;
	@ProcessVariable
	private String recruitmentChannelCode;
	@ProcessVariable
	private String educationBackgroundName;
	@ProcessVariable
	private String recruitmentChannelName;
	@ProcessVariable
	private String physicalExaminationChargeSheetUrl;
	@ProcessVariable
	private String origPhysicalExaminationChargeSheetUrl;
	@ProcessVariable
	private List<InventoryVO> inventories;
	@ProcessVariable
	private String deliveryStaffGradeTypeCode;
	
	public List<InventoryVO> getInventories() {
		return inventories;
	}

	public void setInventories(List<InventoryVO> inventories) {
		this.inventories = inventories;
	}

	public String getPhysicalExaminationChargeSheetUrl() {
		return physicalExaminationChargeSheetUrl;
	}

	public void setPhysicalExaminationChargeSheetUrl(
			String physicalExaminationChargeSheetUrl) {
		this.physicalExaminationChargeSheetUrl = physicalExaminationChargeSheetUrl;
	}

	public Boolean getCompanyVehiclesUsed() {
		return companyVehiclesUsed;
	}

	public void setCompanyVehiclesUsed(Boolean companyVehiclesUsed) {
		this.companyVehiclesUsed = companyVehiclesUsed;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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

	public String getAvatarPictureUrl() {
		return avatarPictureUrl;
	}

	public void setAvatarPictureUrl(String avatarPictureUrl) {
		this.avatarPictureUrl = avatarPictureUrl;
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

	public Boolean getJobTransfer() {
		return jobTransfer;
	}

	public void setJobTransfer(Boolean jobTransfer) {
		this.jobTransfer = jobTransfer;
	}

	public String getOriginPositionCode() {
		return originPositionCode;
	}

	public void setOriginPositionCode(String originPositionCode) {
		this.originPositionCode = originPositionCode;
	}

	public String getOrigIdentityCardPictureUrl1() {
		return origIdentityCardPictureUrl1;
	}

	public void setOrigIdentityCardPictureUrl1(
			String origIdentityCardPictureUrl1) {
		this.origIdentityCardPictureUrl1 = origIdentityCardPictureUrl1;
	}

	public String getOrigIdentityCardPictureUrl2() {
		return origIdentityCardPictureUrl2;
	}

	public void setOrigIdentityCardPictureUrl2(
			String origIdentityCardPictureUrl2) {
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

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getOriginDeliveryStaffCode() {
		return originDeliveryStaffCode;
	}

	public void setOriginDeliveryStaffCode(String originDeliveryStaffCode) {
		this.originDeliveryStaffCode = originDeliveryStaffCode;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getOriginPositionName() {
		return originPositionName;
	}

	public void setOriginPositionName(String originPositionName) {
		this.originPositionName = originPositionName;
	}

	public String getDingdingMobile() {
		return dingdingMobile;
	}

	public void setDingdingMobile(String dingdingMobile) {
		this.dingdingMobile = dingdingMobile;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
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

	public String getOrigPhysicalExaminationChargeSheetUrl() {
		return origPhysicalExaminationChargeSheetUrl;
	}

	public void setOrigPhysicalExaminationChargeSheetUrl(
			String origPhysicalExaminationChargeSheetUrl) {
		this.origPhysicalExaminationChargeSheetUrl = origPhysicalExaminationChargeSheetUrl;
	}

	public String getDeliveryStaffGradeTypeCode() {
		return deliveryStaffGradeTypeCode;
	}

	public void setDeliveryStaffGradeTypeCode(String deliveryStaffGradeTypeCode) {
		this.deliveryStaffGradeTypeCode = deliveryStaffGradeTypeCode;
	}
	
}
