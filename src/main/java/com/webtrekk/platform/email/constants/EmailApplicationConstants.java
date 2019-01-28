package com.webtrekk.platform.email.constants;

public interface EmailApplicationConstants {

	String TOPIC_NAME = "app.email.topic-name";
	String GROUP_NAME = "email";
	
	String HEADER_TRANSACTION_ID_KEY = "Transaction-ID";
	String HEADER_CORRELATION_ID_KEY = "Correlation-ID";

	// Validation Constants
	String INALID_TO_ADDRESS_ERROR_MESSAGE = "Recepient email address is required";
	String INALID_EMAIL_ADDRESS_ERROR_MESSAGE = "Recepient email address provided is in invalid email format.";
	String EMPTY_SUBJECT_ERROR_MESSAGE = "Email Subject is required";

	// Response Message Constants
	String STATUS_SUCCESS = "SUCCESS";
	String STATUS_ERROR = "ERROR";
	String EMAIL_SUCCESS_RESPONSE_MESSAGE = "Email sent successfully to %s";
	String EMAIL_REQUEST_ACCEPTED_MESSAGE = "Email request accepted.";
}
