package com.truve.platform.payment.service.domain.constant;

import java.util.EnumSet;
import java.util.Set;

public enum PaymentStatus {
	READY,
	WAITING_FOR_DEPOSIT,
	EXPIRED,
	DONE,
	PARTIAL_REFUNDED,
	REFUNDED,
	FAILED,
	CANCELED;

	private static final Set<PaymentStatus> CANCELABLE_STATUS = EnumSet.of(READY, WAITING_FOR_DEPOSIT);
	private static final Set<PaymentStatus> REFUNDABLE_STATUS = EnumSet.of(DONE, PARTIAL_REFUNDED);

	public boolean isCancelable() {
		return CANCELABLE_STATUS.contains(this);
	}

	public boolean isRefundable() {
		return REFUNDABLE_STATUS.contains(this);
	}
}
