package com.i360r.bpm.service.rs.process.api;

public interface IContinueTaskRequest extends ITaskRequest {

	public Boolean getContinuation();

	public void setContinuation(Boolean continuation);
	
}
