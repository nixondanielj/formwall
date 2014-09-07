package com.formwall.utils;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.formwall.entities.Email;
import com.formwall.entities.FieldType;
import com.formwall.entities.Setting;

public class Seeder implements ISeeder {

	private final static Logger logger = Logger.getLogger(Seeder.class
			.getName());
	private ISettingsProvider settings;
	private List<Object> list;

	@Inject
	public Seeder(ISettingsProvider settings) {
		this.settings = settings;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.formwall.utils.ISeeder#seed()
	 */
	@Override
	public void seed(boolean clear) {
		if (clear) {
			deleteAll();
		}
		list = new ArrayList<Object>();
		seedSettings();
		// save settings as they may be dependencies for other seed data
		ofy().save().entities(list).now();
		list.clear();
		seedEmail();
		seedFieldTypes();
		logger.info("Seeding data...");
		ofy().save().entities(list).now();
	}

	private void deleteAll() {
		if (settings.getWelcomeMessageName() != null) {
			ofy().delete().entities(
					ofy().load().type(Email.class)
							.ids(settings.getWelcomeMessageName()).values()).now();
		}
		ofy().delete().entities(ofy().load().type(Setting.class).list()).now();
		ofy().delete().entities(ofy().load().type(FieldType.class).list()).now();
	}

	private void seedEmail() {
		Email email = new Email();
		email.setFrom(settings.getAdminEmail());
		email.setMessage("this is the welcome message, password is __password__");
		email.setSubject("this is the subject");
		email.setId(settings.getWelcomeMessageName());
		email.setSenderTitle("senders title");
		email.setTo(settings.getAdminEmail());
		list.add(email);
	}

	private void seedFieldTypes() {
		FieldType f = new FieldType();
		f.setHtmlType("text");
		list.add(f);
		f = new FieldType();
		f.setHtmlType("email");
		f.setDefaultRegexValidator(".*@.*\\..*");
		list.add(f);
	}

	private void seedSettings() {
		Setting s = new Setting();
		s.setId("adminEmail");
		s.setValue("nixon.daniel.j@gmail.com");
		list.add(s);
		s = new Setting();
		s.setId("welcome");
		s.setValue("welcome");
		list.add(s);
	}
}
