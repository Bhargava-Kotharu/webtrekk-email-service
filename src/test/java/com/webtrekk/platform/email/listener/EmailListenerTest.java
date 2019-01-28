package com.webtrekk.platform.email.listener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.webtrekk.platform.email.EmailApplicationTestConfiguration;
import com.webtrekk.platform.email.dto.EmailDTO;
import com.webtrekk.platform.email.exception.BadRequestException;
import com.webtrekk.platform.email.service.EmailService;

/**
 * Test class for {@link EmailListener}
 * 
 * @author bkotharu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EmailListener.class })
@ContextConfiguration(classes = EmailApplicationTestConfiguration.class)
public class EmailListenerTest {

	@Autowired
	private EmailListener listener;

	@MockBean
	private EmailService emailService;

	@Test
	public void testOnMessage() throws BadRequestException {
		// Given
		EmailDTO emailDto = new EmailDTO();
		emailDto.setBody("Mail Body");
		emailDto.setSubject("Mail Subject");
		emailDto.setToAddress("bhargava.kotharu@gmail.com");

		// When
		listener.onMessage(emailDto);

	}

}
