package com.truve.platform.user.service.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	NOT_FOUND_USER(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
	ALREADY_EXISTS_EMAIL(HttpStatus.BAD_REQUEST, "중복된 이메일입니다."),
	NOT_FOUND_EMAIL(HttpStatus.BAD_REQUEST, "이메일 전송에 실패했습니다."),
	NOT_CORRECT_EMAIL_CODE(HttpStatus.BAD_REQUEST, "이메일 인증 코드가 다릅니다."),
	NOT_CORRECT_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다."),
	NOT_VERIFIED_EMAIL(HttpStatus.BAD_REQUEST, "인증되지 않은 이메일입니다."),
	ALREADY_VERIFIED_EMAIL(HttpStatus.BAD_REQUEST, "이미 인증된 이메일입니다."),
	INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "리프레시 토큰이 올바르지 않습니다."),

	;

	private final HttpStatus status;
	private final String message;

}
