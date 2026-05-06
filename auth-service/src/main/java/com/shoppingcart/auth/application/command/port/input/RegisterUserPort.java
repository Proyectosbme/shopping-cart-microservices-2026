package com.shoppingcart.auth.application.command.port.input;

import com.shoppingcart.auth.application.command.dto.RegisterCommand;

public interface RegisterUserPort {
    String execute(RegisterCommand command);
}
