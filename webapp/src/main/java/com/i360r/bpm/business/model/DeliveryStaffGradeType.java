package com.i360r.bpm.business.model;

import java.util.HashMap;
import java.util.Map;

import com.i360r.framework.i18n.EnumMessageTranslator;

public enum DeliveryStaffGradeType {
	SUPER("SUPER", "特级"),
	A("A", "A级"),
	B("B", "B级"),
	C("C", "C级");
	

    private static final Map<String, DeliveryStaffGradeType> code2Type;

    private String code;
    private String description;
    
    static {
    	code2Type = new HashMap<String, DeliveryStaffGradeType>();

        for (DeliveryStaffGradeType type : DeliveryStaffGradeType.values()) {
        	code2Type.put(type.getCode(), type);
        }
    }

    public static DeliveryStaffGradeType fromCode(String code) {
        return code2Type.get(code);
    }

    private DeliveryStaffGradeType(String code, String description) {
        this.code = code;
        this.description = description;
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
