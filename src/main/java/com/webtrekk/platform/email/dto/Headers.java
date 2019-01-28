package com.webtrekk.platform.email.dto;

/**
 * Custom Headers
 * 
 * @author bkotharu
 *
 */
public class Headers {

	private String transactionId;
	private String correlationId;

	public Headers(String transactionId, String correlationId) {
		super();
		this.transactionId = transactionId;
		this.correlationId = correlationId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

}
