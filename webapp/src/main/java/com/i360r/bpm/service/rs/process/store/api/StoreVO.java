package com.i360r.bpm.service.rs.process.store.api;

import com.i360r.bpm.business.model.ProcessVariable;

import java.math.BigDecimal;

/**
 * Created by MengWeiBo on 2016-12-23
 */
public class StoreVO {
    @ProcessVariable
    private String vendorId;
    @ProcessVariable
    private String name;
    @ProcessVariable
    private String address;
    @ProcessVariable
    private BigDecimal longitude;
    @ProcessVariable
    private BigDecimal latitude;
    @ProcessVariable
    private String businessAreaId;
    @ProcessVariable
    private String businessAreaName;
    @ProcessVariable
    private Boolean online;
    @ProcessVariable
    private Boolean resident;
    @ProcessVariable
    private String phone1;
    @ProcessVariable
    private String phone2;
    @ProcessVariable
    private String phone3;
    @ProcessVariable
    private String remarks;

    private String accountId;
    private String accountMobile;
    private String accountName;
    private String invoiceTitle;
    private Boolean accountActivated;
    private BigDecimal alertAmount;
    private Boolean vendorActivated;
    private Boolean parttimeManual;
    private String vendorName;
    private String contactName;
    private String contactMobile;
    private String vendorDescription;
    private String deliverTypeCode;
    private String deliverTypeName;
    private String businessTypeCode;
    private String businessTypeName;
    private String deliverFeeRoundingTypeCode;
    private String deliverFeeRoundingTypeName;
    private Boolean hasPartner;
    private Boolean useFixedRoyaltiesConfig;
    private BigDecimal parttimeFixedRoyaltiesAmount;
    private BigDecimal fulltimeFixedRoyaltiesAmount;
    private Boolean separatePaidSalary;
    private String callBackUrl;
    private Integer locationProcessRatio;
    private Boolean syncLocation;
    private String version;
    private Integer performanceTimeImmediatelyMins;
    private Integer performanceTimeIntimeMinMins;
    private Integer performanceTimeIntimeMaxMins;
    private Integer performanceDistanceCompleteMeter;
    private BigDecimal allowanceDistanceBase;
    private BigDecimal allowanceDistanceUnit;
    private BigDecimal allowanceDistanceAmmountPerUnit;
    private BigDecimal allowanceDistanceMaxAmmount;
    private BigDecimal allowanceWeightBase;
    private BigDecimal allowanceWeightUnit;
    private BigDecimal allowanceWeightAmmountPerUnit;
    private BigDecimal allowanceWeightMaxAmmount;
    private String settlementTypeCode;
    private String settlementTypeName;
    private BigDecimal deliverFeeBase;
    private BigDecimal deliverFeeDistanceBase;
    private BigDecimal deliverFeeDistanceUnit;
    private BigDecimal deliverFeeDistanceAmmountPerUnit;
    private BigDecimal deliverFeeDistanceMaxAmmount;
    private BigDecimal deliverFeeWeightBase;
    private BigDecimal deliverFeeWeightUnit;
    private BigDecimal deliverFeeWeightAmmountPerUnit;
    private BigDecimal deliverFeeWeightMaxAmmount;
    private BigDecimal deliverFeeTimeslot1Ammount;
    private String deliverFeeTimeslot1From;
    private String deliverFeeTimeslot1To;
    private BigDecimal deliverFeeTimeslot2Ammount;
    private String deliverFeeTimeslot2From;
    private String deliverFeeTimeslot2To;
    private BigDecimal deliverFeeTimeslot3Ammount;
    private String deliverFeeTimeslot3From;
    private String deliverFeeTimeslot3To;
    private BigDecimal deliverFeeOrderPriceBase;
    private BigDecimal deliverFeeOrderPriceDiscountRate;
    private BigDecimal deliverFeeOrderPriceMaxAmmount;
    private boolean hasDeliverFeeDistanceConfig;
    private boolean hasDeliverFeeWeightConfig;
    private boolean hasDeliverFeeTimeslotConfig;
    private boolean hasDeliverFeeOrderPriceConfig;
    private boolean hasAllowanceWeightConfig;
    private boolean hasAllowanceDistanceConfig;

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getBusinessAreaId() {
        return businessAreaId;
    }

    public void setBusinessAreaId(String businessAreaId) {
        this.businessAreaId = businessAreaId;
    }

    public String getBusinessAreaName() {
        return businessAreaName;
    }

    public void setBusinessAreaName(String businessAreaName) {
        this.businessAreaName = businessAreaName;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Boolean getResident() {
        return resident;
    }

    public void setResident(Boolean resident) {
        this.resident = resident;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

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

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public Boolean getAccountActivated() {
        return accountActivated;
    }

    public void setAccountActivated(Boolean accountActivated) {
        this.accountActivated = accountActivated;
    }

    public BigDecimal getAlertAmount() {
        return alertAmount;
    }

    public void setAlertAmount(BigDecimal alertAmount) {
        this.alertAmount = alertAmount;
    }

    public Boolean getVendorActivated() {
        return vendorActivated;
    }

    public void setVendorActivated(Boolean vendorActivated) {
        this.vendorActivated = vendorActivated;
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

    public String getDeliverTypeName() {
        return deliverTypeName;
    }

    public void setDeliverTypeName(String deliverTypeName) {
        this.deliverTypeName = deliverTypeName;
    }

    public String getBusinessTypeCode() {
        return businessTypeCode;
    }

    public void setBusinessTypeCode(String businessTypeCode) {
        this.businessTypeCode = businessTypeCode;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    public String getDeliverFeeRoundingTypeCode() {
        return deliverFeeRoundingTypeCode;
    }

    public void setDeliverFeeRoundingTypeCode(String deliverFeeRoundingTypeCode) {
        this.deliverFeeRoundingTypeCode = deliverFeeRoundingTypeCode;
    }

    public String getDeliverFeeRoundingTypeName() {
        return deliverFeeRoundingTypeName;
    }

    public void setDeliverFeeRoundingTypeName(String deliverFeeRoundingTypeName) {
        this.deliverFeeRoundingTypeName = deliverFeeRoundingTypeName;
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

    public String getSettlementTypeCode() {
        return settlementTypeCode;
    }

    public void setSettlementTypeCode(String settlementTypeCode) {
        this.settlementTypeCode = settlementTypeCode;
    }

    public String getSettlementTypeName() {
        return settlementTypeName;
    }

    public void setSettlementTypeName(String settlementTypeName) {
        this.settlementTypeName = settlementTypeName;
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

    public Boolean getParttimeManual() {
        return parttimeManual;
    }

    public void setParttimeManual(Boolean parttimeManual) {
        this.parttimeManual = parttimeManual;
    }

    public boolean isHasDeliverFeeDistanceConfig() {
        return hasDeliverFeeDistanceConfig;
    }

    public void setHasDeliverFeeDistanceConfig(boolean hasDeliverFeeDistanceConfig) {
        this.hasDeliverFeeDistanceConfig = hasDeliverFeeDistanceConfig;
    }

    public boolean isHasDeliverFeeWeightConfig() {
        return hasDeliverFeeWeightConfig;
    }

    public void setHasDeliverFeeWeightConfig(boolean hasDeliverFeeWeightConfig) {
        this.hasDeliverFeeWeightConfig = hasDeliverFeeWeightConfig;
    }

    public boolean isHasDeliverFeeTimeslotConfig() {
        return hasDeliverFeeTimeslotConfig;
    }

    public void setHasDeliverFeeTimeslotConfig(boolean hasDeliverFeeTimeslotConfig) {
        this.hasDeliverFeeTimeslotConfig = hasDeliverFeeTimeslotConfig;
    }

    public boolean isHasDeliverFeeOrderPriceConfig() {
        return hasDeliverFeeOrderPriceConfig;
    }

    public void setHasDeliverFeeOrderPriceConfig(boolean hasDeliverFeeOrderPriceConfig) {
        this.hasDeliverFeeOrderPriceConfig = hasDeliverFeeOrderPriceConfig;
    }

    public boolean isHasAllowanceWeightConfig() {
        return hasAllowanceWeightConfig;
    }

    public void setHasAllowanceWeightConfig(boolean hasAllowanceWeightConfig) {
        this.hasAllowanceWeightConfig = hasAllowanceWeightConfig;
    }

    public boolean isHasAllowanceDistanceConfig() {
        return hasAllowanceDistanceConfig;
    }

    public void setHasAllowanceDistanceConfig(boolean hasAllowanceDistanceConfig) {
        this.hasAllowanceDistanceConfig = hasAllowanceDistanceConfig;
    }
}
