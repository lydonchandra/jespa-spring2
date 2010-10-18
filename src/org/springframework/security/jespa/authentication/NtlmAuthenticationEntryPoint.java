/**
 * NtlmAuthenticationEntryPoint
 * Project: jespa-spring
 * Licence: Apache License, Version 2.0
 * @author Tomek Kuprowski
 * Changes:
 * Lydon Chandra	18-October-2010		Springify to spring-security-2.0.x
 */
package org.springframework.security.jespa.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Authentication entry point which just rejects access
 * 
 * @see org.springframework.security.web.AuthenticationEntryPoint
 */
public class NtlmAuthenticationEntryPoint implements org.springframework.security.ui.AuthenticationEntryPoint {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.AuthenticationEntryPoint#commence(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.AuthenticationException)
	 */
//	public void commence(HttpServletRequest request, HttpServletResponse response,
//			AuthenticationException authException) throws IOException, ServletException {
//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
//	}

	public void commence(ServletRequest request, ServletResponse response,
			org.springframework.security.AuthenticationException authException)
			throws IOException, ServletException {
		
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
	}

}
