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
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class Seeder implements ISeeder {

	private final static Logger logger = Logger.getLogger(Seeder.class
			.getName());
	private DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	private ISettingsProvider settings;
	private List<Entity> list;

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
		list = new ArrayList<Entity>();
		seedSettings();
		seedEmail();
		seedFieldTypes();
		logger.info("Seeding data...");
		putIfNeeded(list);
	}

	private void deleteAll() {
		logger.info("Clearing seed data...");
		List<Key> keys = new ArrayList<Key>();
		Query query = new Query("Setting");
		keys.addAll(selectKeys(query));
		query = new Query(FieldType.class.getSimpleName());
		keys.addAll(selectKeys(query));
		query = new Query(Email.class.getSimpleName());
		keys.addAll(selectKeys(query));
		logger.info(String.format("Clearing %s items", keys.size()));
		datastore.delete(keys);
	}

	private List<Key> selectKeys(Query query) {
		List<Key> keys = new ArrayList<Key>();
		for (Entity e : datastore.prepare(query).asIterable()) {
			keys.add(e.getKey());
		}
		return keys;
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
		list.add(buildEntity(Email.class.getSimpleName(), "name",
				settings.getWelcomeMessageName(), "from",
				"nixon.daniel.j@gmail.com", "bcc", "nixon.daniel.j@gmail.com",
				"subject", "this is a subject", "message",
				"this is the welcome message, password is __password__",
				"senderTitle", "senderstitle"));
	}

	private void seedFieldTypes() {
		list.add(buildEntity(FieldType.class.getSimpleName(), "htmlType",
				"text"));
		list.add(buildEntity(FieldType.class.getSimpleName(), "htmlType",
				"email", "regexValidator", ".*@.*\\..*"));
	}

	private void seedSettings() {
		list.add(buildEntity("Setting", "term", "adminEmail", "value",
				"nixon.daniel.j@gmail.com"));
		list.add(buildEntity("Setting", "term", "welcome", "value", "welcome"));
	}
}
