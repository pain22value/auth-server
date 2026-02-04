package com.truve.platform.user.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class EmailRequest {

	@Getter
	@AllArgsConstructor
	public static class SendVerificationCode {
		String email;
	}

	@Getter
	@AllArgsConstructor
	public static class VerifyCode {
		String email;
		String code;

	}
}
