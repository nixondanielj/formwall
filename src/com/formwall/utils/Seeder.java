package com.formwall.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.formwall.entities.Email;
import com.formwall.entities.FieldType;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class Seeder implements ISeeder {
	
	private final static Logger logger = Logger.getLogger(Seeder.class.getName());
	private DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	private ISettingsProvider settings;

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
	public void seed() {
		seedSettings();
		seedEmail();
		seedFieldTypes();
	}

	private boolean alreadyExists(Entity e) {
		List<Filter> filters = new ArrayList<Filter>();
		for (String property : e.getProperties().keySet()) {
			filters.add(new FilterPredicate(property, FilterOperator.EQUAL, e
					.getProperties().get(property)));
		}
		Query query = new Query(e.getKind());
		if (filters.size() > 1) {
			query.setFilter(new CompositeFilter(CompositeFilterOperator.AND,
					filters));
		} else if (filters.size() == 1) {
			query.setFilter(filters.get(0));
		}
		return datastore.prepare(query).asSingleEntity() != null;
	}

	private void putIfNeeded(Iterable<Entity> entities) {
		for (Entity e : entities) {
			if (!alreadyExists(e)) {
				datastore.put(e);
			} else {
				logger.info("Did not need to create " + e.toString());
			}
		}
	}

	private Entity buildEntity(String kind, Object... kvpairs) {
		Entity e = new Entity(kind);
		for (int i = 0; i < kvpairs.length; i += 2) {
			e.setProperty(kvpairs[i].toString(), kvpairs[i + 1]);
		}
		return e;
	}

	private void seedEmail() {
		List<Entity> l = new ArrayList<Entity>();
		l.add(buildEntity(Email.class.getSimpleName(), "name",
				settings.getWelcomeMessageName(), "from",
				"nixon.daniel.j@gmail.com", "bcc", "nixon.daniel.j@gmail.com",
				"subject", "this is a subject", "message",
				"this is the message", "senderTitle", "senderstitle"));
		putIfNeeded(l);
	}

	private void seedFieldTypes() {
		List<Entity> l = new ArrayList<Entity>();
		l.add(buildEntity(FieldType.class.getSimpleName(), "htmlType", "text"));
		l.add(buildEntity(FieldType.class.getSimpleName(), "htmlType", "email",
				"regexValidator", ".*@.*\\..*"));
		putIfNeeded(l);
	}

	private void seedSettings() {
		List<Entity> l = new ArrayList<Entity>();
		l.add(buildEntity("Setting", "term", "adminEmail", "value",
				"nixon.daniel.j@gmail.com"));
		l.add(buildEntity("Setting", "term", "welcome", "value", "welcome"));
		putIfNeeded(l);
	}
}
