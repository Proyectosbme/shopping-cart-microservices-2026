package com.shoppingcart.payment.framework.input.dto;

import java.math.BigDecimal;

import com.shoppingcart.payment.domain.vo.PaymentMethod;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProcessPaymentRequest(
    @NotNull(message = "Order id is required")
    Long orderId,

    @NotNull(message = "Customer id is required")
    Long customerId,

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    BigDecimal amount,

    @NotNull(message = "Payment method is required")
    PaymentMethod paymentMethod
) {}
