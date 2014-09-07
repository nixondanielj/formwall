package com.formwall.repositories.concrete;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.formwall.entities.Setting;
import com.formwall.repositories.ISettingRepository;

public class SettingRepository implements ISettingRepository {
	/* (non-Javadoc)
	 * @see com.formwall.repositories.concrete.ISettingRepository#get(java.lang.String)
	 */
	@Override
	public Setting get(String key){
		return ofy().load().type(Setting.class).id(key).now();
	}
	
	/* (non-Javadoc)
	 * @see com.formwall.repositories.concrete.ISettingRepository#persist(com.formwall.entities.Setting)
	 */
	@Override
	public void persist(Setting setting){
		ofy().save().entity(setting);
	}
}
