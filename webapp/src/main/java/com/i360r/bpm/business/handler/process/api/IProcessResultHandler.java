package com.i360r.bpm.business.handler.process.api;

import org.activiti.engine.delegate.DelegateExecution;


public interface IProcessResultHandler {
	
	public void onProcessPass(DelegateExecution execution) throws Exception;
	
	public void onProcessNotPass(DelegateExecution execution) throws Exception;
	
	public String getProcessDefinitionKey();
	
}
