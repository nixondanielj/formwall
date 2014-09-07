package com.formwall.utils;

import com.formwall.entities.Setting;
import com.formwall.repositories.ISettingRepository;
import com.google.inject.Inject;

public class DSSettingsProvider implements ISettingsProvider{
	ISettingRepository settingRepo;
	
	
	@Inject
	public DSSettingsProvider(ISettingRepository settingRepo) {
		this.settingRepo = settingRepo;
	}

	@Override
	public String getAdminEmail() {
		return get("adminEmail");
	}
	@Override
	public String getWelcomeMessageName() {
		return get("welcome");
	}
	private String get(String key){
		Setting setting = settingRepo.get(key);
		if(setting != null){
			return setting.getValue();
		} 
		return null;
	}
	
}
