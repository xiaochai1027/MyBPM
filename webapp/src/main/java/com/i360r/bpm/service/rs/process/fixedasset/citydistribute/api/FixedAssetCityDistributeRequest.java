package com.i360r.bpm.service.rs.process.fixedasset.citydistribute.api;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class FixedAssetCityDistributeRequest {

	@ProcessVariable
	@NotNull
	private String cityInventoryId;

	@ProcessVariable
	private String fixedAssetTypeCode;

	@ProcessVariable(isKeyword = true, keywordOrder = 3)
	private String fixedAssetTypeName;

	@ProcessVariable
	private String purchasePrice;

	@ProcessVariable
	private String brand;

	@ProcessVariable
	private String number;

	@ProcessVariable
	private String responsiblePersonCode;

	@ProcessVariable
	private String responsiblePersonName;

	@ProcessVariable
	private int count;

	@ProcessVariable
	@NotNull
	private int distributedCount;

	@ProcessVariable
	@NotNull
	private String fromLocationCode;

	@ProcessVariable
	@NotNull
	private String fromLocationName;

	@ProcessVariable
	@NotNull
	private String toLocationCode;

	@ProcessVariable(isKeyword = true, keywordOrder = 1)
	private String processName = "城市分配";

	@ProcessVariable(isKeyword = true, keywordOrder = 2)
	@NotNull
	private String toLocationName;

	@ProcessVariable
	private String remark;

	@ProcessVariable
	@NotNull
	private String inventoryType;
	
	@ProcessVariable
	private String fixedAssetPictureUrl;

	@ProcessVariable
	private String originFixedAssetPictureUrl;
	
	
	
	public String getFixedAssetPictureUrl() {
		return fixedAssetPictureUrl;
	}

	public void setFixedAssetPictureUrl(String fixedAssetPictureUrl) {
		this.fixedAssetPictureUrl = fixedAssetPictureUrl;
	}

	

	public String getOriginFixedAssetPictureUrl() {
		return originFixedAssetPictureUrl;
	}

	public void setOriginFixedAssetPictureUrl(String originFixedAssetPictureUrl) {
		this.originFixedAssetPictureUrl = originFixedAssetPictureUrl;
	}

	public String getCityInventoryId() {
		return cityInventoryId;
	}

	public void setCityInventoryId(String cityInventoryId) {
		this.cityInventoryId = cityInventoryId;
	}

	public String getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
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

	public String getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getDistributedCount() {
		return distributedCount;
	}

	public void setDistributedCount(int distributedCount) {
		this.distributedCount = distributedCount;
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

}
