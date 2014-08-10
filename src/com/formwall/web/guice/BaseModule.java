package com.formwall.web.guice;

import com.formwall.repositories.IEmailRepository;
import com.formwall.repositories.IFieldTypeRepository;
import com.formwall.repositories.ISessionRepository;
import com.formwall.repositories.IUserRepository;
import com.formwall.repositories.concrete.EmailRepository;
import com.formwall.repositories.concrete.FieldTypeRepository;
import com.formwall.repositories.concrete.SessionRepository;
import com.formwall.repositories.concrete.UserRepository;
import com.formwall.utils.DSSettingsProvider;
import com.formwall.utils.IMailer;
import com.formwall.utils.ISeeder;
import com.formwall.utils.ISettingsProvider;
import com.formwall.utils.Mailer;
import com.formwall.utils.Seeder;
import com.google.inject.AbstractModule;

public class BaseModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ISettingsProvider.class).to(DSSettingsProvider.class);
		bind(IMailer.class).to(Mailer.class);
		bind(IFieldTypeRepository.class).to(FieldTypeRepository.class);
		bind(IUserRepository.class).to(UserRepository.class);
		bind(ISessionRepository.class).to(SessionRepository.class);
		bind(IEmailRepository.class).to(EmailRepository.class);
		bind(ISeeder.class).to(Seeder.class);
	}
	
}