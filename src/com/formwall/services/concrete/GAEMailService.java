package com.formwall.services.concrete;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Logger;

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
import com.formwall.entities.CustomUser;
import com.formwall.repositories.IEmailRepository;
import com.formwall.services.IMailService;
import com.formwall.utils.ISettingsProvider;
import com.formwall.utils.Seeder;

public class GAEMailService implements IMailService {
	private final static Logger logger = Logger.getLogger(GAEMailService.class.getName());
	Properties properties = new Properties();
	Session session = Session.getDefaultInstance(properties);
	private ISettingsProvider settings;
	private IEmailRepository emailRepo;

	@Inject
	public GAEMailService(ISettingsProvider settings, IEmailRepository emailRepo) {
		this.settings = settings;
		this.emailRepo = emailRepo;
	}

	@Override
	public void sendWelcomeEmail(CustomUser user)
			throws UnsupportedEncodingException, MessagingException {
		Email email = emailRepo.getByName(settings.getWelcomeMessageName());
		if (email.getTo() != null) {
			email.setTo(String.format("%s%s;", email.getTo(), user.getEmail()));
		} else {
			email.setTo(user.getEmail());
		}
		email.setMessage(email.getMessage().replaceAll("__password__",
				user.getPassword()));
		sendEmail(email);
	}

	private void sendEmail(Email email) throws MessagingException,
			UnsupportedEncodingException, AddressException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(email.getFrom(), email
				.getSenderTitle()));
		addRecipients(message, email.getTo(), Message.RecipientType.TO);
		addRecipients(message, email.getCc(), Message.RecipientType.CC);
		addRecipients(message, email.getBcc(), Message.RecipientType.BCC);
		message.setSubject(email.getSubject());
		message.setText(email.getMessage());
		Transport.send(message);
		logger.info(String.format("Body Text of email to %s: %s", email.getTo(), email.getMessage()));
	}

	private void addRecipients(Message message, String recipients,
			RecipientType type) throws AddressException, MessagingException {
		if (recipients != null) {
			for (String recipient : recipients.split(";")) {
				message.addRecipient(type, new InternetAddress(recipient));
			}
		}
	}

}
