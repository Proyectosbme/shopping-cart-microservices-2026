package com.shoppingcart.auth.aplicacion.command.service;

import org.springframework.stereotype.Service;

import com.shoppingcart.auth.aplicacion.command.dto.LoginCommand;
import com.shoppingcart.auth.aplicacion.command.dto.RegisterCommand;
import com.shoppingcart.auth.aplicacion.command.port.input.LoginUserPort;
import com.shoppingcart.auth.aplicacion.command.port.input.RegisterUserPort;
import com.shoppingcart.auth.aplicacion.command.usecase.LoginUserUseCase;
import com.shoppingcart.auth.aplicacion.command.usecase.RegisterUserUseCase;

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
