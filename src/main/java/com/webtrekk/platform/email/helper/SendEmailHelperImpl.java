package com.webtrekk.platform.email.helper;

import java.io.IOException;
import java.net.URL;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.webtrekk.platform.email.dto.EmailDTO;

@Component
public class SendEmailHelperImpl implements SendEmailHelper {

	@Autowired
	private JavaMailSender mailSender;

	/**
	 * Sends email without attachment
	 * 
	 * @param {@link
	 * 			EmailDTO}
	 */
	@Override
	public void sendEmailWithoutAttachment(EmailDTO emailDto) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(emailDto.getToAddress());
		message.setSubject(emailDto.getSubject());
		message.setText(emailDto.getBody());
		mailSender.send(message);
	}

	/**
	 * Sends email with attachment. Attachment will be fetched from the
	 * attachmentURI provided.
	 * 
	 * @param {@link
	 * 			EmailDTO}
	 * @throws MessagingException
	 * @throws IOException
	 */
	@Override
	public void sendEmailWithAttachment(EmailDTO emailDto) throws MessagingException, IOException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setTo(emailDto.getToAddress());
		mimeMessageHelper.setSubject(emailDto.getSubject());
		mimeMessageHelper.setText(emailDto.getBody());

		URL url = new URL(emailDto.getAttachmentURI());
		ByteArrayDataSource dataSource = new ByteArrayDataSource(url.openStream(), "application/octet-stream");
		mimeMessageHelper.addAttachment(!dataSource.getName().isEmpty() ? dataSource.getName() : "Attachment",
				dataSource);

		mailSender.send(mimeMessage);

	}
}
