package com.shoppingcart.order.domain.exceptions;

import com.shoppingcart.order.domain.vo.OrderId;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(OrderId id) {
        super("No se encontró la orden con id: " + id.value());
    }
}