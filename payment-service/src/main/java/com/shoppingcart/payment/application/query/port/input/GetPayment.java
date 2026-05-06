package com.shoppingcart.payment.application.query.port.input;

import com.shoppingcart.payment.domain.entity.Payment;

public interface GetPayment {

    Payment getById(Long id);

}
