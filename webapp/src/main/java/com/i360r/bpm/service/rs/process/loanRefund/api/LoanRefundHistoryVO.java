package com.i360r.bpm.service.rs.process.loanRefund.api;

import java.io.Serializable;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.model.ProcessStatus;
import com.i360r.bpm.business.util.DateTimeUtility;

public class LoanRefundHistoryVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String createTime;
	private String loanName;
	private String loanAmount;
	private String actualTurnoverTime;
	private String currentTaskName;
	private String statusName;
	private String note;
	
	public static LoanRefundHistoryVO toVO(HistoricProcessInstance hpi, ProcessDefinition pd, Map<String, Object> variableMap, String taskName) {
		LoanRefundHistoryVO vo = new LoanRefundHistoryVO();
		vo.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMMSS(hpi.getStartTime()));
		vo.setLoanName(pd.getName());
		vo.setLoanAmount(String.valueOf(variableMap.get("amount")));
		vo.setActualTurnoverTime((String)variableMap.get("actualTurnoverTime"));
		vo.setCurrentTaskName(taskName);
		Boolean status = variableMap.containsKey(ProcessConstants.KEY_PASS);
		if(status == null) {
			vo.setStatusName(ProcessStatus.APPROVING.getName());
		}else if(status) {
			vo.setStatusName(ProcessStatus.PASS.getName());
		}else {
			vo.setStatusName(ProcessStatus.NOT_PASS.getName());
		}
		vo.setNote((String)variableMap.get("note"));
		
		return vo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
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

	public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	public String getActualTurnoverTime() {
		return actualTurnoverTime;
	}

	public void setActualTurnoverTime(String actualTurnoverTime) {
		this.actualTurnoverTime = actualTurnoverTime;
	}
	
	
}
