package com.i360r.bpm.service.rs.process.employee.global.salary.api;

import java.util.List;

import com.i360r.bpm.business.model.ProcessVariable;

public class GlobalSalaryDetailVO {

	@ProcessVariable
	private Integer globalSalaryId;
	
	@ProcessVariable
	private String name;
	
	@ProcessVariable
	private String month;
	
	@ProcessVariable
	private List<Integer> unapprovedCitySalaryIds;
	
	public Integer getGlobalSalaryId() {
		return globalSalaryId;
	}

	public void setGlobalSalaryId(Integer globalSalaryId) {
		this.globalSalaryId = globalSalaryId;
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

	public List<Integer> getUnapprovedCitySalaryIds() {
		return unapprovedCitySalaryIds;
	}

	public void setUnapprovedCitySalaryIds(List<Integer> unapprovedCitySalaryIds) {
		this.unapprovedCitySalaryIds = unapprovedCitySalaryIds;
	}
	
	
}
