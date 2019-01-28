package com.webtrekk.platform.email.helper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.webtrekk.platform.email.EmailApplicationTestConfiguration;
import com.webtrekk.platform.email.dto.EmailDTO;

/**
 * Test class for {@link SendEmailHelperImpl}
 * 
 * @author bkotharu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SendEmailHelperImpl.class, JavaMailSenderImpl.class })
@ContextConfiguration(classes = EmailApplicationTestConfiguration.class)
public class SendEmailHelperImplTest {

	@Autowired
	private SendEmailHelperImpl sendEmailImpl;

	@MockBean
	private JavaMailSenderImpl mailSender;

	@MockBean
	private MimeMessage mimeMessage;

	/**
	 * Email with attachment
	 */
	@Test
	public void testSendEmailWithoutAttachment() {
		EmailDTO emailDto = new EmailDTO();
		emailDto.setBody("Mail Body");
		emailDto.setSubject("Mail Subject");
		emailDto.setToAddress("bhargava.kotharu@gmail.com");
		doNothing().when(mailSender).send(any(SimpleMailMessage.class));
		sendEmailImpl.sendEmailWithoutAttachment(emailDto);

	}

	/**
	 * Email with attachment
	 * 
	 * @throws IOException
	 * @throws MessagingException
	 */
	@Test
	public void testSendEmailWithAttachment() throws MessagingException, IOException {
		EmailDTO emailDto = new EmailDTO();
		emailDto.setBody("Mail Body");
		emailDto.setSubject("Mail Subject");
		emailDto.setToAddress("bhargava.kotharu@gmail.com");
		emailDto.setAttachmentURI("http://www.africau.edu/images/default/sample.pdf");
		when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
		doNothing().when(mailSender).send(any(MimeMessage.class));
		sendEmailImpl.sendEmailWithAttachment(emailDto);
	}
}
