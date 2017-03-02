package com.i360r.bpm.service.rs.process.store.api;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

import java.math.BigDecimal;

/**
 * Created by MengWeiBo on 2016-12-23
 */
public class CreateStoreRequest {
    @ProcessVariable
    @NotNull
    private String vendorId;
    @ProcessVariable(isKeyword = true, keywordOrder = 3)
    @NotNull
    private String vendorName;
    @ProcessVariable(isKeyword = true, keywordOrder = 1)
    @NotNull
    private String name;
    @ProcessVariable
    @NotNull
    private String address;
    @ProcessVariable
    @NotNull
    private BigDecimal longitude;
    @ProcessVariable
    @NotNull
    private BigDecimal latitude;
    @ProcessVariable
    @NotNull
    private String businessAreaId;
    @ProcessVariable(isKeyword = true, keywordOrder = 2)
    private String businessAreaName;
    @ProcessVariable
    @NotNull
    private Boolean online;
    @ProcessVariable
    @NotNull
    private Boolean resident;
    @ProcessVariable
    private String phone1;
    @ProcessVariable
    private String phone2;
    @ProcessVariable
    private String phone3;
    @ProcessVariable
    private String remarks;

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
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
}
