package com.shoppingcart.order.aplicacion.query.usecase;

import java.util.List;

import com.shoppingcart.order.aplicacion.query.port.output.OrderQueryRepository;
import com.shoppingcart.order.domain.entity.Order;

public class ListOrdersByCustomerUseCase {

    private final OrderQueryRepository orderQueryRepository;

    public ListOrdersByCustomerUseCase(OrderQueryRepository orderQueryRepository) {
        this.orderQueryRepository = orderQueryRepository;
    }

    public List<Order> execute(Long customerId) {
        return orderQueryRepository.findByCustomerId(customerId);
    }
}
