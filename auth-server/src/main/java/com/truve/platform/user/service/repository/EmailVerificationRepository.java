package com.truve.platform.user.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truve.platform.common.exception.CustomException;
import com.truve.platform.common.exception.ErrorCode;
import com.truve.platform.user.service.domain.entity.EmailVerificationToken;

public interface EmailVerificationRepository extends JpaRepository<EmailVerificationToken, Long> {

	boolean existsByEmail(String email);

	Optional<EmailVerificationToken> findByEmail(String email);

	boolean existsByEmailAndIsVerifiedTrue(String email);

	default EmailVerificationToken findByEmailOrThrow(String email) {
		return findByEmail(email).orElseThrow(
			() -> new CustomException(ErrorCode.NOT_FOUND_EMAIL)
		);
	}

	void deleteByEmail(String email);
}
