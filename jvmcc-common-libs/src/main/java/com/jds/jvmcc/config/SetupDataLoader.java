package com.jds.jvmcc.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jds.jvmcc.entity.Role;
import com.jds.jvmcc.entity.UserEntity;
import com.jds.jvmcc.repository.RoleRepository;
import com.jds.jvmcc.repository.UserRepository;


/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-07
 */
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		if (alreadySetup) {
			return;
		}

		// Create user roles
		var userRole = createRoleIfNotFound(Role.ROLE_USER);
		var adminRole = createRoleIfNotFound(Role.ROLE_ADMIN);

		// Create users
		createUserIfNotFound("user", userRole);
		createUserIfNotFound("jvmcc", adminRole);

		alreadySetup = true;
	}

	@Transactional
	private final Role createRoleIfNotFound(final String name) {
		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
			role = roleRepository.save(role);
		}
		return role;
	}

	@Transactional
	private final UserEntity createUserIfNotFound(final String name, final Role role) {
		UserEntity user = userRepository.findByUsername(name);
		if (user == null) {
			user = new UserEntity(name, "jvmcc");
			user.setRoles(Set.of(role));
			user = userRepository.save(user);
		}
		return user;
	}
}