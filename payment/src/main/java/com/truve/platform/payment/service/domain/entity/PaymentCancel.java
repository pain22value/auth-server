package com.truve.platform.payment.service.domain.entity;

import com.truve.platform.common.support.BaseEntity;
import com.truve.platform.payment.service.domain.constant.CancelType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "payment_cancels")
public class PaymentCancel extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_id")
	private Payment payment;

	@Column(nullable = false)
	private Long cancelAmount;

	@Column(nullable = false)
	private String cancelReason;

	@Enumerated(EnumType.STRING)
	private CancelType type;

	@Builder
	public PaymentCancel(Payment payment, Long cancelAmount, String cancelReason, CancelType type) {
		this.payment = payment;
		this.cancelAmount = cancelAmount;
		this.cancelReason = cancelReason;
		this.type = type;
	}
}
