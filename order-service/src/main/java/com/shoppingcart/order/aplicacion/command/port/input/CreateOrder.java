package com.shoppingcart.order.aplicacion.command.port.input;

import com.shoppingcart.order.aplicacion.command.dto.CreateOrderCommand;
import com.shoppingcart.order.domain.entity.Order;

public interface CreateOrder {
    Order create(CreateOrderCommand command);
}
