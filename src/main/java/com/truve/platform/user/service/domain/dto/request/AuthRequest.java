package com.truve.platform.user.service.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuthRequest {

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class SignUp {
		private String email;
		private String password;
	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Login {
		private String email;
		private String password;
	}
}
