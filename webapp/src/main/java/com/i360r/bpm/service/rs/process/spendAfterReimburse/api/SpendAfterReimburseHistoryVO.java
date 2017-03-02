package com.i360r.bpm.service.rs.process.spendAfterReimburse.api;

import java.io.Serializable;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.bpm.business.model.ProcessStatus;
import com.i360r.bpm.business.util.DateTimeUtility;

public class SpendAfterReimburseHistoryVO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String createTime;
	private String reimburseName;
	private String amount;
	private String actualPaymentTime;
	private String currentTaskName;
	private String statusName;
	private String note;
	private String processInstanceId;
	
	public static SpendAfterReimburseHistoryVO toVO(ProcessDefinition pd, HistoricProcessInstance hpi,
			Map<String, Object> variableMaps, String taskName) {
		SpendAfterReimburseHistoryVO vo = new SpendAfterReimburseHistoryVO();
		
		vo.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMMSS(hpi.getStartTime()));
		vo.setReimburseName(pd.getName());
		vo.setAmount(String.valueOf(variableMaps.get("totalAmount")));
		vo.setActualPaymentTime((String)variableMaps.get("actualPaymentTime"));
		vo.setCurrentTaskName(taskName);
		vo.setProcessInstanceId(hpi.getId());
		vo.setNote((String)variableMaps.get("note"));
		
		Boolean status = (Boolean)variableMaps.get(ProcessConstants.KEY_PASS);
		if(status == null) {
			vo.setStatusName(ProcessStatus.APPROVING.getName());
		} else if(status) {
			vo.setStatusName(ProcessStatus.PASS.getName());
		} else {
			vo.setStatusName(ProcessStatus.NOT_PASS.getName());
		}
		
		return vo;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getReimburseName() {
		return reimburseName;
	}
	public void setReimburseName(String reimburseName) {
		this.reimburseName = reimburseName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
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
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	
}
