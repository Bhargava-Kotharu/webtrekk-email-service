package com.webtrekk.platform.email.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webtrekk.platform.email.constants.EmailApplicationConstants;
import com.webtrekk.platform.email.dto.EmailDTO;
import com.webtrekk.platform.email.dto.Headers;
import com.webtrekk.platform.email.dto.ResponseDTO;
import com.webtrekk.platform.email.publisher.EmailPublisher;

/**
 * Rest Endpoint for sending email asynchronously
 * 
 * @author bkotharu
 *
 */
@Validated
@RestController
@RequestMapping("/email")
public class AsynchronousEmailController {

	public static Logger LOGGER = LoggerFactory.getLogger(AsynchronousEmailController.class);

	@Autowired
	private EmailPublisher producer;

	/**
	 * Publishes the message to the queue for sending email asynchronously
	 * 
	 * @param emailDto
	 * @return {@link ResponseEntity<ResponseDTO>}
	 */
	@PostMapping
	public ResponseEntity<ResponseDTO> sendEmail(@Valid @RequestBody EmailDTO emailDto) {

		LOGGER.info("Request to send email accepted. {}", emailDto);
		producer.publishMessage(emailDto);
		LOGGER.info("Email request queued successfully.");
		return ResponseEntity.accepted().body(new ResponseDTO(EmailApplicationConstants.STATUS_SUCCESS,
				EmailApplicationConstants.EMAIL_REQUEST_ACCEPTED_MESSAGE));

	}
}
