package com.jds.jvmcc.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-26
 * 
 * In Spring Security there is a distinction between a role and an authority. 
 * A role is an authority that is prefixed with "ROLE_". 
 * as an example the authority "ROLE_ADMIN" is the same as the role "ADMIN".
 */
public class RealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        final Map<String, List<String>> realmAccess = (Map<String, List<String>>) jwt.getClaims().get("realm_access");
        return realmAccess.get("roles")
                .stream()
                .map(roleName -> "ROLE_" + roleName) // Prefix required by Spring Security for roles.
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}