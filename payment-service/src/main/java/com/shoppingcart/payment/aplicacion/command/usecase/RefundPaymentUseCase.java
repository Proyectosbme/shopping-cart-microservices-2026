package com.shoppingcart.payment.aplicacion.command.usecase;

import com.shoppingcart.payment.aplicacion.command.port.output.OrderStatusPort;
import com.shoppingcart.payment.aplicacion.command.port.output.PaymentCommandRepository;
import com.shoppingcart.payment.domain.entity.Payment;

public class RefundPaymentUseCase {

    private final PaymentCommandRepository repository;
    private final OrderStatusPort orderStatusPort;

    public RefundPaymentUseCase(PaymentCommandRepository repository, OrderStatusPort orderStatusPort) {
        this.repository = repository;
        this.orderStatusPort = orderStatusPort;
    }

    public Payment execute(Long paymentId) {
        Payment payment = repository.findById(paymentId);
        payment = repository.save(payment.refund());
        orderStatusPort.revertToPending(payment.getOrderId());
        return payment;
    }
}
