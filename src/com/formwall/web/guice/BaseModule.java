package com.formwall.web.guice;

import javax.inject.Singleton;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Email;
import com.formwall.entities.Field;
import com.formwall.entities.FieldType;
import com.formwall.entities.Form;
import com.formwall.entities.Permission;
import com.formwall.entities.Session;
import com.formwall.entities.Setting;
import com.formwall.repositories.IEmailRepository;
import com.formwall.repositories.IFieldRepository;
import com.formwall.repositories.IFieldTypeRepository;
import com.formwall.repositories.IFormRepository;
import com.formwall.repositories.IPermissionRepository;
import com.formwall.repositories.ISessionRepository;
import com.formwall.repositories.ISettingRepository;
import com.formwall.repositories.IUserRepository;
import com.formwall.repositories.concrete.EmailRepository;
import com.formwall.repositories.concrete.FieldRepository;
import com.formwall.repositories.concrete.FieldTypeRepository;
import com.formwall.repositories.concrete.FormRepository;
import com.formwall.repositories.concrete.PermissionRepository;
import com.formwall.repositories.concrete.SessionRepository;
import com.formwall.repositories.concrete.SettingRepository;
import com.formwall.repositories.concrete.UserRepository;
import com.formwall.services.IAuthService;
import com.formwall.services.ICustomUserService;
import com.formwall.services.IFormService;
import com.formwall.services.IMailService;
import com.formwall.services.ISessionService;
import com.formwall.services.concrete.CustomUserService;
import com.formwall.services.concrete.FormService;
import com.formwall.services.concrete.GAEMailService;
import com.formwall.services.concrete.SessionService;
import com.formwall.utils.DSSettingsProvider;
import com.formwall.utils.ISeeder;
import com.formwall.utils.ISettingsProvider;
import com.formwall.utils.Seeder;
import com.google.inject.AbstractModule;
import com.google.inject.servlet.RequestScoped;
import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;

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
		bind(IAuthService.class).toProvider(AuthSvcProvider.class).in(RequestScoped.class);
		bind(ISessionService.class).to(SessionService.class);
		bind(ISessionRepository.class).to(SessionRepository.class);
		bind(ObjectifyFilter.class).in(Singleton.class);
		bind(IPermissionRepository.class).to(PermissionRepository.class);
		bind(IFormService.class).to(FormService.class);
		bind(IFormRepository.class).to(FormRepository.class);
		bind(IFieldRepository.class).to(FieldRepository.class);
		bind(ISettingRepository.class).to(SettingRepository.class);
	}
	
	static{
		ObjectifyService.register(CustomUser.class);
		ObjectifyService.register(Email.class);
		ObjectifyService.register(Field.class);
		ObjectifyService.register(FieldType.class);
		ObjectifyService.register(Form.class);
		ObjectifyService.register(Permission.class);
		ObjectifyService.register(Session.class);
		ObjectifyService.register(Setting.class);
	}
	
}
