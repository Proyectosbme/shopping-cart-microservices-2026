package com.shoppingcart.auth.domain.exception;

public class InvalidRoleException extends DomainException {
    public InvalidRoleException(String message) {
        super(message, "INVALID_ROLE");
    }
}
