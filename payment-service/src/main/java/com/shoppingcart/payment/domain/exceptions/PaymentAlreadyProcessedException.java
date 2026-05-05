package com.shoppingcart.payment.domain.exceptions;


public class PaymentAlreadyProcessedException extends RuntimeException {
    public PaymentAlreadyProcessedException(Long id) {
        super("Payment " + id + " has already been processed");
    }
}

