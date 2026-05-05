package com.shoppingcart.order.aplicacion.query.port.input;

import com.shoppingcart.order.domain.entity.Order;

public interface GetOrder {
    Order getById(Long orderId);
}
