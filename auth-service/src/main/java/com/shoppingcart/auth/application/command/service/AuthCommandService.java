package com.shoppingcart.auth.application.command.service;

import org.springframework.stereotype.Service;

import com.shoppingcart.auth.application.command.dto.LoginCommand;
import com.shoppingcart.auth.application.command.dto.RegisterCommand;
import com.shoppingcart.auth.application.command.port.input.LoginUserPort;
import com.shoppingcart.auth.application.command.port.input.RegisterUserPort;
import com.shoppingcart.auth.application.command.usecase.LoginUserUseCase;
import com.shoppingcart.auth.application.command.usecase.RegisterUserUseCase;

@Service
public class AuthCommandService implements RegisterUserPort, LoginUserPort {

    private final RegisterUserUseCase registerUseCase;
    private final LoginUserUseCase loginUseCase;

    public AuthCommandService(RegisterUserUseCase registerUseCase, LoginUserUseCase loginUseCase) {
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
    }

    @Override
    public String execute(RegisterCommand command) {
        return registerUseCase.execute(command);
    }

    @Override
    public String execute(LoginCommand command) {
        return loginUseCase.execute(command);
    }
}
