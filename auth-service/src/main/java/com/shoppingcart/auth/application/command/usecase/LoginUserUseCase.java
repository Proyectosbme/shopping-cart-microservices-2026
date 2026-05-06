package com.shoppingcart.auth.application.command.usecase;

import com.shoppingcart.auth.application.command.dto.LoginCommand;
import com.shoppingcart.auth.application.command.port.output.PasswordEncoderPort;
import com.shoppingcart.auth.application.command.port.output.TokenGeneratorPort;
import com.shoppingcart.auth.application.query.port.output.LoadUserPort;
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
