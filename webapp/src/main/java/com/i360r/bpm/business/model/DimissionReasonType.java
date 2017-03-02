package com.i360r.bpm.business.model;

import java.util.HashMap;
import java.util.Map;

public enum DimissionReasonType {

	BACK_HOME(1, "BACK_HOME", "回老家"),
	CHANGE_INDUSTRY(2, "CHANGE_INDUSTRY", "换行业"),
	GET_OTHER_JOB(3, "GET_OTHER_JOB", "另外找到工作"),
	HEALTH(4, "HEALTH", "身体原因"),
	VOLUNTARY_TURNOVER_OTHER(5, "VOLUNTARY_TURNOVER_OTHER","其他原因"),
	NOT_COMPETENT(6, "NOT_COMPETENT", "不能胜任工作"),
	ILLNESS(7, "ILLNESS", "病退"),
	ABSENT(8, "ABSENT", "旷工"),
	DISMISS_OTHER(9, "DISMISS_OTHER", "其他原因"),
	TRANSFER(10, "TRANSFER", "转岗"),
	PROBATIONARY_PERIOD_TIMEOUT(11, "PROBATIONARY_PERIOD_TIMEOUT", "试用期超时");
	
	private static final Map<String, DimissionReasonType> codeDimissionReasonType;
	
	private int id;
	private String code;
	private String description;
	
	static {
		codeDimissionReasonType = new HashMap<String, DimissionReasonType>();
		
		for (DimissionReasonType type : DimissionReasonType.values()) {
			codeDimissionReasonType.put(type.code, type);
		}
	}
	
	private DimissionReasonType(int id, String code, String description) {
		this.id = id;
		this.code = code;
		this.description = description;
	}
	
	public static DimissionReasonType fromCode(String code) {
		return codeDimissionReasonType.get(code);
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
