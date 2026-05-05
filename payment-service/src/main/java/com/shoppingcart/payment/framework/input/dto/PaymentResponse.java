package com.shoppingcart.payment.framework.input.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.shoppingcart.payment.domain.vo.PaymentMethod;
import com.shoppingcart.payment.domain.vo.PaymentStatus;

public record PaymentResponse(
    Long id,
    Long orderId,
    Long customerId,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    PaymentStatus status,
    LocalDateTime createdAt
){}