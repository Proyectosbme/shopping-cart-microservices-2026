package com.shoppingcart.order.domain.exceptions;

public class PriceMismatchException extends RuntimeException {

    public PriceMismatchException(Long productId, Double expectedPrice, Double actualPrice) {
        super("Price mismatch for product " + productId + ". Expected: " + expectedPrice + ", Actual: " + actualPrice);
    }

    public PriceMismatchException(String message) {
        super(message);
    }
}
