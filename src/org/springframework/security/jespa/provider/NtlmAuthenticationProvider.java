/**
 * NtlmAuthenticationProvider
 * 
 * Project: jespa-spring
 * Licence: Apache License, Version 2.0
 * @author Tomek Kuprowski
 */
package org.springframework.security.jespa.provider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.jespa.authentication.NtlmAuthenticationToken;

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
