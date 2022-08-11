package com.jds.jvmcc.reviewservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jds.jvmcc.reviewservice.entity.UserEntity;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-07
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByUsername(String userName);
}