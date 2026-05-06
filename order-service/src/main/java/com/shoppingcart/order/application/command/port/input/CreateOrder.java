package com.shoppingcart.order.application.command.port.input;

import com.shoppingcart.order.application.command.dto.CreateOrderCommand;
import com.shoppingcart.order.domain.entity.Order;

public interface CreateOrder {
    Order create(CreateOrderCommand command);
}
