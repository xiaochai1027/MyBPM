package com.i360r.bpm.service.rs.process.fixedasset.businessareatransferout.api;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.BusinessAreaBasedVO;

public class FixedAssetBusinessAreaTransferOutDetailVO extends BusinessAreaBasedVO {
	
	@ProcessVariable
	private String  businessAreaInventoryId;
	
	@ProcessVariable
	private String  number;
	
	@ProcessVariable
	private String fixedAssetTypeCode;
	
	@ProcessVariable
	private String fixedAssetTypeName;
	
	@ProcessVariable
	private String  brand;
	
	@ProcessVariable
	private String  responsiblePersonCode;
	
	@ProcessVariable
	private String  responsiblePersonName;
	
	@ProcessVariable
	private String  count;
	
	@ProcessVariable
	private String  transferOutCount;
	
	@ProcessVariable
	private String  toLocationCode;
	
	@ProcessVariable
	private String  toLocationName;
	
	@ProcessVariable
	private String  fromLocationCode;
	
	@ProcessVariable
	private String  fromLocationName;
	
	@ProcessVariable
	private String  remark;
	
	@ProcessVariable
	private String inventoryType;
	
	
	public String getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}
	
	public String getBusinessAreaInventoryId() {
		return businessAreaInventoryId;
	}
	
	public void setBusinessAreaInventoryId(String businessAreaInventoryId) {
		this.businessAreaInventoryId = businessAreaInventoryId;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getFixedAssetTypeCode() {
		return fixedAssetTypeCode;
	}

	public void setFixedAssetTypeCode(String fixedAssetTypeCode) {
		this.fixedAssetTypeCode = fixedAssetTypeCode;
	}

	public String getFixedAssetTypeName() {
		return fixedAssetTypeName;
	}

	public void setFixedAssetTypeName(String fixedAssetTypeName) {
		this.fixedAssetTypeName = fixedAssetTypeName;
	}

	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getResponsiblePersonCode() {
		return responsiblePersonCode;
	}
	
	public void setResponsiblePersonCode(String responsiblePersonCode) {
		this.responsiblePersonCode = responsiblePersonCode;
	}
	
	public String getResponsiblePersonName() {
		return responsiblePersonName;
	}
	
	public void setResponsiblePersonName(String responsiblePersonName) {
		this.responsiblePersonName = responsiblePersonName;
	}
	
	public String getCount() {
		return count;
	}
	
	public void setCount(String count) {
		this.count = count;
	}
	
	public String getTransferOutCount() {
		return transferOutCount;
	}
	
	public void setTransferOutCount(String transferOutCount) {
		this.transferOutCount = transferOutCount;
	}
	
	public String getToLocationCode() {
		return toLocationCode;
	}
	
	public void setToLocationCode(String toLocationCode) {
		this.toLocationCode = toLocationCode;
	}
	
	public String getToLocationName() {
		return toLocationName;
	}
	
	public void setToLocationName(String toLocationName) {
		this.toLocationName = toLocationName;
	}
	
	public String getFromLocationCode() {
		return fromLocationCode;
	}
	
	public void setFromLocationCode(String fromLocationCode) {
		this.fromLocationCode = fromLocationCode;
	}
	
	public String getFromLocationName() {
		return fromLocationName;
	}
	
	public void setFromLocationName(String fromLocationName) {
		this.fromLocationName = fromLocationName;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
