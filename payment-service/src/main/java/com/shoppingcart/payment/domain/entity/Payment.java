package com.shoppingcart.payment.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.shoppingcart.payment.domain.exceptions.InvalidPaymentAmountException;
import com.shoppingcart.payment.domain.exceptions.PaymentAlreadyProcessedException;
import com.shoppingcart.payment.domain.vo.PaymentMethod;
import com.shoppingcart.payment.domain.vo.PaymentStatus;

public class Payment {
    private Long id;
    private Long orderId;
    private Long customerId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private LocalDateTime createdAt;

    private Payment(Long id, Long orderId, Long customerId, BigDecimal amount, PaymentMethod paymentMethod,
            PaymentStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static Payment create(Long orderId, Long customerId, BigDecimal amount, PaymentMethod paymentMethod) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new InvalidPaymentAmountException();
        return new Payment(null, orderId, customerId, amount, paymentMethod, PaymentStatus.PENDING,
                LocalDateTime.now());
    }

    public static Payment reconstitute(Long id, Long orderId, Long customerId,
            BigDecimal amount, PaymentMethod method, PaymentStatus status, LocalDateTime createdAt) {
        return new Payment(id, orderId, customerId, amount, method, status, createdAt);
    }

    public Payment approve() {
        if (this.status != PaymentStatus.PENDING)
            throw new PaymentAlreadyProcessedException(this.id);
        this.status = PaymentStatus.APPROVED;
        return this;
    }

    public Payment reject() {
        if (this.status != PaymentStatus.PENDING)
            throw new PaymentAlreadyProcessedException(this.id);
        this.status = PaymentStatus.REJECTED;
        return this;
    }

    public Payment refund() {
        if (this.status != PaymentStatus.APPROVED)
            throw new PaymentAlreadyProcessedException(this.id);
        this.status = PaymentStatus.REFUNDED;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
