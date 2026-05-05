package com.shoppingcart.product.domain.exceptions;

public class ProductValidationException extends RuntimeException {

    public ProductValidationException(String message) {
        super(message);
    }

    public ProductValidationException(String field, String message) {
        super("Validation failed for '" + field + "': " + message);
    }
}
