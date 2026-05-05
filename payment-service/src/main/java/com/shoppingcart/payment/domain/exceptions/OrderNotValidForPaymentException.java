package com.shoppingcart.payment.domain.exceptions;

public class OrderNotValidForPaymentException extends RuntimeException {

    public OrderNotValidForPaymentException(Long orderId, String status) {
        super("Order " + orderId + " is not valid for payment, current status: " + status);
    }
}
