package com.webtrekk.platform.email.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.webtrekk.platform.email.EmailApplicationTestConfiguration;
import com.webtrekk.platform.email.constants.EmailApplicationConstants;
import com.webtrekk.platform.email.dto.EmailDTO;
import com.webtrekk.platform.email.dto.ResponseDTO;
import com.webtrekk.platform.email.publisher.EmailPublisher;

/**
 * Test class for {@link AsynchronousEmailController}
 * 
 * @author bkotharu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { AsynchronousEmailController.class })
@ContextConfiguration(classes = EmailApplicationTestConfiguration.class)
@DirtiesContext
// @EmbeddedKafka
public class AsynchronousEmailControllerTest {

	@Autowired
	private AsynchronousEmailController controller;

	@MockBean
	private EmailPublisher emailPublisher;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	/**
	 * Positive Test
	 */
	@Test
	public void testSendEmail() {
		// Given
		EmailDTO emailDto = new EmailDTO();
		emailDto.setBody("Mail Body");
		emailDto.setSubject("Mail Subject");
		emailDto.setToAddress("bhargava.kotharu@gmail.com");
		// When
		ResponseEntity<ResponseDTO> response = controller.sendEmail(emailDto);
		// Assert
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(EmailApplicationConstants.STATUS_SUCCESS, response.getBody().getStatus());
	}

	/**
	 * Negative Test - With invalid email address
	 * 
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	public void testSendEmailWithInvalidEmail() throws JsonProcessingException, Exception {
		// Given
		EmailDTO emailDto = new EmailDTO();
		emailDto.setBody("Mail Body");
		emailDto.setSubject("Mail Subject");
		emailDto.setToAddress("bhargava.kotharumail.com");

		// When
		expectedEx.expect(javax.validation.ConstraintViolationException.class);
		expectedEx.expectMessage("Recepient email address provided is in invalid email format.");
		ResponseEntity<ResponseDTO> response = controller.sendEmail(emailDto);
		// Assert
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(EmailApplicationConstants.STATUS_SUCCESS, response.getBody().getStatus());
	}

	/**
	 * Negative Test - With null email address
	 * 
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	public void testSendEmailWithEmptyEmail() throws JsonProcessingException, Exception {
		// Given
		EmailDTO emailDto = new EmailDTO();
		emailDto.setBody("Mail Body");
		emailDto.setSubject("Mail Subject");

		// When
		expectedEx.expect(javax.validation.ConstraintViolationException.class);
		expectedEx.expectMessage("sendEmail.emailDto.toAddress: Recepient email address is required");
		ResponseEntity<ResponseDTO> response = controller.sendEmail(emailDto);
		// Assert
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(EmailApplicationConstants.STATUS_SUCCESS, response.getBody().getStatus());
	}
}
