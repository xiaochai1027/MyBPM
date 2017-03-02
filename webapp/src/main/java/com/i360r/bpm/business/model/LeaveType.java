package com.i360r.bpm.business.model;

import java.util.HashMap;
import java.util.Map;

import com.i360r.framework.i18n.EnumMessageTranslator;

public enum LeaveType {

	NORMAL	(1, "NORMAL", 	"普通休假"),
	SICKNESS(2, "SICKNESS", "病假"), 
	INJURY	(3, "INJURY", 	"伤假"),
	PERSONAL(4, "PERSONAL", "事假");
	
    private static final Map<String, LeaveType> code2LeaveType;
    
    private int id;
    private String code;
    private String description;
    
    static {
    	code2LeaveType = new HashMap<String, LeaveType>();

        for (LeaveType type : LeaveType.values()) {
        	code2LeaveType.put(type.getCode(), type);
        }
    
    }

    public static LeaveType fromCode(String code) {
        return code2LeaveType.get(code);
    }

    private LeaveType(int id, String code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
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

	public String getName() {
        return EnumMessageTranslator.getName(getClass().getSimpleName() + "." + code);
    }
	
}
