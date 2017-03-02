package com.i360r.bpm.service.rs.process.fixedasset;

import com.i360r.framework.common.service.rs.api.ServiceResponse;

public class ResponsiblePersonResponse extends ServiceResponse {
  
	private static final long serialVersionUID = 1L;

	private String responsiblePersonCode;
   
	private String responsiblePersonName;

	public String getResponsiblePersonCode() {
		return responsiblePersonCode;
	}

	public void setResponsiblePersonCode(String responsiblePersonCode) {
		this.responsiblePersonCode = responsiblePersonCode;
	}

	public String getResponsiblePersonName() {
		return responsiblePersonName;
	}

	public void setResponsiblePersonName(String responsiblePersonName) {
		this.responsiblePersonName = responsiblePersonName;
	}
    

    
    

}
