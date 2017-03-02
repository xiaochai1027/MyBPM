package com.i360r.bpm.service.rs.process.attendance.api;

import com.i360r.bpm.business.model.ProcessUniqueScope;
import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.ProcessUniqueVariableRequest;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class AttendanceRawDataRequest extends ProcessUniqueVariableRequest {


    @ProcessVariable
    @NotNull
    private String deliveryStaffCode;

    @ProcessVariable(isKeyword = true, keywordOrder = 2, isUnique = true, showName = "添加原始考勤流程", uniqueScope = ProcessUniqueScope.UNFINISHED)
    @NotNull
    private String deliveryStaffName;

    @ProcessVariable(isKeyword = true, keywordOrder = 1)
    @NotNull
    private String businessAreaName;

    @ProcessVariable
    @NotNull
    private String businessAreaCode;

    @ProcessVariable
    @NotNull
    private String attendanceDate;

    @ProcessVariable
    @NotNull
    private String attendanceTime;

    @ProcessVariable
    @NotNull
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
