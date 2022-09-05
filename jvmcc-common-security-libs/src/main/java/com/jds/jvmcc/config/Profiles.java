package com.jds.jvmcc.config;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-06
 */
public class Profiles {

	private Profiles() {
	}

	public static final String NO_AUTH = "noauth";
	public static final String BASIC_AUTH = "basicauth";
	public static final String JWT_AUTH = "jwtauth";
	public static final String LDAP_AUTH = "ldapauth";
	public static final String MULTI_AUTH = "multiauth";
}