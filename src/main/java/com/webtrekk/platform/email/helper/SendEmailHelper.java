package com.webtrekk.platform.email.helper;

import java.io.IOException;

import javax.mail.MessagingException;

import com.webtrekk.platform.email.dto.EmailDTO;

public interface SendEmailHelper {

	/**
	 * Sends email without attachment
	 * 
	 * @param {@link
	 * 			EmailDTO}
	 */
	public void sendEmailWithoutAttachment(EmailDTO emailDto);

	/**
	 * Sends email with attachment. Attachment will be fetched from the
	 * attachmentURI provided.
	 * 
	 * @param {@link
	 * 			EmailDTO}
	 * @throws MessagingException
	 * @throws IOException
	 */
	public void sendEmailWithAttachment(EmailDTO emailDto) throws MessagingException, IOException;
}
