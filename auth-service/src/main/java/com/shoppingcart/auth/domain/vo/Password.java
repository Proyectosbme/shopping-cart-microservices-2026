package com.shoppingcart.auth.domain.vo;

import com.shoppingcart.auth.domain.exception.InvalidPasswordException;

public record Password(String value) {

    // For plain text passwords (registration/login)
    public static Password ofPlainText(String raw) {
        if (raw == null || raw.length() < 8)
            throw new InvalidPasswordException("Minimum 8 characters required");
        return new Password(raw);
    }

    // For reconstructing from DB (hash is already validated)
    public static Password ofHash(String hash) {
        return new Password(hash);
    }
}