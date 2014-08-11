package com.formwall.web;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class AuthHelper {
	public String getAuthValue(ServletRequest request){
		return new HttpServletRequestWrapper((HttpServletRequest) request).getHeader("Authorization");
	}
}
