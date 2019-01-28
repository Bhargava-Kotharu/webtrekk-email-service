package com.webtrekk.platform.email.exception;

/**
 * Custom exception for client side errors
 * 
 * @author bkotharu
 *
 */
public class BadRequestException extends Exception {

	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
	}

}
