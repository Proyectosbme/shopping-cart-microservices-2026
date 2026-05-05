package com.shoppingcart.order.aplicacion.query.port.output;

import java.util.List;
import java.util.Optional;

import com.shoppingcart.order.domain.entity.Order;
import com.shoppingcart.order.domain.vo.OrderId;

public interface OrderQueryRepository {

    Optional<Order> findById(OrderId id);

    List<Order> findByCustomerId(Long customerId);
}
