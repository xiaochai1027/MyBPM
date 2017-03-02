package com.i360r.bpm.business.exception;

public class WithdrawWalletBeyondUseableWalletException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public WithdrawWalletBeyondUseableWalletException() {
        super();
    }

    public WithdrawWalletBeyondUseableWalletException(String message, Throwable cause) {
        super(message, cause);
    }

    public WithdrawWalletBeyondUseableWalletException(String message) {
        super(message);
    }

    public WithdrawWalletBeyondUseableWalletException(Throwable cause) {
        super(cause);
    }

}
