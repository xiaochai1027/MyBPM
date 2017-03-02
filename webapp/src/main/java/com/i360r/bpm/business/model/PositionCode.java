package com.i360r.bpm.business.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum PositionCode {
	
	DELIVERY_STATION_MANAGER("DELIVERY_STATION_MANAGER", "物流站长"), 
	DIRECT_MANAGEMENT_PART_TIME_DELIVERY_STAFF("DIRECT_MANAGEMENT_PART_TIME_DELIVERY_STAFF", "兼职配送员"),
	DISPATCHER("DISPATCHER", "调度"),
	RESERVE_CADRE_DELIVERY_STAFF("RESERVE_CADRE_DELIVERY_STAFF", "储备干部配送员"),
	DIRECT_MANAGEMENT_FULL_TIME_DELIVERY_STAFF("DIRECT_MANAGEMENT_FULL_TIME_DELIVERY_STAFF", "全职配送员");	
	
	private static final Map<String, PositionCode> code2Position;
	private static final Map<String, PositionCode> haveLeavePosition;
	
	static {
		code2Position = new HashMap<String, PositionCode>();
		haveLeavePosition = new HashMap<String, PositionCode>();
		
		for (PositionCode partnerCode : PositionCode.values()) {
			code2Position.put(partnerCode.getCode(), partnerCode);
			if (DELIVERY_STATION_MANAGER == partnerCode
					|| DISPATCHER == partnerCode
					|| RESERVE_CADRE_DELIVERY_STAFF == partnerCode
					|| DIRECT_MANAGEMENT_FULL_TIME_DELIVERY_STAFF == partnerCode) {
				haveLeavePosition.put(partnerCode.getCode(), partnerCode);
			}
		}
	}
    
	private final String code;
	private final String description;
	
	public static PositionCode fromCode(String code) {
        return code2Position.get(code);
    }
	
	private PositionCode(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	// 可调休的岗位
    public static List<String> getHaveLeavePositionCodes() {
    	return new ArrayList<String>(haveLeavePosition.keySet());
    }
}