package com.truve.platform.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truve.platform.user.service.domain.entity.EmailVerificationToken;

public interface EmailVerificationRepository extends JpaRepository<EmailVerificationToken, Long> {
}
