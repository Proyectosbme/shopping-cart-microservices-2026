package com.shoppingcart.payment.aplicacion.command.usecase;

import com.shoppingcart.payment.aplicacion.command.port.output.PaymentCommandRepository;
import com.shoppingcart.payment.domain.entity.Payment;

public class RefundPaymentUseCase {
    private final PaymentCommandRepository repository;

    public RefundPaymentUseCase(PaymentCommandRepository repository) {
        this.repository = repository;
    }

    public Payment execute(Long paymentId) {
        Payment payment = repository.findById(paymentId);
        return repository.save(payment.refund());
    }
}

