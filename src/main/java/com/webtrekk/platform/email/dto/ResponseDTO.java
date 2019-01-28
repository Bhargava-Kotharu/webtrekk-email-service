package com.webtrekk.platform.email.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * REST API sends out the response in this format
 * 
 * @author bkotharu
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDTO {

	private String status;
	private String message;

	public ResponseDTO(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", message=" + message + "]";
	}

}
