package com.webtrekk.platform.email.exception;

/**
 * Custom exception for Server side errors
 * 
 * @author bkotharu
 *
 */
public class InternalServerException extends Exception {

	private static final long serialVersionUID = 1L;

	public InternalServerException(String message) {
		super(message);
	}

}
