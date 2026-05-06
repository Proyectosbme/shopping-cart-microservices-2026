package com.shoppingcart.auth.aplicacion.query.port.input;

import java.util.Optional;

import com.shoppingcart.auth.domain.entity.User;

public interface FindUserPort {
    Optional<User> findByEmail(String email);
}