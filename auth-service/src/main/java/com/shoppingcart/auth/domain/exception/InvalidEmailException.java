package com.shoppingcart.auth.domain.exception;

public class InvalidEmailException extends DomainException {
    public InvalidEmailException(String message) {
        super(message, "INVALID_EMAIL");
    }
}