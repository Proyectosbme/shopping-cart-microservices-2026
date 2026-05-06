package com.shoppingcart.auth.application.query.usecase;

import com.shoppingcart.auth.application.query.port.output.LoadUserPort;
import com.shoppingcart.auth.domain.entity.User;
import com.shoppingcart.auth.domain.exception.UserNotFoundException;

public class FindUserUseCase {

    private final LoadUserPort loadUserPort;

    public FindUserUseCase(LoadUserPort loadUserPort) {
        this.loadUserPort = loadUserPort;
    }

    public User findByEmail(String email) {
        return loadUserPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}
