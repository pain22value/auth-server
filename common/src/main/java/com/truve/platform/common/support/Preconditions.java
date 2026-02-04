package com.truve.platform.common.support;

import com.truve.platform.common.exception.CustomException;
import com.truve.platform.common.exception.ErrorCode;

public class Preconditions {

	public static void validate(boolean expression, ErrorCode errorCode) {
		if (!expression) {
			throw new CustomException(errorCode);
		}
	}
}
