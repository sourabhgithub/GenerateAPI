package com.trustmarkins.mesd.exception;

public class TMKException extends Exception {

	private static final long serialVersionUID = 3630191310409559680L;

	public TMKException() {
	}

	public TMKException(String message) {
		super(message);
	}

	public TMKException(Throwable cause) {
		super(cause);
	}

	public TMKException(String message, Throwable cause) {
		super(message, cause);
	}

	public TMKException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
