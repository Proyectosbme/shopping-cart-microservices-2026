package com.shoppingcart.auth.application.command.port.input;

import com.shoppingcart.auth.application.command.dto.LoginCommand;

public interface LoginUserPort {
    String execute(LoginCommand command);
}
