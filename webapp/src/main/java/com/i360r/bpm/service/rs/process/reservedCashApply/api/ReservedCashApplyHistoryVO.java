package com.i360r.bpm.service.rs.process.reservedCashApply.api;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.model.ProcessStatus;
import com.i360r.bpm.business.util.DateTimeUtility;

public class ReservedCashApplyHistoryVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String createTime;
	private String reservedCashName;
	private String reservedCashAmountBefore;
	private String reservedCashAmountAfter;
	private String actualPaymentTime;
	private String currentTaskName;
	private String statusName;
	private String note;
	
	public static ReservedCashApplyHistoryVO toVO(ProcessDefinition pd, HistoricProcessInstance hpi,
			Map<String, Object> variableMap, String currentTaskName) {
		ReservedCashApplyHistoryVO vo = new ReservedCashApplyHistoryVO();
		
		vo.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMMSS(hpi.getStartTime()));
		vo.setReservedCashName(pd.getName());
		BigDecimal amountBefore = (BigDecimal)variableMap.get("reservedCashAmount");
		BigDecimal changeAmount = (BigDecimal)variableMap.get("changeAmount");
		BigDecimal amountAfter = amountBefore.add(changeAmount);
		vo.setReservedCashAmountBefore(String.valueOf(amountBefore));
		vo.setReservedCashAmountAfter(String.valueOf(amountAfter));
		vo.setActualPaymentTime((String)variableMap.get("actualPaymentTime"));
		vo.setCurrentTaskName(currentTaskName);
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
	public String getReservedCashName() {
		return reservedCashName;
	}
	public void setReservedCashName(String reservedCashName) {
		this.reservedCashName = reservedCashName;
	}
	public String getReservedCashAmountBefore() {
		return reservedCashAmountBefore;
	}
	public void setReservedCashAmountBefore(String reservedCashAmountBefore) {
		this.reservedCashAmountBefore = reservedCashAmountBefore;
	}
	public String getReservedCashAmountAfter() {
		return reservedCashAmountAfter;
	}
	public void setReservedCashAmountAfter(String reservedCashAmountAfter) {
		this.reservedCashAmountAfter = reservedCashAmountAfter;
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
