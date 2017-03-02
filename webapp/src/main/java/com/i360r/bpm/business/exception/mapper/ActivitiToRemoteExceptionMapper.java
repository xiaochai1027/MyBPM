package com.i360r.bpm.business.exception.mapper;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.javax.el.ELException;

import com.i360r.bpm.business.exception.RemoteServerException;
import com.i360r.framework.service.api.exception.IExceptionMapper;

public class ActivitiToRemoteExceptionMapper implements IExceptionMapper<ActivitiException> {

	@Override
	public Exception mapping(ActivitiException actException) {
		if (actException == null) {
			return actException;
		}
		Throwable exception1 = actException.getCause();
		if (exception1 == null) {
			return actException;
		}
		if (exception1 instanceof RemoteServerException) {
			return (RemoteServerException)exception1;
		} else if (exception1 instanceof ELException) {
			Throwable exception2 = exception1.getCause();
			if (exception2 == null) {
				return actException;
			}
			if (exception2 instanceof RemoteServerException) {
				return (RemoteServerException)exception2;
			}
		}
		
		return actException;
	}

	@Override
	public String getClassName() {
		return ActivitiException.class.getName();
	}
}
