package com.shoppingcart.payment.domain.exceptions;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(Long id) {
        super("Payment with ID " + id + " not found");
    }
}
