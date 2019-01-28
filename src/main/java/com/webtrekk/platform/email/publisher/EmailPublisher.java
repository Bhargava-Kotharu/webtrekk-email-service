package com.webtrekk.platform.email.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.webtrekk.platform.email.dto.EmailDTO;
import com.webtrekk.platform.email.dto.Headers;

/**
 * Publishes the email message to the topic
 * 
 * @author bkotharu
 *
 */
@Component
public class EmailPublisher {

	public static Logger LOGGER = LoggerFactory.getLogger(EmailPublisher.class);

	private static final String TOPIC_NAME = "app.email.topic-name";

	@Autowired
	private KafkaTemplate<String, EmailDTO> kafkaTemplate;

	/**
	 * Publishes the message to the topic
	 * 
	 * @param headers
	 * @param emailDTO
	 */
	public void publishMessage(EmailDTO emailDTO) {
		LOGGER.info("Publishing send email message to topic={} | message={}", TOPIC_NAME, emailDTO);
		ListenableFuture<SendResult<String, EmailDTO>> sendResult = kafkaTemplate.send(TOPIC_NAME, emailDTO);
		LOGGER.info("Send email message published successfully to topic={} | message={}", TOPIC_NAME, emailDTO);
	}

}
