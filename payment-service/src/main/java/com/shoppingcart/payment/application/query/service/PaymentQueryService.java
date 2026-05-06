package com.shoppingcart.payment.application.query.service;

import java.util.List;

import com.shoppingcart.payment.application.query.port.input.GetPayment;
import com.shoppingcart.payment.application.query.port.input.GetPaymentsByOrder;
import com.shoppingcart.payment.application.query.port.output.PaymentQueryRepository;
import com.shoppingcart.payment.application.query.usecase.GetPaymentUseCase;
import com.shoppingcart.payment.application.query.usecase.GetPaymentsByOrderUseCase;
import com.shoppingcart.payment.domain.entity.Payment;

public class PaymentQueryService implements GetPayment, GetPaymentsByOrder {
    private final GetPaymentUseCase getUseCase;
    private final GetPaymentsByOrderUseCase getByOrderUseCase;

    public PaymentQueryService(PaymentQueryRepository repository) {
        this.getUseCase = new GetPaymentUseCase(repository);
        this.getByOrderUseCase = new GetPaymentsByOrderUseCase(repository);
    }

    public Payment getById(Long id) {
        return getUseCase.execute(id);
    }

    public List<Payment> getByOrderId(Long orderId) {
        return getByOrderUseCase.execute(orderId);
    }
}
