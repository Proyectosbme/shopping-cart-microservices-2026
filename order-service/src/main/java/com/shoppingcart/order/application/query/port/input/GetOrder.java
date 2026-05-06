package com.shoppingcart.order.application.query.port.input;

import com.shoppingcart.order.domain.entity.Order;

public interface GetOrder {
    Order getById(Long orderId);
}
