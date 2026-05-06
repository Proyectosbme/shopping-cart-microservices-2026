package com.shoppingcart.auth.domain.exception;

public class UserNotFoundException extends DomainException {
    public UserNotFoundException(String email) {
        super("User not found: " + email, "USER_NOT_FOUND");
    }
}