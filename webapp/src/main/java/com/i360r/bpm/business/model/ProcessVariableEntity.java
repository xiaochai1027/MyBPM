package com.i360r.bpm.business.model;

public class ProcessVariableEntity {

	private String key;
	private Object value;
	private String showName;
	private ProcessUniqueScope uniqueScope;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public ProcessUniqueScope getUniqueScope() {
		return uniqueScope;
	}
	public void setUniqueScope(ProcessUniqueScope uniqueScope) {
		this.uniqueScope = uniqueScope;
	}
}
