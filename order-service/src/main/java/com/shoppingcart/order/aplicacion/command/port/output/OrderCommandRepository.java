package com.shoppingcart.order.aplicacion.command.port.output;

import java.util.Optional;

import com.shoppingcart.order.domain.entity.Order;
import com.shoppingcart.order.domain.vo.OrderId;

public interface OrderCommandRepository {
    Optional<Order> findById(OrderId id);
    Order save(Order order);
}
