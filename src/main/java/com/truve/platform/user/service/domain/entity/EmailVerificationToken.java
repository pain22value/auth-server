package com.truve.platform.user.service.domain.entity;

import com.truve.platform.user.service.common.support.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// TODO: DB -> 레디스로 전환

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailVerificationToken extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String email;
	private String token;
}
