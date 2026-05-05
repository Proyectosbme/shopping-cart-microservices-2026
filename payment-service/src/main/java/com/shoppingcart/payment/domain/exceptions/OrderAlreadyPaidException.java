package com.shoppingcart.payment.domain.exceptions;

public class OrderAlreadyPaidException extends RuntimeException {
    public OrderAlreadyPaidException(Long orderId) {
        super("Order " + orderId + " already has an active payment");
    }
}
