package com.i360r.bpm.service.rs.task.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.framework.common.service.rs.api.PagingRequest;

public class GetMyCurrentTaskRequest extends PagingRequest {

	private String processInstanceId;
	private String processName;
	private String beginDate;
	private String endDate;
	private String createdByName;
	private String keyword;
	
	private String currentAssigneeName;
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCurrentAssigneeName() {
		return currentAssigneeName;
	}
	public void setCurrentAssigneeName(String currentAssigneeName) {
		this.currentAssigneeName = currentAssigneeName;
	}
	public Map<String,String> argsToMap (){
		Map<String,String> map = new HashMap<String,String>();
		if (!StringUtils.isBlank(this.getCreatedByName())) {
			map.put(ProcessConstants.KEY_CREATED_BY_NAME,this.getCreatedByName());
		}
		if (!StringUtils.isBlank(this.getKeyword())) {
			map.put(ProcessConstants.KEY_KEYWORD,this.getKeyword());
		}
		return map;
	}
	
}
