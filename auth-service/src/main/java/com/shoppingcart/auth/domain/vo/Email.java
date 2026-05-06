package com.shoppingcart.auth.domain.vo;

import com.shoppingcart.auth.domain.exception.InvalidEmailException;

public record Email(String value) {

    // Compact constructor — valida antes de asignar
    public Email {
        if (value == null || !value.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"))
            throw new InvalidEmailException("Invalid email: " + value);
        value = value.toLowerCase().trim();
    }
}