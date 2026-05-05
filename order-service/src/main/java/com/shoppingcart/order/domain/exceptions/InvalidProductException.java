package com.shoppingcart.order.domain.exceptions;

public class InvalidProductException extends RuntimeException {

    public InvalidProductException(String message) {
        super(message);
    }

    public InvalidProductException(Long productId) {
        super("Product with ID " + productId + " does not exist or is not available");
    }
}
