package com.i360r.bpm.service.rs.task.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.i360r.bpm.business.handler.process.ProcessConstants;
import com.i360r.framework.common.service.rs.api.PagingRequest;

public class KeywordSearchTaskListRequest extends PagingRequest  {

	private String keyword;
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Map<String,String> argsToMap (){
		Map<String,String> map = new HashMap<String,String>();
		if (StringUtils.isNotBlank(this.getKeyword())) {
			map.put(ProcessConstants.KEY_CREATED_BY_NAME, this.getKeyword());
			map.put(ProcessConstants.KEY_KEYWORD, this.getKeyword());
		}
		
		return map;
	}
}
