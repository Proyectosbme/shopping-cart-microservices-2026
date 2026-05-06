package com.shoppingcart.auth.application.query.port.output;

import java.util.Optional;

import com.shoppingcart.auth.domain.entity.User;

public interface LoadUserPort {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
}
