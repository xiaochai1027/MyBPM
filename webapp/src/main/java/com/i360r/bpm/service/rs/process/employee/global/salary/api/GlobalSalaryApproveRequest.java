package com.i360r.bpm.service.rs.process.employee.global.salary.api;

import java.util.List;

import com.i360r.bpm.service.rs.process.api.ApproveRequest;

public class GlobalSalaryApproveRequest extends ApproveRequest {

	private List<Integer> unapprovedCitySalaryIds;

	public List<Integer> getUnapprovedCitySalaryIds() {
		return unapprovedCitySalaryIds;
	}

	public void setUnapprovedCitySalaryIds(List<Integer> unapprovedCitySalaryIds) {
		this.unapprovedCitySalaryIds = unapprovedCitySalaryIds;
	}
}
