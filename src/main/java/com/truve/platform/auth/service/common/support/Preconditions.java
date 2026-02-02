package com.truve.platform.auth.service.common.support;

import com.truve.platform.auth.service.common.exception.CustomException;
import com.truve.platform.auth.service.common.exception.ErrorCode;

public class Preconditions {

	public static void validate(boolean expression, ErrorCode errorCode) {
		if (!expression) {
			throw new CustomException(errorCode);
		}
	}
}
