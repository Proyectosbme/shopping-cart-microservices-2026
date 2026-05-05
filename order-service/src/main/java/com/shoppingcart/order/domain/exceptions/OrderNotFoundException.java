package com.shoppingcart.order.domain.exceptions;

import com.shoppingcart.order.domain.vo.OrderId;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(OrderId id) {
        super("Order not found with id: " + id.value());
    }
}
