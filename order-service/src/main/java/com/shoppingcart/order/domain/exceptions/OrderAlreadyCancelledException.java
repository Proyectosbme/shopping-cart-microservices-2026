package com.shoppingcart.order.domain.exceptions;

import com.shoppingcart.order.domain.vo.OrderId;

public class OrderAlreadyCancelledException extends RuntimeException {
    public OrderAlreadyCancelledException(OrderId id) {
        super("Order with id " + id.value() + " has already been cancelled");
    }
}
