/**
 * NtlmAuthenticationProvider
 * 
 * Project: jespa-spring2
 * Licence: Apache License, Version 2.0
 * @author Tomek Kuprowski
 * Changes:
 * Lydon Chandra 18-October-2010 Springify to spring-security-2.0.x 
 */
package org.springframework.security.jespa.provider;

import org.springframework.security.AuthenticationException;
import org.springframework.security.jespa.authentication.NtlmAuthenticationToken;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.providers.dao.DaoAuthenticationProvider;
import org.springframework.security.userdetails.UserDetails;

/**
 * Authentication provider which role is to skip check of password
 */
public class NtlmAuthenticationProvider extends DaoAuthenticationProvider {

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.dao.DaoAuthenticationProvider#additionalAuthenticationChecks(org.springframework.security.core.userdetails.UserDetails, org.springframework.security.authentication.UsernamePasswordAuthenticationToken)
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if (!(authentication instanceof NtlmAuthenticationToken)) {
			super.additionalAuthenticationChecks(userDetails, authentication);
		}
	}
}
