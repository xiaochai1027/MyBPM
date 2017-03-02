package com.i360r.bpm.service.rs.process.employee.city.salary.api;

import com.i360r.bpm.business.model.ProcessUniqueScope;
import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.bpm.service.rs.process.api.TaskRequest;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class CitySalaryRequest extends TaskRequest {
	
	@ProcessVariable(isUnique=true, uniqueScope=ProcessUniqueScope.UNFINISHED, showName="该月工资审核")
	@NotNull
	private Integer citySalaryId;
	
	@ProcessVariable(isKeyword=true)
	private String name;
	
	@ProcessVariable(isKeyword=true)
	private String month;

	@ProcessVariable
	private String cityCode;
	
	@ProcessVariable(isKeyword=true)
	private String cityName;
	
	public Integer getCitySalaryId() {
		return citySalaryId;
	}

	public void setCitySalaryId(Integer citySalaryId) {
		this.citySalaryId = citySalaryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
  
}
