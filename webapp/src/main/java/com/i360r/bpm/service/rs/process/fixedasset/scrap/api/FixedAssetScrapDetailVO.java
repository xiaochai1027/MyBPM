package com.i360r.bpm.service.rs.process.fixedasset.scrap.api;

import java.util.Date;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.BusinessAreaBasedVO;
import com.i360r.framework.util.DateTimeUtility;

public class FixedAssetScrapDetailVO extends BusinessAreaBasedVO {

	@ProcessVariable
	private String businessAreaInventoryId;

	@ProcessVariable
	private String scrapTypeCode;

	@ProcessVariable
	private String scrapTypeName;

	@ProcessVariable
	private String scrapCount;

	@ProcessVariable
	private String responsiblePersonName;

	@ProcessVariable
	private String lastUsedPersonName;

	@ProcessVariable
	private String remark;

	@ProcessVariable
	private String businessAreaName;

	@ProcessVariable
	private String number;

	@ProcessVariable
	private String inventoryType;

	@ProcessVariable
	private String scrapPrice;

	@ProcessVariable
	private String fixedAssetTypeCode;

	@ProcessVariable
	private String fixedAssetTypeName;

	@ProcessVariable
	private String scrapPictureUrl;

	@ProcessVariable
	private String origScrapPictureUrl;
	
	@ProcessVariable
	private Date usedDate;

	public String getUsedDate() {
		String dateString = DateTimeUtility.formatYYYYMMDD(DateTimeUtility.getDate(usedDate));
		return dateString;
	}

	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
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

	public String getBusinessAreaName() {
		return businessAreaName;
	}

	public void setBusinessAreaName(String businessAreaName) {
		this.businessAreaName = businessAreaName;
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

}
