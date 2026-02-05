package com.truve.platform.payment.service.domain.constant;

public enum PaymentStatus {
	READY,
	WAITING_FOR_DEPOSIT,
	EXPIRED,
	DONE,
	PARTIAL_REFUNDED,
	REFUNDED,
	FAILED,
	CANCELED
}
