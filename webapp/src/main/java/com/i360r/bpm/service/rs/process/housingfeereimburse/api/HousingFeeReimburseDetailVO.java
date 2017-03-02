package com.i360r.bpm.service.rs.process.housingfeereimburse.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.i360r.bpm.business.model.ProcessVariable;

public class HousingFeeReimburseDetailVO {

	@ProcessVariable
	private String contractId;

	@ProcessVariable
	private String address;

	@ProcessVariable
	private String cityCode;

	@ProcessVariable
	private String cityName;

	@ProcessVariable
	private String businessAreaCode;

	@ProcessVariable
	private String businessAreaName;

	@ProcessVariable
	private String bankAccountName;
	
	@ProcessVariable
	private String bankAccountNumber;
	
	@ProcessVariable
	private String rejectItemsRemind;
	
	@ProcessVariable
	private List<HousingFeeReimburseItemVO> items;
	
	@ProcessVariable
	private String note;

	@ProcessVariable
	private String actualPaymentDate;
	
	@ProcessVariable
	private BigDecimal totalAmount;
	
	@ProcessVariable
	private String createdByName; // 申请人名字
	@ProcessVariable
	private String employeePosition; // 申请人岗位
	@ProcessVariable
	private String employeeMobile; // 申请人手机号

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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

	public List<HousingFeeReimburseItemVO> getItems() {
		return items;
	}

	public void setItems(List<HousingFeeReimburseItemVO> items) {
		this.items = items;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public String getEmployeePosition() {
		return employeePosition;
	}

	public void setEmployeePosition(String employeePosition) {
		this.employeePosition = employeePosition;
	}

	public String getEmployeeMobile() {
		return employeeMobile;
	}

	public void setEmployeeMobile(String employeeMobile) {
		this.employeeMobile = employeeMobile;
	}

	public String getActualPaymentDate() {
		return actualPaymentDate;
	}

	public void setActualPaymentDate(String actualPaymentDate) {
		this.actualPaymentDate = actualPaymentDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getRejectItemsRemind() {
		return rejectItemsRemind;
	}

	public void setRejectItemsRemind(String rejectItemsRemind) {
		this.rejectItemsRemind = rejectItemsRemind;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<HousingFeeReimburseItemVO> getApprovedItems() {
		List<HousingFeeReimburseItemVO> list = new ArrayList<HousingFeeReimburseItemVO>();
		for (HousingFeeReimburseItemVO vo : this.getItems()) {
			if (!vo.isRejected()) {
				list.add(vo);
			}
		}
		return list;
	}

	public List<HousingFeeReimburseItemVO> getRejectedItems() {
		List<HousingFeeReimburseItemVO> list = new ArrayList<HousingFeeReimburseItemVO>();
		for (HousingFeeReimburseItemVO vo : this.getItems()) {
			if (vo.isRejected()) {
				list.add(vo);
			}
		}
		return list;
	}
	
}
