package com.i360r.bpm.service.rs.process.fixedasset.scrap.api;

import java.util.Date;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class FixedAssetScrapRequest {
	
	@ProcessVariable
	private String  businessAreaInventoryId;
	
	@ProcessVariable
	private String  scrapTypeCode;
	
	@ProcessVariable
	private String  scrapTypeName;
	
	@ProcessVariable
	private String  count;

	@ProcessVariable
	private String  scrapCount;

	@ProcessVariable
	private String  responsiblePersonName;
	
	@ProcessVariable
	private String  responsiblePersonCode;
	
	@ProcessVariable
	private String  lastUsedPersonCode;
	
	@ProcessVariable
	private String  lastUsedPersonName;

	@ProcessVariable
	private String  remark;

	@ProcessVariable
	@NotNull
	private String  businessAreaCode;

	@ProcessVariable(isKeyword=true, keywordOrder=1)
	private String processName = "报废";
	
	@ProcessVariable(isKeyword=true, keywordOrder=2)
	@NotNull
	private String  businessAreaName;
	
	@ProcessVariable
	private String inventoryType;
	
	@ProcessVariable
	private String scrapPrice;

	@ProcessVariable
	private String scrapPictureUrl;
	
	@ProcessVariable
	private String origScrapPictureUrl;
	
	@ProcessVariable
	private String fixedAssetTypeCode;

	@ProcessVariable(isKeyword = true, keywordOrder = 3)
	private String fixedAssetTypeName;

	@ProcessVariable
	private Date usedDate;
	
	public Date getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
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

	public String getScrapPictureUrl() {
		return scrapPictureUrl;
	}

	public void setScrapPictureUrl(String scrapPictureUrl) {
		this.scrapPictureUrl = scrapPictureUrl;
	}

	public String getOrigScrapPictureUrl() {
		return origScrapPictureUrl;
	}

	public void setOrigScrapPictureUrl(String origScrapPictureUrl) {
		this.origScrapPictureUrl = origScrapPictureUrl;
	}

	public String getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}

	public String getScrapPrice() {
		return scrapPrice;
	}

	public void setScrapPrice(String scrapPrice) {
		this.scrapPrice = scrapPrice;
	}

	public String getBusinessAreaInventoryId() {
		return businessAreaInventoryId;
	}

	public void setBusinessAreaInventoryId(String businessAreaInventoryId) {
		this.businessAreaInventoryId = businessAreaInventoryId;
	}

	public String getScrapTypeCode() {
		return scrapTypeCode;
	}

	public void setScrapTypeCode(String scrapTypeCode) {
		this.scrapTypeCode = scrapTypeCode;
	}

	public String getScrapTypeName() {
		return scrapTypeName;
	}

	public void setScrapTypeName(String scrapTypeName) {
		this.scrapTypeName = scrapTypeName;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getScrapCount() {
		return scrapCount;
	}

	public void setScrapCount(String scrapCount) {
		this.scrapCount = scrapCount;
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

	public String getLastUsedPersonCode() {
		return lastUsedPersonCode;
	}

	public void setLastUsedPersonCode(String lastUsedPersonCode) {
		this.lastUsedPersonCode = lastUsedPersonCode;
	}

	public String getLastUsedPersonName() {
		return lastUsedPersonName;
	}

	public void setLastUsedPersonName(String lastUsedPersonName) {
		this.lastUsedPersonName = lastUsedPersonName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	
}
