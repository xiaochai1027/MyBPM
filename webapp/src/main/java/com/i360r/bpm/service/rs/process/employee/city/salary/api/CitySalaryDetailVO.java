package com.i360r.bpm.service.rs.process.employee.city.salary.api;

import com.i360r.bpm.business.model.ProcessVariable;

public class CitySalaryDetailVO {

	@ProcessVariable
	private Integer citySalaryId;
	
	@ProcessVariable
	private String name;
	
	@ProcessVariable
	private String month;

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
}
