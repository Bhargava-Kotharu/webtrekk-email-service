package com.webtrekk.platform.email.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.webtrekk.platform.email.EmailApplicationTestConfiguration;
import com.webtrekk.platform.email.dto.EmailDTO;
import com.webtrekk.platform.email.helper.SendEmailHelper;

/**
 * Test class for {@link EmailServiceImpl}
 * 
 * @author bkotharu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EmailServiceImpl.class })
@ContextConfiguration(classes = EmailApplicationTestConfiguration.class)
public class EmailServiceImplTest {

	@Autowired
	private EmailServiceImpl emailService;

	@MockBean
	private SendEmailHelper mailHelper;

	@MockBean
	private JavaMailSender mailSender;

	/**
	 * Email with attachment Test
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSendEmailWithoutAttachment() throws Exception {
		EmailDTO emailDto = new EmailDTO();
		emailDto.setBody("Mail Body");
		emailDto.setSubject("Mail Subject");
		emailDto.setToAddress("bhargava.kotharu@gmail.com");
		emailService.sendEmail(emailDto);

		verify(mailHelper, times(1)).sendEmailWithoutAttachment(emailDto);
	}

	/**
	 * Email without attachment Test
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSendEmailWithAttachment() throws Exception {
		EmailDTO emailDto = new EmailDTO();
		emailDto.setBody("Mail Body");
		emailDto.setSubject("Mail Subject");
		emailDto.setToAddress("bhargava.kotharu@gmail.com");
		emailDto.setAttachmentURI("http://localhost.com");
		emailService.sendEmail(emailDto);

		verify(mailHelper, times(1)).sendEmailWithAttachment(emailDto);
	}
}
