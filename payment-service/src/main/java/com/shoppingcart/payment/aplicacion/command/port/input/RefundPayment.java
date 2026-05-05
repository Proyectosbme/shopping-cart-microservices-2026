package com.shoppingcart.payment.aplicacion.command.port.input;

import com.shoppingcart.payment.domain.entity.Payment;

public interface RefundPayment {
    Payment refund(Long paymentId);

}
