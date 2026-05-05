package com.shoppingcart.payment.domain.exceptions;


public class InvalidPaymentAmountException extends RuntimeException {
    public InvalidPaymentAmountException() {
        super("Payment amount must be greater than zero");
    }
}
