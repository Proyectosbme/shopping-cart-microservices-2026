package com.shoppingcart.order.application.query.usecase;

import com.shoppingcart.order.application.query.port.output.OrderQueryRepository;
import com.shoppingcart.order.domain.entity.Order;
import com.shoppingcart.order.domain.exceptions.OrderNotFoundException;
import com.shoppingcart.order.domain.vo.OrderId;

public class GetOrderUseCase {

    private final OrderQueryRepository orderQueryRepository;

    public GetOrderUseCase(OrderQueryRepository orderQueryRepository) {
        this.orderQueryRepository = orderQueryRepository;
    }

    public Order execute(Long orderId) {
        OrderId id = new OrderId(orderId);
        return orderQueryRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }
}
