package com.shoppingcart.auth.aplicacion.command.port.input;

import com.shoppingcart.auth.aplicacion.command.dto.RegisterCommand;

public interface RegisterUserPort {
    String execute(RegisterCommand command);
}
