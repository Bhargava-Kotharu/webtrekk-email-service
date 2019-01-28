package com.webtrekk.platform.email.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.webtrekk.platform.email.constants.EmailApplicationConstants;
import com.webtrekk.platform.email.dto.EmailDTO;
import com.webtrekk.platform.email.dto.Headers;
import com.webtrekk.platform.email.exception.BadRequestException;
import com.webtrekk.platform.email.service.EmailService;

/**
 * Listener component to consume email messages and send emails
 * 
 * @author bkotharu
 *
 */
@Component
public class EmailListener {

	public static Logger LOGGER = LoggerFactory.getLogger(EmailListener.class);

	@Autowired
	private EmailService emailService;

	/**
	 * Sends email on receiving the message
	 * 
	 * @param message
	 * @throws BadRequestException
	 */
	@KafkaListener(topics = EmailApplicationConstants.TOPIC_NAME, groupId = EmailApplicationConstants.GROUP_NAME)
	public void onMessage(@Payload EmailDTO emailDTO) throws BadRequestException {

		LOGGER.info("Send email message Consumed Group={} | Payload={}", EmailApplicationConstants.GROUP_NAME,
				emailDTO);
		try {
			emailService.sendEmail(emailDTO);
			LOGGER.info(
					String.format(EmailApplicationConstants.EMAIL_SUCCESS_RESPONSE_MESSAGE, emailDTO.getToAddress()));
		} catch (Exception e) {
			// Not differentiating between functional/operational failures.
			LOGGER.error("Error sending email.", e);
			throw e;
		}

	}

}
