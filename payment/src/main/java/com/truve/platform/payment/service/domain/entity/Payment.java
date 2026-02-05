package com.truve.platform.payment.service.domain.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.truve.platform.common.exception.ErrorCode;
import com.truve.platform.common.support.BaseEntity;
import com.truve.platform.common.support.Preconditions;
import com.truve.platform.payment.service.domain.constant.CancelType;
import com.truve.platform.payment.service.domain.constant.PaymentMethod;
import com.truve.platform.payment.service.domain.constant.PaymentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "payments")
public class Payment extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String orderId;

	@Column(unique = true)
	private String paymentKey;

	@Column(nullable = false)
	private Long amount;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PaymentMethod method;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PaymentStatus status;

	@Column(nullable = false)
	private Long cancelableAmount;

	private String failReason;

	@OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
	private List<PaymentCancel> cancels = new ArrayList<>();

	private LocalDateTime approvedAt;

	@Builder
	public Payment(String orderId, Long amount, PaymentMethod method) {
		this.orderId = orderId;
		this.amount = amount;
		this.cancelableAmount = amount;
		this.method = method;
		this.status = PaymentStatus.READY;
	}

	public void waitDeposit(String paymentKey) {
		validateWaitDepositStatus();

		this.paymentKey = paymentKey;
		this.status = PaymentStatus.WAITING_FOR_DEPOSIT;
	}

	public void expire() {
		validateExpireStatus();

		this.status = PaymentStatus.EXPIRED;
		this.cancelableAmount = 0L;
	}

	public void complete(String paymentKey) {
		validateCompleteStatus();
		verifyPaymentKey(paymentKey);

		this.paymentKey = paymentKey;
		this.status = PaymentStatus.DONE;
		this.approvedAt = LocalDateTime.now();
	}

	public void applyCancel(Long cancelAmount, String reason, CancelType type) {
		if (status == PaymentStatus.WAITING_FOR_DEPOSIT) {
			processWaitingCancel();
		} else {
			validateCancelStatus();
			validateCancelAmount(cancelAmount);
			processCancel(cancelAmount);
		}

		this.cancels.add(new PaymentCancel(this, cancelAmount, reason, type));
	}

	private void processWaitingCancel() {
		this.cancelableAmount = 0L;
		this.status = PaymentStatus.CANCELED;
	}

	private void processCancel(Long cancelAmount) {
		this.cancelableAmount -= cancelAmount;
		this.status = (this.cancelableAmount == 0) ? PaymentStatus.REFUNDED : PaymentStatus.PARTIAL_REFUNDED;
	}

	private void validateWaitDepositStatus() {
		Preconditions.validate(status == PaymentStatus.READY, ErrorCode.INVALID_PAYMENT_STATUS);
	}

	private void validateExpireStatus() {
		Preconditions.validate(status == PaymentStatus.WAITING_FOR_DEPOSIT, ErrorCode.INVALID_PAYMENT_STATUS);
	}

	private void validateCompleteStatus() {
		Preconditions.validate(status == PaymentStatus.READY || status == PaymentStatus.WAITING_FOR_DEPOSIT,
			ErrorCode.INVALID_PAYMENT_STATUS);
	}

	private void verifyPaymentKey(String paymentKey) {
		if (StringUtils.hasText(this.paymentKey)) {
			Preconditions.validate(this.paymentKey.equals(paymentKey), ErrorCode.INVALID_PAYMENT_KEY);
		}
	}

	private void validateCancelStatus() {
		Preconditions.validate(status != PaymentStatus.REFUNDED && status != PaymentStatus.CANCELED,
			ErrorCode.ALREADY_CANCELED_PAYMENT);
		// TODO canCancel Status 묶기 -> Test 코드도 업데이트
		Preconditions.validate(status == PaymentStatus.DONE || status == PaymentStatus.PARTIAL_REFUNDED
				|| status == PaymentStatus.WAITING_FOR_DEPOSIT,
			ErrorCode.CANNOT_CANCEL_PAYMENT);

	}

	private void validateCancelAmount(Long cancelAmount) {
		Preconditions.validate(cancelAmount > 0, ErrorCode.INVALID_CANCEL_AMOUNT);
		Preconditions.validate(cancelAmount <= cancelableAmount, ErrorCode.NOT_ENOUGH_CANCELABLE_AMOUNT);
	}

}
