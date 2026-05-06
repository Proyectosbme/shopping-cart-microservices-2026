package com.shoppingcart.auth.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shoppingcart.auth.application.command.port.output.PasswordEncoderPort;
import com.shoppingcart.auth.application.command.port.output.SaveUserPort;
import com.shoppingcart.auth.application.command.port.output.TokenGeneratorPort;
import com.shoppingcart.auth.application.command.usecase.LoginUserUseCase;
import com.shoppingcart.auth.application.command.usecase.RegisterUserUseCase;
import com.shoppingcart.auth.application.query.port.output.LoadUserPort;
import com.shoppingcart.auth.application.query.usecase.FindUserUseCase;
import com.shoppingcart.auth.framework.output.persistence.adapters.UserPersistenceAdapter;
import com.shoppingcart.auth.framework.output.persistence.repository.UserJpaRepository;

@Configuration
public class ApplicationConfig {

    @Bean
    public RegisterUserUseCase registerUserUseCase(SaveUserPort saveUserPort,
            LoadUserPort loadUserPort, PasswordEncoderPort passwordEncoder,
            TokenGeneratorPort tokenGenerator) {
        return new RegisterUserUseCase(saveUserPort, loadUserPort, passwordEncoder, tokenGenerator);
    }

    @Bean
    public LoginUserUseCase loginUserUseCase(LoadUserPort loadUserPort,
            PasswordEncoderPort passwordEncoder, TokenGeneratorPort tokenGenerator) {
        return new LoginUserUseCase(loadUserPort, passwordEncoder, tokenGenerator);
    }

    @Bean
    public FindUserUseCase findUserUseCase(LoadUserPort loadUserPort) {
        return new FindUserUseCase(loadUserPort);
    }

    @Bean
    public UserPersistenceAdapter userPersistenceAdapter(UserJpaRepository jpaRepository) {
        return new UserPersistenceAdapter(jpaRepository);
    }
}
