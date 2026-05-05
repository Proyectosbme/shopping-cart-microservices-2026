package com.shoppingcart.order.aplicacion.command.usecase;

import com.shoppingcart.order.aplicacion.command.port.output.OrderCommandRepository;
import com.shoppingcart.order.domain.entity.Order;
import com.shoppingcart.order.domain.exceptions.OrderNotFoundException;
import com.shoppingcart.order.domain.vo.OrderId;

public class CancelOrderUseCase {

    private final OrderCommandRepository orderCommandRepository;

    public CancelOrderUseCase(OrderCommandRepository orderCommandRepository) {
        this.orderCommandRepository = orderCommandRepository;
    }

    public Order execute(Long orderId) {
        OrderId id = new OrderId(orderId);
        Order order = orderCommandRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        order.cancel();
        return orderCommandRepository.save(order);
    }
}
