package com.webtrekk.platform.email.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtrekk.platform.email.dto.EmailDTO;
import com.webtrekk.platform.email.exception.BadRequestException;
import com.webtrekk.platform.email.helper.SendEmailHelper;

/**
 * Service Implementation of {@link EmailService}
 * 
 * @author bkotharu
 *
 */
@Service
public class EmailServiceImpl implements EmailService {

	public static Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Autowired
	private SendEmailHelper mailHelper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sendEmail(EmailDTO emailDto) throws BadRequestException {
		try {
			if (StringUtils.isEmpty(emailDto.getAttachmentURI())) {
				LOGGER.info("Attempting to send email without attachment");
				mailHelper.sendEmailWithoutAttachment(emailDto);
			} else {
				LOGGER.info("Attempting to send email with attachment");
				mailHelper.sendEmailWithAttachment(emailDto);
			}
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.info("Attachment could not be downloaded from the given url={} | error={}",
					emailDto.getAttachmentURI(), e.getMessage());
			throw new BadRequestException(e.getLocalizedMessage());
		} catch (MessagingException e) {
			LOGGER.info("Caught MessagingException. Message={}", e.getMessage());
			throw new BadRequestException(e.getLocalizedMessage());
		}

	}

}
