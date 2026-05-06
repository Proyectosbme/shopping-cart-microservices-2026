package com.shoppingcart.order.application.command.usecase;

import com.shoppingcart.order.application.command.port.output.OrderCommandRepository;
import com.shoppingcart.order.domain.entity.Order;
import com.shoppingcart.order.domain.exceptions.OrderNotFoundException;
import com.shoppingcart.order.domain.vo.OrderId;

public class MarkOrderAsPaidUseCase {

    private final OrderCommandRepository orderCommandRepository;

    public MarkOrderAsPaidUseCase(OrderCommandRepository orderCommandRepository) {
        this.orderCommandRepository = orderCommandRepository;
    }

    public Order execute(Long orderId) {
        OrderId id = new OrderId(orderId);
        Order order = orderCommandRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        order.markAsPaid();
        return orderCommandRepository.save(order);
    }
}
