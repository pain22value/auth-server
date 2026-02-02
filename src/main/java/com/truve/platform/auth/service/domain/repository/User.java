package com.truve.platform.auth.service.domain.repository;

import com.truve.platform.auth.service.common.constants.AuthProvider;
import com.truve.platform.auth.service.common.constants.UserRole;
import com.truve.platform.auth.service.common.support.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String email;
	private String password;

	private boolean verified;

	@Enumerated(EnumType.STRING)
	private AuthProvider provider;

	@Enumerated(EnumType.STRING)
	private UserRole role;
}
