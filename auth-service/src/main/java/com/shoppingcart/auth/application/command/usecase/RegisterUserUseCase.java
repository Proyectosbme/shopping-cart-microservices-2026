package com.shoppingcart.auth.application.command.usecase;

import com.shoppingcart.auth.application.command.dto.RegisterCommand;
import com.shoppingcart.auth.application.command.port.output.PasswordEncoderPort;
import com.shoppingcart.auth.application.command.port.output.SaveUserPort;
import com.shoppingcart.auth.application.command.port.output.TokenGeneratorPort;
import com.shoppingcart.auth.application.query.port.output.LoadUserPort;
import com.shoppingcart.auth.domain.entity.User;
import com.shoppingcart.auth.domain.exception.UserAlreadyExistsException;
import com.shoppingcart.auth.domain.vo.Role;

public class RegisterUserUseCase {

    private final SaveUserPort saveUserPort;
    private final LoadUserPort loadUserPort;
    private final PasswordEncoderPort passwordEncoder;
    private final TokenGeneratorPort tokenGenerator;

    public RegisterUserUseCase(SaveUserPort saveUserPort, LoadUserPort loadUserPort,
            PasswordEncoderPort passwordEncoder, TokenGeneratorPort tokenGenerator) {
        this.saveUserPort = saveUserPort;
        this.loadUserPort = loadUserPort;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
    }

    public String execute(RegisterCommand command) {
        loadUserPort.findByEmail(command.email()).ifPresent(u -> {
            throw new UserAlreadyExistsException(command.email());
        });

        User user = new User(command.email(), command.password());
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        User userToSave = new User(null, user.getEmail(), hashedPassword, Role.USER);

        User saved = saveUserPort.save(userToSave);
        return tokenGenerator.generateToken(saved.getEmail());
    }
}
