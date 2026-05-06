package com.shoppingcart.auth.domain.vo;

import com.shoppingcart.auth.domain.exception.InvalidRoleException;

public enum Role {
    USER,
    ADMIN;

    public static Role from(String value) {
        try {
            return Role.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new InvalidRoleException("Invalid role: " + value + ". Use USER or ADMIN");
        }
    }
}