package com.webtrekk.platform.email.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.webtrekk.platform.email.constants.EmailApplicationConstants;

/**
 * Email Request
 * 
 * @author bkotharu
 *
 */
public class EmailDTO {

	@NotNull(message = EmailApplicationConstants.INALID_TO_ADDRESS_ERROR_MESSAGE)
	@Email(message = EmailApplicationConstants.INALID_EMAIL_ADDRESS_ERROR_MESSAGE)
	private String toAddress;

	@NotNull(message = EmailApplicationConstants.INALID_TO_ADDRESS_ERROR_MESSAGE)
	private String subject;

	private String body;

	private String attachmentURI;

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAttachmentURI() {
		return attachmentURI;
	}

	public void setAttachmentURI(String attachmentURI) {
		this.attachmentURI = attachmentURI;
	}

	@Override
	public String toString() {
		return "EmailDto [toAddress=" + toAddress + ", subject=" + subject + ", body=" + body + ", attachmentURI="
				+ attachmentURI + "]";
	}

}
