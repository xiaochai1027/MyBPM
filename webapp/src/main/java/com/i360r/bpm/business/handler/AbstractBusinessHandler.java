package com.i360r.bpm.business.handler;

import com.i360r.bpm.business.exception.RemoteServerException;
import com.i360r.framework.common.service.rs.api.ServiceResponse;
import com.i360r.framework.service.StatusCodeManager;

public abstract class AbstractBusinessHandler {

	protected void checkResponseSuccess(ServiceResponse response) {
		if (response == null) {
			throw new RemoteServerException("系统错误(response is null)");
		}
		if (!StatusCodeManager.getSucceedCode().equals(response.getResultCode())) {
			throw new RemoteServerException(response.getResultMessage());
		}
	}
	
}
