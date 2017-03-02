package com.i360r.bpm.service.rs.process.fixedasset.citydistributeunchecked.api;

import java.util.List;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.BusinessAreaBasedVO;

public class FixedAssetCityDistributeUncheckedDetailVO extends BusinessAreaBasedVO {

	@ProcessVariable
	private String createdByName;
	
	@ProcessVariable
	private String creatorPositionNames;
	
	@ProcessVariable
	private String creatorMobile;
	
	@ProcessVariable
	private String fromLocationCode;
	
	@ProcessVariable
	private String fromLocationName;
	
	@ProcessVariable
	private String toLocationCode;
	
	@ProcessVariable
	private String toLocationName;
	
	@ProcessVariable
	private String fixedAssetTypeCode;
	
	@ProcessVariable
	private String fixedAssetTypeName;
	
	@ProcessVariable
	private String brand;
	
	@ProcessVariable
	private String purchasePrice;
	
	@ProcessVariable
	private int distributedCount;

	@ProcessVariable
	private String number;
	
	@ProcessVariable
	private String remark;
	
	@ProcessVariable
	private List<FixedAssetDistributeUncheckedNumberDetail> fixedAssetDistributeNumberDetails;

	@ProcessVariable
	private String  cityInventoryId;
	
	@ProcessVariable
	private String inventoryType;
	
	@ProcessVariable
	private String responsiblePersonName;
	
	@ProcessVariable
	private String responsiblePersonCode;	
	
	@ProcessVariable
	private String fixedAssetPictureUrl;

	@ProcessVariable
	private String originFixedAssetPictureUrl;
	
	@ProcessVariable
	private boolean changeNumber;
	

	public boolean isChangeNumber() {
		return changeNumber;
	}

	public void setChangeNumber(boolean changeNumber) {
		this.changeNumber = changeNumber;
	}

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

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public String getCreatorPositionNames() {
		return creatorPositionNames;
	}

	public void setCreatorPositionNames(String creatorPositionNames) {
		this.creatorPositionNames = creatorPositionNames;
	}

	public String getCreatorMobile() {
		return creatorMobile;
	}

	public void setCreatorMobile(String creatorMobile) {
		this.creatorMobile = creatorMobile;
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

	public String getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public int getDistributedCount() {
		return distributedCount;
	}

	public void setDistributedCount(int distributedCount) {
		this.distributedCount = distributedCount;
	}

	public List<FixedAssetDistributeUncheckedNumberDetail> getFixedAssetDistributeNumberDetails() {
		return fixedAssetDistributeNumberDetails;
	}

	public void setFixedAssetDistributeNumberDetails(
			List<FixedAssetDistributeUncheckedNumberDetail> fixedAssetDistributeNumberDetails) {
		this.fixedAssetDistributeNumberDetails = fixedAssetDistributeNumberDetails;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
