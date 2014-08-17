package com.formwall.web.guice;

import com.formwall.repositories.IEmailRepository;
import com.formwall.repositories.IFieldTypeRepository;
import com.formwall.repositories.ISessionRepository;
import com.formwall.repositories.IUserRepository;
import com.formwall.repositories.concrete.EmailRepository;
import com.formwall.repositories.concrete.FieldTypeRepository;
import com.formwall.repositories.concrete.SessionRepository;
import com.formwall.repositories.concrete.UserRepository;
import com.formwall.services.IAuthService;
import com.formwall.services.ICustomUserService;
import com.formwall.services.IMailService;
import com.formwall.services.ISessionService;
import com.formwall.services.concrete.CustomAuthService;
import com.formwall.services.concrete.CustomUserService;
import com.formwall.services.concrete.GAEMailService;
import com.formwall.services.concrete.SessionService;
import com.formwall.utils.DSSettingsProvider;
import com.formwall.utils.ISeeder;
import com.formwall.utils.ISettingsProvider;
import com.formwall.utils.Seeder;
import com.google.inject.AbstractModule;

public class BaseModule extends AbstractModule {
	
	//TODO https://github.com/google/guice/wiki/CustomInjections
	// above is for injecting logger

	@Override
	protected void configure() {
		bind(ISettingsProvider.class).to(DSSettingsProvider.class);
		bind(IMailService.class).to(GAEMailService.class);
		bind(IFieldTypeRepository.class).to(FieldTypeRepository.class);
		bind(IUserRepository.class).to(UserRepository.class);
		bind(ISessionRepository.class).to(SessionRepository.class);
		bind(IEmailRepository.class).to(EmailRepository.class);
		bind(ISeeder.class).to(Seeder.class);
		bind(ICustomUserService.class).to(CustomUserService.class);
		bind(IAuthService.class).to(CustomAuthService.class);
		bind(ISessionService.class).to(SessionService.class);
		bind(ISessionRepository.class).to(SessionRepository.class);
	}
	
}
