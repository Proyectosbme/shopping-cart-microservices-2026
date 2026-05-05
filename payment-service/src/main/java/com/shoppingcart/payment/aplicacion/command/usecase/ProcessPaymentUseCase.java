package com.shoppingcart.payment.aplicacion.command.usecase;

import com.shoppingcart.payment.aplicacion.command.dto.ProcessPaymentCommand;
import com.shoppingcart.payment.aplicacion.command.port.output.PaymentCommandRepository;
import com.shoppingcart.payment.domain.entity.Payment;
import com.shoppingcart.payment.domain.exceptions.OrderAlreadyPaidException;

public class ProcessPaymentUseCase {

    private final PaymentCommandRepository repository;

    public ProcessPaymentUseCase(PaymentCommandRepository repository) {
        this.repository = repository;
    }

    public Payment execute(ProcessPaymentCommand command) {
        if (repository.existsActivePaymentForOrder(command.orderId()))
            throw new OrderAlreadyPaidException(command.orderId());

        Payment payment = Payment.create(
                command.orderId(), command.customerId(),
                command.amount(), command.paymentMethod());
        // Simulación: aprueba el 80% de los pagos
        boolean approved = Math.random() < 0.8;
        payment = approved ? payment.approve() : payment.reject();
        return repository.save(payment);
    }
}
