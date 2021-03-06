package com.i360r.bpm.service.rs.process.store.account.api;

import com.i360r.bpm.business.model.ProcessUniqueScope;
import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

import java.math.BigDecimal;

/**
 * Created by MengWeiBo on 2016-12-22
 */
public class CreateStoreAccountRequest {

    @ProcessVariable(isKeyword = true, keywordOrder = 2)
    private String accountMobile;
    @ProcessVariable(isKeyword = true, keywordOrder = 1, isUnique = true, showName = "新增商家账号流程", uniqueScope = ProcessUniqueScope.UNFINISHED)
    @NotNull
    private String accountName;
    @ProcessVariable
    @NotNull
    private Boolean accountStatus;
    @ProcessVariable
    private BigDecimal alertAmount;
    @ProcessVariable
    @NotNull
    private Boolean vendorStatus;
    @ProcessVariable
    @NotNull
    private String vendorName;
    @ProcessVariable
    @NotNull
    private String contactName;
    @ProcessVariable
    @NotNull
    private String contactMobile;
    @ProcessVariable
    @NotNull
    private String vendorDescription;
    @ProcessVariable
    @NotNull
    private String deliverTypeCode;
    @ProcessVariable
    @NotNull
    private String businessTypeCode;
    @ProcessVariable
    @NotNull
    private String deliverFeeRoundingTypeCode;
    @ProcessVariable
    @NotNull
    private Boolean hasPartner;
    @ProcessVariable
    private BigDecimal distanceLimit;
    @ProcessVariable
    private Integer plannedCompleteTimeInterval;
    @ProcessVariable
    private String callBackUrl;
    @ProcessVariable
    private Integer locationProcessRatio;
    @ProcessVariable
    private Boolean syncLocation;
    @ProcessVariable
    private String version;
    @ProcessVariable
    private Integer performanceTimeImmediatelyMins;
    @ProcessVariable
    private Integer performanceTimeIntimeMinMins;
    @ProcessVariable
    private Integer performanceTimeIntimeMaxMins;
    @ProcessVariable
    private Integer performanceDistanceCompleteMeter;
    @ProcessVariable
    private BigDecimal allowanceDistanceBase;
    @ProcessVariable
    private BigDecimal allowanceDistanceUnit;
    @ProcessVariable
    private BigDecimal allowanceDistanceAmmountPerUnit;
    @ProcessVariable
    private BigDecimal allowanceDistanceMaxAmmount;
    @ProcessVariable
    private BigDecimal allowanceWeightBase;
    @ProcessVariable
    private BigDecimal allowanceWeightUnit;
    @ProcessVariable
    private BigDecimal allowanceWeightAmmountPerUnit;
    @ProcessVariable
    private BigDecimal allowanceWeightMaxAmmount;
    @ProcessVariable
    private Boolean partTimeManual;
    @ProcessVariable
    private String settlementTypeCode;
    @ProcessVariable
    private BigDecimal deliverFeeBase;
    @ProcessVariable
    private BigDecimal deliverFeeDistanceBase;
    @ProcessVariable
    private BigDecimal deliverFeeDistanceUnit;
    @ProcessVariable
    private BigDecimal deliverFeeDistanceAmmountPerUnit;
    @ProcessVariable
    private BigDecimal deliverFeeDistanceMaxAmmount;
    @ProcessVariable
    private BigDecimal deliverFeeWeightBase;
    @ProcessVariable
    private BigDecimal deliverFeeWeightUnit;
    @ProcessVariable
    private BigDecimal deliverFeeWeightAmmountPerUnit;
    @ProcessVariable
    private BigDecimal deliverFeeWeightMaxAmmount;
    @ProcessVariable
    private BigDecimal deliverFeeTimeslot1Ammount;
    @ProcessVariable
    private String deliverFeeTimeslot1From;
    @ProcessVariable
    private String deliverFeeTimeslot1To;
    @ProcessVariable
    private BigDecimal deliverFeeTimeslot2Ammount;
    @ProcessVariable
    private String deliverFeeTimeslot2From;
    @ProcessVariable
    private String deliverFeeTimeslot2To;
    @ProcessVariable
    private BigDecimal deliverFeeTimeslot3Ammount;
    @ProcessVariable
    private String deliverFeeTimeslot3From;
    @ProcessVariable
    private String deliverFeeTimeslot3To;
    @ProcessVariable
    private BigDecimal deliverFeeOrderPriceBase;
    @ProcessVariable
    private BigDecimal deliverFeeOrderPriceDiscountRate;
    @ProcessVariable
    private BigDecimal deliverFeeOrderPriceMaxAmmount;
    @ProcessVariable
    @NotNull
    private Boolean useFixedRoyaltiesConfig;
    @ProcessVariable
    private BigDecimal parttimeFixedRoyaltiesAmount;
    @ProcessVariable
    private BigDecimal fulltimeFixedRoyaltiesAmount;
    @ProcessVariable
    private Boolean separatePaidSalary;
    @ProcessVariable
    private String remarks;

    public String getAccountMobile() {
        return accountMobile;
    }

    public void setAccountMobile(String accountMobile) {
        this.accountMobile = accountMobile;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Boolean getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    public BigDecimal getAlertAmount() {
        return alertAmount;
    }

    public void setAlertAmount(BigDecimal alertAmount) {
        this.alertAmount = alertAmount;
    }

    public Boolean getVendorStatus() {
        return vendorStatus;
    }

    public void setVendorStatus(Boolean vendorStatus) {
        this.vendorStatus = vendorStatus;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getVendorDescription() {
        return vendorDescription;
    }

    public void setVendorDescription(String vendorDescription) {
        this.vendorDescription = vendorDescription;
    }

    public String getDeliverTypeCode() {
        return deliverTypeCode;
    }

    public void setDeliverTypeCode(String deliverTypeCode) {
        this.deliverTypeCode = deliverTypeCode;
    }

    public String getBusinessTypeCode() {
        return businessTypeCode;
    }

    public void setBusinessTypeCode(String businessTypeCode) {
        this.businessTypeCode = businessTypeCode;
    }

    public String getDeliverFeeRoundingTypeCode() {
        return deliverFeeRoundingTypeCode;
    }

    public void setDeliverFeeRoundingTypeCode(String deliverFeeRoundingTypeCode) {
        this.deliverFeeRoundingTypeCode = deliverFeeRoundingTypeCode;
    }

    public Boolean getHasPartner() {
        return hasPartner;
    }

    public void setHasPartner(Boolean hasPartner) {
        this.hasPartner = hasPartner;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }

    public Integer getLocationProcessRatio() {
        return locationProcessRatio;
    }

    public void setLocationProcessRatio(Integer locationProcessRatio) {
        this.locationProcessRatio = locationProcessRatio;
    }

    public Boolean getSyncLocation() {
        return syncLocation;
    }

    public void setSyncLocation(Boolean syncLocation) {
        this.syncLocation = syncLocation;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getPerformanceTimeImmediatelyMins() {
        return performanceTimeImmediatelyMins;
    }

    public void setPerformanceTimeImmediatelyMins(Integer performanceTimeImmediatelyMins) {
        this.performanceTimeImmediatelyMins = performanceTimeImmediatelyMins;
    }

    public Integer getPerformanceTimeIntimeMinMins() {
        return performanceTimeIntimeMinMins;
    }

    public void setPerformanceTimeIntimeMinMins(Integer performanceTimeIntimeMinMins) {
        this.performanceTimeIntimeMinMins = performanceTimeIntimeMinMins;
    }

    public Integer getPerformanceTimeIntimeMaxMins() {
        return performanceTimeIntimeMaxMins;
    }

    public void setPerformanceTimeIntimeMaxMins(Integer performanceTimeIntimeMaxMins) {
        this.performanceTimeIntimeMaxMins = performanceTimeIntimeMaxMins;
    }

    public Integer getPerformanceDistanceCompleteMeter() {
        return performanceDistanceCompleteMeter;
    }

    public void setPerformanceDistanceCompleteMeter(Integer performanceDistanceCompleteMeter) {
        this.performanceDistanceCompleteMeter = performanceDistanceCompleteMeter;
    }

    public BigDecimal getAllowanceDistanceBase() {
        return allowanceDistanceBase;
    }

    public void setAllowanceDistanceBase(BigDecimal allowanceDistanceBase) {
        this.allowanceDistanceBase = allowanceDistanceBase;
    }

    public BigDecimal getAllowanceDistanceUnit() {
        return allowanceDistanceUnit;
    }

    public void setAllowanceDistanceUnit(BigDecimal allowanceDistanceUnit) {
        this.allowanceDistanceUnit = allowanceDistanceUnit;
    }

    public BigDecimal getAllowanceDistanceAmmountPerUnit() {
        return allowanceDistanceAmmountPerUnit;
    }

    public void setAllowanceDistanceAmmountPerUnit(BigDecimal allowanceDistanceAmmountPerUnit) {
        this.allowanceDistanceAmmountPerUnit = allowanceDistanceAmmountPerUnit;
    }

    public BigDecimal getAllowanceDistanceMaxAmmount() {
        return allowanceDistanceMaxAmmount;
    }

    public void setAllowanceDistanceMaxAmmount(BigDecimal allowanceDistanceMaxAmmount) {
        this.allowanceDistanceMaxAmmount = allowanceDistanceMaxAmmount;
    }

    public BigDecimal getAllowanceWeightBase() {
        return allowanceWeightBase;
    }

    public void setAllowanceWeightBase(BigDecimal allowanceWeightBase) {
        this.allowanceWeightBase = allowanceWeightBase;
    }

    public BigDecimal getAllowanceWeightUnit() {
        return allowanceWeightUnit;
    }

    public void setAllowanceWeightUnit(BigDecimal allowanceWeightUnit) {
        this.allowanceWeightUnit = allowanceWeightUnit;
    }

    public BigDecimal getAllowanceWeightAmmountPerUnit() {
        return allowanceWeightAmmountPerUnit;
    }

    public void setAllowanceWeightAmmountPerUnit(BigDecimal allowanceWeightAmmountPerUnit) {
        this.allowanceWeightAmmountPerUnit = allowanceWeightAmmountPerUnit;
    }

    public BigDecimal getAllowanceWeightMaxAmmount() {
        return allowanceWeightMaxAmmount;
    }

    public void setAllowanceWeightMaxAmmount(BigDecimal allowanceWeightMaxAmmount) {
        this.allowanceWeightMaxAmmount = allowanceWeightMaxAmmount;
    }

    public Boolean getPartTimeManual() {
        return partTimeManual;
    }

    public void setPartTimeManual(Boolean partTimeManual) {
        this.partTimeManual = partTimeManual;
    }

    public String getSettlementTypeCode() {
        return settlementTypeCode;
    }

    public void setSettlementTypeCode(String settlementTypeCode) {
        this.settlementTypeCode = settlementTypeCode;
    }

    public BigDecimal getDeliverFeeBase() {
        return deliverFeeBase;
    }

    public void setDeliverFeeBase(BigDecimal deliverFeeBase) {
        this.deliverFeeBase = deliverFeeBase;
    }

    public BigDecimal getDeliverFeeDistanceBase() {
        return deliverFeeDistanceBase;
    }

    public void setDeliverFeeDistanceBase(BigDecimal deliverFeeDistanceBase) {
        this.deliverFeeDistanceBase = deliverFeeDistanceBase;
    }

    public BigDecimal getDeliverFeeDistanceUnit() {
        return deliverFeeDistanceUnit;
    }

    public void setDeliverFeeDistanceUnit(BigDecimal deliverFeeDistanceUnit) {
        this.deliverFeeDistanceUnit = deliverFeeDistanceUnit;
    }

    public BigDecimal getDeliverFeeDistanceAmmountPerUnit() {
        return deliverFeeDistanceAmmountPerUnit;
    }

    public void setDeliverFeeDistanceAmmountPerUnit(BigDecimal deliverFeeDistanceAmmountPerUnit) {
        this.deliverFeeDistanceAmmountPerUnit = deliverFeeDistanceAmmountPerUnit;
    }

    public BigDecimal getDeliverFeeDistanceMaxAmmount() {
        return deliverFeeDistanceMaxAmmount;
    }

    public void setDeliverFeeDistanceMaxAmmount(BigDecimal deliverFeeDistanceMaxAmmount) {
        this.deliverFeeDistanceMaxAmmount = deliverFeeDistanceMaxAmmount;
    }

    public BigDecimal getDeliverFeeWeightBase() {
        return deliverFeeWeightBase;
    }

    public void setDeliverFeeWeightBase(BigDecimal deliverFeeWeightBase) {
        this.deliverFeeWeightBase = deliverFeeWeightBase;
    }

    public BigDecimal getDeliverFeeWeightUnit() {
        return deliverFeeWeightUnit;
    }

    public void setDeliverFeeWeightUnit(BigDecimal deliverFeeWeightUnit) {
        this.deliverFeeWeightUnit = deliverFeeWeightUnit;
    }

    public BigDecimal getDeliverFeeWeightAmmountPerUnit() {
        return deliverFeeWeightAmmountPerUnit;
    }

    public void setDeliverFeeWeightAmmountPerUnit(BigDecimal deliverFeeWeightAmmountPerUnit) {
        this.deliverFeeWeightAmmountPerUnit = deliverFeeWeightAmmountPerUnit;
    }

    public BigDecimal getDeliverFeeWeightMaxAmmount() {
        return deliverFeeWeightMaxAmmount;
    }

    public void setDeliverFeeWeightMaxAmmount(BigDecimal deliverFeeWeightMaxAmmount) {
        this.deliverFeeWeightMaxAmmount = deliverFeeWeightMaxAmmount;
    }

    public BigDecimal getDeliverFeeTimeslot1Ammount() {
        return deliverFeeTimeslot1Ammount;
    }

    public void setDeliverFeeTimeslot1Ammount(BigDecimal deliverFeeTimeslot1Ammount) {
        this.deliverFeeTimeslot1Ammount = deliverFeeTimeslot1Ammount;
    }

    public String getDeliverFeeTimeslot1From() {
        return deliverFeeTimeslot1From;
    }

    public void setDeliverFeeTimeslot1From(String deliverFeeTimeslot1From) {
        this.deliverFeeTimeslot1From = deliverFeeTimeslot1From;
    }

    public String getDeliverFeeTimeslot1To() {
        return deliverFeeTimeslot1To;
    }

    public void setDeliverFeeTimeslot1To(String deliverFeeTimeslot1To) {
        this.deliverFeeTimeslot1To = deliverFeeTimeslot1To;
    }

    public BigDecimal getDeliverFeeTimeslot2Ammount() {
        return deliverFeeTimeslot2Ammount;
    }

    public void setDeliverFeeTimeslot2Ammount(BigDecimal deliverFeeTimeslot2Ammount) {
        this.deliverFeeTimeslot2Ammount = deliverFeeTimeslot2Ammount;
    }

    public String getDeliverFeeTimeslot2From() {
        return deliverFeeTimeslot2From;
    }

    public void setDeliverFeeTimeslot2From(String deliverFeeTimeslot2From) {
        this.deliverFeeTimeslot2From = deliverFeeTimeslot2From;
    }

    public String getDeliverFeeTimeslot2To() {
        return deliverFeeTimeslot2To;
    }

    public void setDeliverFeeTimeslot2To(String deliverFeeTimeslot2To) {
        this.deliverFeeTimeslot2To = deliverFeeTimeslot2To;
    }

    public BigDecimal getDeliverFeeTimeslot3Ammount() {
        return deliverFeeTimeslot3Ammount;
    }

    public void setDeliverFeeTimeslot3Ammount(BigDecimal deliverFeeTimeslot3Ammount) {
        this.deliverFeeTimeslot3Ammount = deliverFeeTimeslot3Ammount;
    }

    public String getDeliverFeeTimeslot3From() {
        return deliverFeeTimeslot3From;
    }

    public void setDeliverFeeTimeslot3From(String deliverFeeTimeslot3From) {
        this.deliverFeeTimeslot3From = deliverFeeTimeslot3From;
    }

    public String getDeliverFeeTimeslot3To() {
        return deliverFeeTimeslot3To;
    }

    public void setDeliverFeeTimeslot3To(String deliverFeeTimeslot3To) {
        this.deliverFeeTimeslot3To = deliverFeeTimeslot3To;
    }

    public BigDecimal getDeliverFeeOrderPriceBase() {
        return deliverFeeOrderPriceBase;
    }

    public void setDeliverFeeOrderPriceBase(BigDecimal deliverFeeOrderPriceBase) {
        this.deliverFeeOrderPriceBase = deliverFeeOrderPriceBase;
    }

    public BigDecimal getDeliverFeeOrderPriceDiscountRate() {
        return deliverFeeOrderPriceDiscountRate;
    }

    public void setDeliverFeeOrderPriceDiscountRate(BigDecimal deliverFeeOrderPriceDiscountRate) {
        this.deliverFeeOrderPriceDiscountRate = deliverFeeOrderPriceDiscountRate;
    }

    public BigDecimal getDeliverFeeOrderPriceMaxAmmount() {
        return deliverFeeOrderPriceMaxAmmount;
    }

    public void setDeliverFeeOrderPriceMaxAmmount(BigDecimal deliverFeeOrderPriceMaxAmmount) {
        this.deliverFeeOrderPriceMaxAmmount = deliverFeeOrderPriceMaxAmmount;
    }

    public Boolean getUseFixedRoyaltiesConfig() {
        return useFixedRoyaltiesConfig;
    }

    public void setUseFixedRoyaltiesConfig(Boolean useFixedRoyaltiesConfig) {
        this.useFixedRoyaltiesConfig = useFixedRoyaltiesConfig;
    }

    public BigDecimal getParttimeFixedRoyaltiesAmount() {
        return parttimeFixedRoyaltiesAmount;
    }

    public void setParttimeFixedRoyaltiesAmount(BigDecimal parttimeFixedRoyaltiesAmount) {
        this.parttimeFixedRoyaltiesAmount = parttimeFixedRoyaltiesAmount;
    }

    public BigDecimal getFulltimeFixedRoyaltiesAmount() {
        return fulltimeFixedRoyaltiesAmount;
    }

    public void setFulltimeFixedRoyaltiesAmount(BigDecimal fulltimeFixedRoyaltiesAmount) {
        this.fulltimeFixedRoyaltiesAmount = fulltimeFixedRoyaltiesAmount;
    }

    public Boolean getSeparatePaidSalary() {
        return separatePaidSalary;
    }

    public void setSeparatePaidSalary(Boolean separatePaidSalary) {
        this.separatePaidSalary = separatePaidSalary;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	public BigDecimal getDistanceLimit() {
		return distanceLimit;
	}

	public void setDistanceLimit(BigDecimal distanceLimit) {
		this.distanceLimit = distanceLimit;
	}

	public Integer getPlannedCompleteTimeInterval() {
		return plannedCompleteTimeInterval;
	}

	public void setPlannedCompleteTimeInterval(Integer plannedCompleteTimeInterval) {
		this.plannedCompleteTimeInterval = plannedCompleteTimeInterval;
	}

}
