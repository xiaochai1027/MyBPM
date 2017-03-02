package com.i360r.bpm.business.model;

import java.util.HashMap;
import java.util.Map;

public enum DimissionType {

	VOLUNTARY_TURNOVER(1, "VOLUNTARY_TURNOVER", "主动离职"),
	DISMISS(2, "DISMISS", "辞退");
	
	private static Map<String, DimissionType> codeDimissionType;
	
	private int id;
	private String code;
	private String description;
	
	static {
		codeDimissionType = new HashMap<String, DimissionType>();
		for (DimissionType type : DimissionType.values()) {
			codeDimissionType.put(type.code, type);
		}
	}
	
	private DimissionType(int id, String code, String description) {
		this.id = id;
		this.code = code;
		this.description = description;
	}

	public static DimissionType fromCode(String code) {
		return codeDimissionType.get(code);
	}
	
	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	
}
