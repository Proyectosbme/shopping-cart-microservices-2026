package com.shoppingcart.auth.aplicacion.command.port.output;

public interface TokenGeneratorPort {
    String generateToken(String email);
}
