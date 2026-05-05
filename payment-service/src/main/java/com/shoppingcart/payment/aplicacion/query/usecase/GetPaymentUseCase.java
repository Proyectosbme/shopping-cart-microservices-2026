package com.shoppingcart.payment.aplicacion.query.usecase;

import com.shoppingcart.payment.aplicacion.query.port.output.PaymentQueryRepository;
import com.shoppingcart.payment.domain.entity.Payment;

public class GetPaymentUseCase {

    private final PaymentQueryRepository repository;

    public GetPaymentUseCase(PaymentQueryRepository repository) {
        this.repository = repository;
    }

    public Payment execute(Long id) {
        return repository.findById(id);
    }

}
