package com.shoppingcart.order.aplicacion.query.port.input;

import java.util.List;

import com.shoppingcart.order.domain.entity.Order;

public interface ListOrdersByCustomer {
    List<Order> listByCustomer(Long customerId);
}
