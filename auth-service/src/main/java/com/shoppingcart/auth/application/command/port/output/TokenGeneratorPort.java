package com.shoppingcart.auth.application.command.port.output;

public interface TokenGeneratorPort {
    String generateToken(String email);
}
