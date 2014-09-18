package com.formwall.web;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.formwall.web.models.AuthenticationRequest;
import com.google.api.server.spi.auth.common.User;

public class AuthHelper {
	public static AuthenticationRequest getAuthReq(User user, ServletRequest req){
		AuthenticationRequest request = new AuthenticationRequest();
		if(user != null){
			request.email = user.getEmail();
		} else {
			request.authCode = new HttpServletRequestWrapper((HttpServletRequest) req).getHeader("Authorization");
			request.authCode = request.authCode.split(" ")[1];
		}
		return request;
	}
}
