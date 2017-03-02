package com.i360r.bpm.business.exception;

import com.i360r.framework.service.api.exception.ParamedException;

public class FixedAssetTransferOutException extends ParamedException {

	private static final long serialVersionUID = 5831076867058855168L;

	public FixedAssetTransferOutException(String msg, String name) {
        super(msg);
        setParams(new Object[]{name});
    }

    public FixedAssetTransferOutException(Throwable cause, String name) {
        super(cause);
        setParams(new Object[]{name});
    }
	
}
