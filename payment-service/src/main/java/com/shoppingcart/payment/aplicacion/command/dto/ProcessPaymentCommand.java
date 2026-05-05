package com.shoppingcart.payment.aplicacion.command.dto;
import java.math.BigDecimal;
import com.shoppingcart.payment.domain.vo.PaymentMethod;

public record ProcessPaymentCommand(
    Long orderId,
    Long customerId,
    BigDecimal amount,
    PaymentMethod paymentMethod
) {}



