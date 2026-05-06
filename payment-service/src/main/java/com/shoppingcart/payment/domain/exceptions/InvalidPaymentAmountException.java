package com.shoppingcart.payment.domain.exceptions;


public class InvalidPaymentAmountException extends RuntimeException {
    public InvalidPaymentAmountException() {
        super("Payment amount must be greater than zero");
    }

    public InvalidPaymentAmountException(java.math.BigDecimal expected, java.math.BigDecimal received) {
        super("Payment amount " + received + " does not match order total " + expected);
    }
}
