package com.i360r.bpm.business.model;

import java.util.HashMap;
import java.util.Map;

import com.i360r.framework.i18n.EnumMessageTranslator;
import com.i360r.framework.mybatis.typehandler.IdInterface;

public enum ContractType implements IdInterface {
	LABOR_CONTRACT					(1, "LABOR_CONTRACT", "labor contract"), 
	SERVICE_CONTRACT				(2, "SERVICE_CONTRACT", "service contract"),
    PART_TIME_CONTRACT				(3, "PART_TIME_CONTRACT", "part time contract"), 
    INTERNSHIP_AGREEMENT			(4, "INTERNSHIP_AGREEMENT", "internship agreement");
    
    private static final Map<String, ContractType> code2OrderCancelReason;

    private int id;
    private String code;
    private String description;
    
    static {
        code2OrderCancelReason = new HashMap<String, ContractType>();

        for (ContractType orderCancelReason : ContractType.values()) {
            code2OrderCancelReason.put(orderCancelReason.getCode(), orderCancelReason);
        }
    
    }

    public static ContractType fromCode(String code) {
        return code2OrderCancelReason.get(code);
    }

    ContractType(int id, String code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }
    
    @Override
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
