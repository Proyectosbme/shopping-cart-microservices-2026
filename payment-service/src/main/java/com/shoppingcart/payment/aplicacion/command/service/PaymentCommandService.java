package com.shoppingcart.payment.aplicacion.command.service;

import com.shoppingcart.payment.aplicacion.command.dto.ProcessPaymentCommand;
import com.shoppingcart.payment.aplicacion.command.port.input.ProcessPayment;
import com.shoppingcart.payment.aplicacion.command.port.input.RefundPayment;
import com.shoppingcart.payment.aplicacion.command.port.output.OrderStatusPort;
import com.shoppingcart.payment.aplicacion.command.port.output.OrderValidationPort;
import com.shoppingcart.payment.aplicacion.command.port.output.PaymentCommandRepository;
import com.shoppingcart.payment.aplicacion.command.usecase.ProcessPaymentUseCase;
import com.shoppingcart.payment.aplicacion.command.usecase.RefundPaymentUseCase;
import com.shoppingcart.payment.domain.entity.Payment;

public class PaymentCommandService implements ProcessPayment, RefundPayment {

    private final ProcessPaymentUseCase processUseCase;
    private final RefundPaymentUseCase refundUseCase;

    public PaymentCommandService(ProcessPaymentUseCase processUseCase, RefundPaymentUseCase refundUseCase) {
        this.processUseCase = processUseCase;
        this.refundUseCase = refundUseCase;
    }

    public PaymentCommandService(PaymentCommandRepository repository,
            OrderValidationPort orderValidationPort, OrderStatusPort orderStatusPort) {
        this.processUseCase = new ProcessPaymentUseCase(repository, orderValidationPort, orderStatusPort);
        this.refundUseCase = new RefundPaymentUseCase(repository, orderStatusPort);
    }

    @Override
    public Payment process(ProcessPaymentCommand command) {
        return processUseCase.execute(command);
    }

    @Override
    public Payment refund(Long paymentId) {
        return refundUseCase.execute(paymentId);
    }
}
