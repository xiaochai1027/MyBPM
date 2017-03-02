package com.i360r.bpm.business.model;

import java.util.HashMap;
import java.util.Map;

public enum EmployeeStatus {
	IN_POSITION		("IN_POSITION"), 
	OUT_OF_POSITION	("OUT_OF_POSITION"),
	ON_TRIAL		("ON_TRIAL");
    
    private static final Map<String, EmployeeStatus> code2EmployeeStatus;

    private String code;
    
    static {
    	code2EmployeeStatus = new HashMap<String, EmployeeStatus>();

        for (EmployeeStatus employeeStatus : EmployeeStatus.values()) {
        	code2EmployeeStatus.put(employeeStatus.getCode(), employeeStatus);
        }
    }

    public static EmployeeStatus fromCode(String code) {
    	if (code != null) {
    		 return code2EmployeeStatus.get(code);
		}else {
			return null;
		}
    }
    
    EmployeeStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
