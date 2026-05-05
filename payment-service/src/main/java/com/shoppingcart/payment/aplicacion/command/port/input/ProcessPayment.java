package com.shoppingcart.payment.aplicacion.command.port.input;

import com.shoppingcart.payment.aplicacion.command.dto.ProcessPaymentCommand;
import com.shoppingcart.payment.domain.entity.Payment;

public interface ProcessPayment {
    Payment process(ProcessPaymentCommand command);

}
