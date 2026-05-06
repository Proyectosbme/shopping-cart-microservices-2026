package com.shoppingcart.auth.aplicacion.command.port.input;

import com.shoppingcart.auth.aplicacion.command.dto.LoginCommand;

public interface LoginUserPort {
    String execute(LoginCommand command);
}
