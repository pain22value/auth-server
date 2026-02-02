package com.truve.platform.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truve.platform.user.service.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
