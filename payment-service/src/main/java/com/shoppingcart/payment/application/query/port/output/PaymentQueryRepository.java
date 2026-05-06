package com.shoppingcart.payment.application.query.port.output;

import java.util.List;

import com.shoppingcart.payment.domain.entity.Payment;

public interface PaymentQueryRepository {

    Payment findById(Long id);
    List<Payment> findByOrderId(Long orderId);

}
