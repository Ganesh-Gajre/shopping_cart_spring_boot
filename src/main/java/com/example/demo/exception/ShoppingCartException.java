package com.example.demo.exception;

public class ShoppingCartException extends Exception {
	private int httpStatusCode;
	public ShoppingCartException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShoppingCartException(int httpStatusCode, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.httpStatusCode = httpStatusCode;
		// TODO Auto-generated constructor stub
	}

	public ShoppingCartException(int httpStatusCode, String message, Throwable cause) {
		super(message, cause);
		this.httpStatusCode = httpStatusCode;
		// TODO Auto-generated constructor stub
	}

	public ShoppingCartException(int httpStatusCode, String message) {
		super(message);
		this.httpStatusCode = httpStatusCode;
		// TODO Auto-generated constructor stub
	}

	public ShoppingCartException(int httpStatusCode, Throwable cause) {
		super(cause);
		this.httpStatusCode = httpStatusCode;
		// TODO Auto-generated constructor stub
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

}
