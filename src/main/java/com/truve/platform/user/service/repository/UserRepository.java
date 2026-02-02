package com.truve.platform.user.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truve.platform.user.service.common.exception.CustomException;
import com.truve.platform.user.service.common.exception.ErrorCode;
import com.truve.platform.user.service.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

	default User findByEmailOrThrow(String email) {
		return findByEmail(email).orElseThrow(
			() -> new CustomException(ErrorCode.ALREADY_EXISTS_EMAIL)
		);
	}
}
