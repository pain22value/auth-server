package com.truve.platform.user.service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truve.platform.user.service.common.response.ApiResult;
import com.truve.platform.user.service.domain.dto.EmailRequest;
import com.truve.platform.user.service.service.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class EmailController {
	private final EmailService emailService;

	@PostMapping
	public ApiResult<Void> sendMail(
		@RequestBody EmailRequest.SendVerificationCode request
	) {
		emailService.sendMail(request.getEmail());
		return ApiResult.ok();
	}

	@PostMapping("/verify")
	public ApiResult<Void> verifyEmail(
		@RequestBody EmailRequest.VerifyCode request
	) {
		emailService.verifyEmail(request.getEmail(), request.getCode());
		return ApiResult.ok();
	}

}
