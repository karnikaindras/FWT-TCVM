package com.yash.tcvm.exception;

public class NullValueNotAllowedException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullValueNotAllowedException(String message) {
		super(message);
	}

}
