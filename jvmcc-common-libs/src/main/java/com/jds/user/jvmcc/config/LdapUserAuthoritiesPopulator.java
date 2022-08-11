package com.jds.jvmcc.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

import com.jds.jvmcc.entity.Role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-07
 * @see UserDetailsService
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class LdapUserAuthoritiesPopulator implements LdapAuthoritiesPopulator {

	private final UserDetailsService userDetailsService;

	@Override
	public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
		Collection<? extends GrantedAuthority> authorities = new HashSet<>();
		try {
			authorities = userDetailsService.loadUserByUsername(username).getAuthorities();
		} catch (Exception e) {
			log.warn("The user authorities could not be retrieved from the database. Consequently, defining a default user role.");
			authorities = Arrays.asList(new SimpleGrantedAuthority(Role.ROLE_USER));
		}
		return authorities;
	}
}
