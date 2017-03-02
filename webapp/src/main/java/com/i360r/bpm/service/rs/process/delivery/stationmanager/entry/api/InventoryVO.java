package com.i360r.bpm.service.rs.process.delivery.stationmanager.entry.api;

import java.io.Serializable;

public class InventoryVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer inventoryId;	//	资产id
	private Integer count;	//	绑定资产数量
	
	public Integer getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

}
