package com.jds.jvmcc.reviewcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jds.jvmcc.reviewcrud.entity.Role;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-07
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}