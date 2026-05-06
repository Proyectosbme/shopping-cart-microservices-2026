package com.shoppingcart.auth.domain.exception;

public class InvalidPasswordException extends DomainException {
    public InvalidPasswordException(String message) {
        super(message, "INVALID_PASSWORD");
    }
}