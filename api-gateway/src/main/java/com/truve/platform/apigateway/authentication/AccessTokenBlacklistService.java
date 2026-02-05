package com.truve.platform.apigateway.authentication;

import javax.crypto.SecretKey;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccessTokenBlacklistService {
	private final StringRedisTemplate redisTemplate;

	private static final String PREFIX = "BL:AT";

	private String key(String jti) {
		return PREFIX + jti;
	}

	public boolean isExist(String jti) {
		return redisTemplate.hasKey(key(jti));
	}

}
