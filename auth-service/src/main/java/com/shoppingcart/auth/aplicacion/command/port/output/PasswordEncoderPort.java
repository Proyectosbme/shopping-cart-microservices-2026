package com.shoppingcart.auth.aplicacion.command.port.output;

public interface PasswordEncoderPort {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
