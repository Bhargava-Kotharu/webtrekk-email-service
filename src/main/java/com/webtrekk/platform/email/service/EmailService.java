package com.webtrekk.platform.email.service;

import com.webtrekk.platform.email.dto.EmailDTO;
import com.webtrekk.platform.email.exception.BadRequestException;

/**
 * Service to handle email request
 * 
 * @author bkotharu
 *
 */
public interface EmailService {

	/**
	 * Sends email to the given emailId
	 * 
	 * @param emailDto
	 * @throws BadRequestException
	 */
	public void sendEmail(EmailDTO emailDto) throws BadRequestException;

}
