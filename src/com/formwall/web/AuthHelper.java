package com.formwall.web;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.formwall.services.AuthenticationRequest;
import com.google.api.server.spi.auth.common.User;

public class AuthHelper {
	public static AuthenticationRequest getAuthReq(User user, ServletRequest req){
		AuthenticationRequest request = new AuthenticationRequest();
		if(user != null){
			request.email = user.getEmail();
		} else {
			request.authCode = new HttpServletRequestWrapper((HttpServletRequest) req).getHeader("Authorization");
		}
		return request;
	}
}
