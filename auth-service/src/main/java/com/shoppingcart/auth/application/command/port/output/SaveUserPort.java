package com.shoppingcart.auth.application.command.port.output;

import com.shoppingcart.auth.domain.entity.User;

public interface SaveUserPort {
    User save(User user);
}
