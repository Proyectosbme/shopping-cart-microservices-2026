package com.shoppingcart.order.domain.entity;

public record OrderId(Long value) {
    public OrderId {
        if (value != null && value <= 0)
            throw new IllegalArgumentException("OrderId must be a positive number");
    }
}
