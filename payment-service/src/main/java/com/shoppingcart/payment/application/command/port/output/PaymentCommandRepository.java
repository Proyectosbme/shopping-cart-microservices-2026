package com.shoppingcart.payment.application.command.port.output;

import com.shoppingcart.payment.domain.entity.Payment;

public interface PaymentCommandRepository {
    Payment save(Payment payment);

    Payment findById(Long id);

    boolean existsActivePaymentForOrder(Long orderId);

}
