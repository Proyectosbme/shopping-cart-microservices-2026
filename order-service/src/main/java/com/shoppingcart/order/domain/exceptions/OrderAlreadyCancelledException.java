package com.shoppingcart.order.domain.exceptions;

import com.shoppingcart.order.domain.vo.OrderId;

public class OrderAlreadyCancelledException extends RuntimeException {
    public OrderAlreadyCancelledException(OrderId id) {
        super("La orden con id " + id.value() + " ya fue cancelada");
    }
}
