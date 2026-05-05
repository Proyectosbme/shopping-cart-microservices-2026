package com.shoppingcart.payment.aplicacion.command.usecase;

import com.shoppingcart.payment.aplicacion.command.dto.ProcessPaymentCommand;
import com.shoppingcart.payment.aplicacion.command.port.output.OrderStatusPort;
import com.shoppingcart.payment.aplicacion.command.port.output.OrderValidationPort;
import com.shoppingcart.payment.aplicacion.command.port.output.PaymentCommandRepository;
import com.shoppingcart.payment.domain.entity.Payment;
import com.shoppingcart.payment.domain.exceptions.OrderAlreadyPaidException;

public class ProcessPaymentUseCase {

    private final PaymentCommandRepository repository;
    private final OrderValidationPort orderValidationPort;
    private final OrderStatusPort orderStatusPort;

    public ProcessPaymentUseCase(PaymentCommandRepository repository,
            OrderValidationPort orderValidationPort, OrderStatusPort orderStatusPort) {
        this.repository = repository;
        this.orderValidationPort = orderValidationPort;
        this.orderStatusPort = orderStatusPort;
    }

    public Payment execute(ProcessPaymentCommand command) {
        orderValidationPort.getOrderIfValid(command.orderId());

        if (repository.existsActivePaymentForOrder(command.orderId()))
            throw new OrderAlreadyPaidException(command.orderId());

        Payment payment = Payment.create(
                command.orderId(), command.customerId(),
                command.amount(), command.paymentMethod());

        // Simulate payment: 80% approval rate
        boolean approved = Math.random() < 0.8;
        payment = approved ? payment.approve() : payment.reject();
        payment = repository.save(payment);

        if (payment.getStatus().name().equals("APPROVED"))
            orderStatusPort.markAsPaid(command.orderId());

        return payment;
    }
}
