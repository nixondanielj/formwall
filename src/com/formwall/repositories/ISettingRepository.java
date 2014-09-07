package com.formwall.repositories;

import com.formwall.entities.Setting;

public interface ISettingRepository {

	public abstract Setting get(String key);

	public abstract void persist(Setting setting);

}