package com.i360r.bpm.service.rs.process.fixedasset.businessareatransferout.api;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class FixedAssetBusinessAreaTransferOutRequest {
	
	@ProcessVariable
	private String  businessAreaInventoryId;
	
	@ProcessVariable
	private String number;
	
	@ProcessVariable
	private String fixedAssetTypeCode;
	
	@ProcessVariable(isKeyword=true, keywordOrder=3)
	private String fixedAssetTypeName;
	
	@ProcessVariable
	private String brand;
	
	@ProcessVariable
	private String responsiblePersonName;
	
	@ProcessVariable
	private String responsiblePersonCode;
	
	@ProcessVariable
	private String count;
	
	@ProcessVariable
	private String transferOutCount;
	
	@ProcessVariable
	@NotNull
	private String  fromLocationCode;					//原始商圈
	
	@ProcessVariable
	private String  fromLocationName;  					//原始商圈name
	
	@ProcessVariable
	@NotNull
	private String  toLocationCode;  					//目标商圈code
	
	@ProcessVariable(isKeyword=true, keywordOrder=1)
	private String processName = "站点调出";
	
	@ProcessVariable(isKeyword=true, keywordOrder=2)
	@NotNull
	private String  toLocationName;   					//目标商圈name
	
	@ProcessVariable
	private String remark;
	
	@ProcessVariable
	private Boolean isBusinessAreaToBusinessArea; 		//true为站点库调出到站点库，false为站点库调出到城市库
	
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

	public String getResponsiblePersonName() {
		return responsiblePersonName;
	}

	public void setResponsiblePersonName(String responsiblePersonName) {
		this.responsiblePersonName = responsiblePersonName;
	}
	
	public String getResponsiblePersonCode() {
		return responsiblePersonCode;
	}
	
	public void setResponsiblePersonCode(String responsiblePersonCode) {
		this.responsiblePersonCode = responsiblePersonCode;
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
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Boolean getIsBusinessAreaToBusinessArea() {
		return isBusinessAreaToBusinessArea;
	}
	
	public void setIsBusinessAreaToBusinessArea(Boolean isBusinessAreaToBusinessArea) {
		this.isBusinessAreaToBusinessArea = isBusinessAreaToBusinessArea;
	}

	
	
}
