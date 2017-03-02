package com.i360r.bpm.business.model;

import java.util.HashMap;
import java.util.Map;

import com.i360r.framework.i18n.EnumMessageTranslator;
import com.i360r.framework.mybatis.typehandler.IdInterface;

public enum ProcessStatus implements IdInterface{
	
	APPROVING	(1, "APPROVING", 	"审核中"),
	NOT_PASS	(2, "NOT_PASS", 	"未通过"),
	PASS		(3, "PASS", 		"已通过");

    private static final Map<String, ProcessStatus> code2Type;

    private int id;
    private String code;
    private String desc;
    
    static {
    	code2Type = new HashMap<String, ProcessStatus>();

        for (ProcessStatus type : ProcessStatus.values()) {
        	code2Type.put(type.getCode(), type);
        }
    }

    public static ProcessStatus fromCode(String code) {
    	if (code != null) {
    		return code2Type.get(code);
    	} else {
    		return null;
    	}
    }
    
    private ProcessStatus(int id, String code, String desc) {
        this.id = id;
    	this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
		return desc;
	}

	public String getCode() {
        return code;
    }

    @Override
    public int getId() {
		return id;
	}

	public String getName() {
        return EnumMessageTranslator.getName(getClass().getSimpleName() + "." + code);
    }
}
