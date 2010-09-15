/**
 * NtlmSecurityFilter
 * 
 * Project: jespa-spring
 * Licence: Apache License, Version 2.0
 * @author Tomek Kuprowski
 */
package org.springframework.security.jespa.filter;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jespa.http.HttpSecurityServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jespa.authentication.NtlmAuthenticationToken;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Basic authentication filter
 */
public class NtlmSecurityFilter extends GenericFilterBean {

	/** The authentication details source. */
	private AuthenticationDetailsSource authenticationDetailsSource;

	/** The authentication entry point. */
	private AuthenticationEntryPoint authenticationEntryPoint;

	/** The authentication manager. */
	private AuthenticationManager authenticationManager;

	/* (non-Javadoc)
	 * @see org.springframework.web.filter.GenericFilterBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() {
		Assert.notNull(authenticationManager, "An AuthenticationManager is required");
	}

	/**
	 * Checks if authentication is required for current request
	 *
	 * @param principal the principal
	 * @return true, if authentication is required
	 */
	private boolean authenticationIsRequired(Principal principal) {
		Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();

		if (existingAuth == null || !existingAuth.isAuthenticated() || principal == null) {
			return true;
		} else if (existingAuth instanceof NtlmAuthenticationToken
				&& existingAuth.getName().equals(principal.getName())) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		final boolean debug = logger.isDebugEnabled();
		final HttpServletResponse httpResponse = (HttpServletResponse) response;

		if (request instanceof HttpSecurityServletRequest) {
			final HttpSecurityServletRequest securityRequest = (HttpSecurityServletRequest) request;
			Principal principal = securityRequest.getUserPrincipal();

			if (authenticationIsRequired(principal)) {
				NtlmAuthenticationToken authenticationToken = new NtlmAuthenticationToken(principal);
				if (authenticationDetailsSource != null) {
					authenticationToken.setDetails(authenticationDetailsSource.buildDetails(securityRequest));
				}

				Authentication authResult;
				try {
					authResult = authenticationManager.authenticate(authenticationToken);
				} catch (AuthenticationException failed) {
					if (debug) {
						logger.debug("Authentication request for NTLM principal: "
								+ authenticationToken.getPrincipal() + " failed: " + failed.toString());
					}
					SecurityContextHolder.getContext().setAuthentication(null);
					onUnsuccessfulAuthentication((HttpServletRequest) request, httpResponse, failed);
					authenticationEntryPoint.commence(securityRequest, httpResponse, failed);
					return;
				}

				// Authentication success
				if (debug) {
					logger.debug("Authentication success: " + authResult.toString());
				}
				SecurityContextHolder.getContext().setAuthentication(authResult);
				onSuccessfulAuthentication((HttpServletRequest) request, httpResponse, authResult);
			}
		}

		chain.doFilter(request, response);
	}

	/**
	 * Gets the authentication entry point.
	 *
	 * @return the authentication entry point
	 */
	protected AuthenticationEntryPoint getAuthenticationEntryPoint() {
		return authenticationEntryPoint;
	}

	/**
	 * Gets the authentication manager.
	 *
	 * @return the authentication manager
	 */
	protected AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	/**
	 * On successful authentication.
	 *
	 * @param request the request
	 * @param response the response
	 * @param authResult the auth result
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			Authentication authResult) throws IOException {
	}

	/**
	 * On unsuccessful authentication.
	 *
	 * @param request the request
	 * @param response the response
	 * @param failed the failed
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException {
	}

	/**
	 * Sets the authentication details source.
	 *
	 * @param authenticationDetailsSource the new authentication details source
	 */
	public void setAuthenticationDetailsSource(AuthenticationDetailsSource authenticationDetailsSource) {
		this.authenticationDetailsSource = authenticationDetailsSource;
	}

	/**
	 * Sets the authentication entry point.
	 *
	 * @param authenticationEntryPoint the new authentication entry point
	 */
	public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
		this.authenticationEntryPoint = authenticationEntryPoint;
	}

	/**
	 * Sets the authentication manager.
	 *
	 * @param authenticationManager the new authentication manager
	 */
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
}
