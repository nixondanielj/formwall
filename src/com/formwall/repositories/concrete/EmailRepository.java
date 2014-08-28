package com.formwall.repositories.concrete;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.formwall.entities.Email;
import com.formwall.repositories.BaseRepository;
import com.formwall.repositories.IEmailRepository;

public class EmailRepository extends BaseRepository implements IEmailRepository {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.formwall.repositories.concrete.IEmailRepository#getByName(java.lang
	 * .String)
	 */
	@Override
	public Email getByName(String name){
		return ofy().load().type(Email.class).filter("name", name).first().now();
	}

}
