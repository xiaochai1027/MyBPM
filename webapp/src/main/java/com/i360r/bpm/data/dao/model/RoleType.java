package com.i360r.bpm.data.dao.model;

import java.util.HashMap;
import java.util.Map;

public enum RoleType {
	DELIVERY_STAFF (1, "DELIVERY_STAFF"),
	STATION_MANAGER (2, "STATION_MANAGER");
	
	private int id;
	private String code;
	
	private static final Map<String,RoleType> code2RoleType;
	
	static {
		code2RoleType = new HashMap<String, RoleType>();
        
        for (RoleType role : RoleType.values()) {
        	code2RoleType.put(role.getCode(), role);
        }
    }
	
	public static RoleType fromCode(String code) {
    	if (code != null) {
    		return code2RoleType.get(code);
    	} else {
    		return null;
    	}
    }
	
	private RoleType(int id, String code) {
		this.id = id ;
		this.code = code;
	}
	
	public  String getCode() {
        return code;
    }

	public int getId() {
		return id;
	}

}
