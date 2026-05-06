package com.shoppingcart.auth.aplicacion.query.port.output;

import java.util.Optional;

import com.shoppingcart.auth.domain.entity.User;

public interface LoadUserPort {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
}
