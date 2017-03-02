package com.i360r.bpm.service.rs.process.loanApply.api;

import java.io.Serializable;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.model.ProcessStatus;
import com.i360r.bpm.business.util.DateTimeUtility;

public class LoanApplyHistoryVO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String createTime;
	private String loanName;
	private String loanAmount;
	private String actualPaymentTime;
	private String currentTaskName;
	private String statusName;
	private String note;
	
	public static LoanApplyHistoryVO toVO(HistoricProcessInstance hpi, ProcessDefinition pd,
			Map<String, Object> variabelMap, String taskName) {
		LoanApplyHistoryVO vo = new LoanApplyHistoryVO();
		vo.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMMSS(hpi.getStartTime()));
		vo.setLoanName(pd.getName());
		vo.setLoanAmount(String.valueOf(variabelMap.get("amount")));
		vo.setActualPaymentTime((String)variabelMap.get("actualPaymentTime"));
		vo.setCurrentTaskName(taskName);
		Boolean status = (Boolean)variabelMap.get(ProcessConstants.KEY_PASS);
		if(status == null) {
			vo.setStatusName(ProcessStatus.APPROVING.getName());
		}else if(status) {
			vo.setStatusName(ProcessStatus.PASS.getName());
		}else {
			vo.setStatusName(ProcessStatus.NOT_PASS.getName());
		}
		vo.setNote((String)variabelMap.get("note"));
		
		return vo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getActualPaymentTime() {
		return actualPaymentTime;
	}

	public void setActualPaymentTime(String actualPaymentTime) {
		this.actualPaymentTime = actualPaymentTime;
	}

	public String getCurrentTaskName() {
		return currentTaskName;
	}

	public void setCurrentTaskName(String currentTaskName) {
		this.currentTaskName = currentTaskName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
}
