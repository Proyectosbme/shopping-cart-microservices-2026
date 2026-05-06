package com.shoppingcart.order.application.query.usecase;

import java.util.List;

import com.shoppingcart.order.application.query.port.output.OrderQueryRepository;
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
