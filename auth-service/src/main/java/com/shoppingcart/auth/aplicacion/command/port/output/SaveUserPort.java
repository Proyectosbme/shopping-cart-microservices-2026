package com.shoppingcart.auth.aplicacion.command.port.output;

import com.shoppingcart.auth.domain.entity.User;

public interface SaveUserPort {
    User save(User user);
}
