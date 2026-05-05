package com.shoppingcart.order.aplicacion.command.port.input;

import com.shoppingcart.order.domain.entity.Order;

public interface CancelOrder {
    Order cancel(Long orderId);
}
