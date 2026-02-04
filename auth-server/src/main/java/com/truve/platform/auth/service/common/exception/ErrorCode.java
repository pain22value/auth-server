package com.truve.platform.auth.service.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorCode {

	private final HttpStatus status;
	private final String message;

}
