package com.shoppingcart.payment.framework.input.dto;

import java.math.BigDecimal;

import com.shoppingcart.payment.domain.vo.PaymentMethod;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProcessPaymentRequest(
    @NotNull(message = "El id de la orden es obligatorio")
    Long orderId,

    @NotNull(message = "El id del cliente es obligatorio")
    Long customerId,

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    BigDecimal amount,

    @NotNull(message = "El método de pago es obligatorio")
    PaymentMethod paymentMethod
){}
