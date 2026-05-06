package com.shoppingcart.payment.application.command.port.input;

import com.shoppingcart.payment.domain.entity.Payment;

public interface RefundPayment {
    Payment refund(Long paymentId);

}
