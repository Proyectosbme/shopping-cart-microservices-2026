package com.shoppingcart.auth.aplicacion.command.usecase;

import com.shoppingcart.auth.aplicacion.command.dto.LoginCommand;
import com.shoppingcart.auth.aplicacion.command.port.output.PasswordEncoderPort;
import com.shoppingcart.auth.aplicacion.command.port.output.TokenGeneratorPort;
import com.shoppingcart.auth.aplicacion.query.port.output.LoadUserPort;
import com.shoppingcart.auth.domain.entity.User;
import com.shoppingcart.auth.domain.exception.InvalidPasswordException;
import com.shoppingcart.auth.domain.exception.UserNotFoundException;

public class LoginUserUseCase {

    private final LoadUserPort loadUserPort;
    private final PasswordEncoderPort passwordEncoder;
    private final TokenGeneratorPort tokenGenerator;

    public LoginUserUseCase(LoadUserPort loadUserPort, PasswordEncoderPort passwordEncoder,
            TokenGeneratorPort tokenGenerator) {
        this.loadUserPort = loadUserPort;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
    }

    public String execute(LoginCommand command) {
        User user = loadUserPort.findByEmail(command.email())
                .orElseThrow(() -> new UserNotFoundException(command.email()));

        if (!passwordEncoder.matches(command.password(), user.getPassword()))
            throw new InvalidPasswordException("Invalid credentials");

        return tokenGenerator.generateToken(user.getEmail());
    }
}
