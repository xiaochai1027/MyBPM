package com.i360r.bpm.service.rs.task.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.framework.common.service.rs.api.PagingRequest;

public class ConditionSearchTaskListRequest extends PagingRequest{
	
	private String type;
	private String condition;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public Map<String,String> argsToMap() {
		Map<String,String> map = new HashMap<String,String>();
		if (!StringUtils.isBlank(this.getCondition())) {
			map.put(ProcessConstants.KEY_CREATED_BY_NAME, this.getCondition());
			map.put(ProcessConstants.KEY_KEYWORD, this.getCondition());
		}
		return map;
	}
}
