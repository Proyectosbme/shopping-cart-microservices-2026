package com.shoppingcart.auth.application.query.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shoppingcart.auth.application.query.port.input.FindUserPort;
import com.shoppingcart.auth.application.query.usecase.FindUserUseCase;
import com.shoppingcart.auth.domain.entity.User;

@Service
public class UserQueryService implements FindUserPort {

    private final FindUserUseCase findUserUseCase;

    public UserQueryService(FindUserUseCase findUserUseCase) {
        this.findUserUseCase = findUserUseCase;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.of(findUserUseCase.findByEmail(email));
    }
}
