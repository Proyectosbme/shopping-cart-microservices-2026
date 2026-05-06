package com.shoppingcart.payment.application.query.usecase;

import java.util.List;

import com.shoppingcart.payment.application.query.port.output.PaymentQueryRepository;
import com.shoppingcart.payment.domain.entity.Payment;

public class GetPaymentsByOrderUseCase {

    private final PaymentQueryRepository repository;

    public GetPaymentsByOrderUseCase(PaymentQueryRepository repository) {
        this.repository = repository;
    }

    public List<Payment> execute(Long orderId) {
        return repository.findByOrderId(orderId);
    }

}
