package com.shoppingcart.payment.framework.input.mapper;

import com.shoppingcart.payment.aplicacion.command.dto.ProcessPaymentCommand;
import com.shoppingcart.payment.domain.entity.Payment;
import com.shoppingcart.payment.framework.input.dto.PaymentResponse;
import com.shoppingcart.payment.framework.input.dto.ProcessPaymentRequest;

public class PaymentHttpMapper {

    private PaymentHttpMapper() {}

    public static ProcessPaymentCommand toCommand(ProcessPaymentRequest request) {
        return new ProcessPaymentCommand(
            request.orderId(),
            request.customerId(),
            request.amount(),
            request.paymentMethod()
        );
    }

    public static PaymentResponse toResponse(Payment payment) {
        return new PaymentResponse(
            payment.getId(),
            payment.getOrderId(),
            payment.getCustomerId(),
            payment.getAmount(),
            payment.getPaymentMethod(),
            payment.getStatus(),
            payment.getCreatedAt()
        );
    }
}

