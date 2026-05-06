package com.shoppingcart.payment.application.command.port.input;

import com.shoppingcart.payment.application.command.dto.ProcessPaymentCommand;
import com.shoppingcart.payment.domain.entity.Payment;

public interface ProcessPayment {
    Payment process(ProcessPaymentCommand command);

}
