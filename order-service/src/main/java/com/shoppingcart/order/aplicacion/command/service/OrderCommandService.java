package com.shoppingcart.order.aplicacion.command.service;

import com.shoppingcart.order.aplicacion.command.dto.CreateOrderCommand;
import com.shoppingcart.order.aplicacion.command.port.input.CancelOrder;
import com.shoppingcart.order.aplicacion.command.port.input.CreateOrder;
import com.shoppingcart.order.aplicacion.command.port.output.OrderCommandRepository;
import com.shoppingcart.order.aplicacion.command.usecase.CancelOrderUseCase;
import com.shoppingcart.order.aplicacion.command.usecase.CreateOrderUseCase;
import com.shoppingcart.order.domain.entity.Order;

public class OrderCommandService implements CreateOrder, CancelOrder {

    private final CreateOrderUseCase createOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;

    public OrderCommandService(OrderCommandRepository orderCommandRepository) {
        this.createOrderUseCase = new CreateOrderUseCase(orderCommandRepository);
        this.cancelOrderUseCase = new CancelOrderUseCase(orderCommandRepository);
    }

    @Override
    public Order create(CreateOrderCommand command) {
        return createOrderUseCase.execute(command);
    }

    @Override
    public Order cancel(Long orderId) {
        return cancelOrderUseCase.execute(orderId);
    }

}
