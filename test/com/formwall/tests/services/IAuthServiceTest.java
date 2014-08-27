package com.formwall.tests.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.formwall.entities.CustomUser;
import com.formwall.entities.Session;
import com.formwall.repositories.IUserRepository;
import com.formwall.services.IAuthService;
import com.formwall.services.ISessionService;
import com.formwall.services.Roles;
import com.formwall.services.concrete.CustomAuthService;
import com.google.appengine.api.datastore.Key;

public class IAuthServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public IAuthService factory(ISessionService sessionSvc,
			IUserRepository userRepo) {
		if (sessionSvc == null) {
			sessionSvc = mock(ISessionService.class);
		}
		if (userRepo == null) {
			userRepo = mock(IUserRepository.class);
		}
		return new CustomAuthService(userRepo, sessionSvc);
	}

	@Test
	public void testIsAuthorizedWithSession() {
		Session session = mock(Session.class);
		Roles role = Roles.User;
		IAuthService authService = setupIsAuthorizedTest(true, role);
		assertTrue(authService.isAuthorized(session, role));
		authService = setupIsAuthorizedTest(true, null);
		assertFalse(authService.isAuthorized(session, role));
		authService = setupIsAuthorizedTest(false, role);
		assertFalse(authService.isAuthorized(session, role));
		authService = setupIsAuthorizedTest(false, null);
		assertFalse(authService.isAuthorized(session, role));
	}

	private IAuthService setupIsAuthorizedTest(boolean authenticated,
			Roles role) {
		ISessionService sessionSvc = mock(ISessionService.class);
		IUserRepository userRepo = mock(IUserRepository.class);
		CustomUser user = mock(CustomUser.class);
		List<String> roles = new ArrayList<String>();
		if(role != null){
			roles.add(role.name());
		}
		when(user.getRoles()).thenReturn(roles);
		when(sessionSvc.isValidSession(any(Session.class))).thenReturn(authenticated);
		when(userRepo.getById(any(String.class))).thenReturn(user);
		return factory(sessionSvc, userRepo);
	}

	@Test
	public void testAddRoleToUser() {
		IUserRepository userRepo = mock(IUserRepository.class);
		IAuthService authSvc = factory(null, userRepo);
		// verify each role sticks to a new user
		for (Roles role : Roles.values()) {
			CustomUser user = new CustomUser();
			authSvc.addRoleToUser(user, role);
			Assert.assertEquals(user.getRoles().get(0), role.name());
			verify(userRepo).persist(user);
		}
		// verify multiple roles stick to a user
		CustomUser user = new CustomUser();
		for (Roles role : Roles.values()) {
			authSvc.addRoleToUser(user, role);
			Assert.assertTrue(user.getRoles().contains(role.name()));
		}
		Assert.assertEquals(user.getRoles().size(), Roles.values().length);
		verify(userRepo, times(Roles.values().length)).persist(user);
	}

}
