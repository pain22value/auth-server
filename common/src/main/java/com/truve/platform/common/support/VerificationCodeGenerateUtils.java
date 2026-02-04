package com.truve.platform.common.support;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class VerificationCodeGenerateUtils {
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int CODE_LENGTH = 6;
	private static final SecureRandom RANDOM = new SecureRandom();

	public String generateVerificationCode() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < CODE_LENGTH; i++) {
			sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
		}

		return sb.toString();
	}
}
