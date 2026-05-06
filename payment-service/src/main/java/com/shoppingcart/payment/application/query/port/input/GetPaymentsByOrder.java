package com.shoppingcart.payment.application.query.port.input;

import java.util.List;

import com.shoppingcart.payment.domain.entity.Payment;

public interface GetPaymentsByOrder {
    
    List<Payment> getByOrderId(Long orderId);

}
