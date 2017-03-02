package com.i360r.bpm.service.rs.process.attendance.api;

import com.i360r.bpm.business.model.ProcessVariable;

public class AttendanceRawDataDetailVO {

    @ProcessVariable
    private String deliveryStaffName;

    @ProcessVariable
    private String deliveryStaffCode;

    @ProcessVariable
    private String businessAreaName;

    @ProcessVariable
    private String businessAreaCode;

    @ProcessVariable
    private String attendanceDate;

    @ProcessVariable
    private String attendanceTime;

    @ProcessVariable
    private String reason;

    @ProcessVariable
    private String dutyTypeCode;

    public String getDeliveryStaffName() {
        return deliveryStaffName;
    }

    public void setDeliveryStaffName(String deliveryStaffName) {
        this.deliveryStaffName = deliveryStaffName;
    }

    public String getDeliveryStaffCode() {
        return deliveryStaffCode;
    }

    public void setDeliveryStaffCode(String deliveryStaffCode) {
        this.deliveryStaffCode = deliveryStaffCode;
    }

    public String getBusinessAreaName() {
        return businessAreaName;
    }

    public void setBusinessAreaName(String businessAreaName) {
        this.businessAreaName = businessAreaName;
    }

    public String getBusinessAreaCode() {
        return businessAreaCode;
    }

    public void setBusinessAreaCode(String businessAreaCode) {
        this.businessAreaCode = businessAreaCode;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(String attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDutyTypeCode() {
        return dutyTypeCode;
    }

    public void setDutyTypeCode(String dutyTypeCode) {
        this.dutyTypeCode = dutyTypeCode;
    }
}
