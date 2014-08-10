package com.formwall.utils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.formwall.entities.Email;
import com.formwall.entities.User;
import com.formwall.repositories.IEmailRepository;

public class Mailer implements IMailer {
	Properties properties = new Properties();
	Session session = Session.getDefaultInstance(properties);
	private ISettingsProvider settings;
	private IEmailRepository emailRepo;
	
	@Inject
	public Mailer(ISettingsProvider settings, IEmailRepository emailRepo){
		this.settings = settings;
		this.emailRepo = emailRepo;
	}

	@Override
	public void sendWelcomeEmail(User user) throws UnsupportedEncodingException, MessagingException {
		Email email = emailRepo.getByName(settings.getWelcomeMessageName());
		email.setTo(String.format("%s%s;", email.getTo(), user.getEmail()));
		sendEmail(email);
	}

	private void sendEmail(Email email) throws MessagingException,
			UnsupportedEncodingException, AddressException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(email.getFrom(), email.getSenderTitle()));
		addRecipients(message, email.getTo(), Message.RecipientType.TO);
		addRecipients(message, email.getCc(), Message.RecipientType.CC);
		addRecipients(message, email.getBcc(), Message.RecipientType.BCC);
		message.setSubject(email.getSubject());
		message.setText(email.getMessage());
		Transport.send(message);
	}

	private void addRecipients(Message message, String recipients, RecipientType type) 
			throws AddressException, MessagingException {
		for(String recipient : recipients.split(";")){
			message.addRecipient(type, new InternetAddress(recipient));
		}
	}
	
}
