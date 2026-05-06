package com.shoppingcart.order.application.command.service;

import com.shoppingcart.order.application.command.dto.CreateOrderCommand;
import com.shoppingcart.order.application.command.port.input.CancelOrder;
import com.shoppingcart.order.application.command.port.input.CreateOrder;
import com.shoppingcart.order.application.command.port.input.MarkOrderAsPaid;
import com.shoppingcart.order.application.command.port.input.RevertOrderToPending;
import com.shoppingcart.order.application.command.port.output.OrderCommandRepository;
import com.shoppingcart.order.application.command.usecase.CancelOrderUseCase;
import com.shoppingcart.order.application.command.usecase.CreateOrderUseCase;
import com.shoppingcart.order.application.command.usecase.MarkOrderAsPaidUseCase;
import com.shoppingcart.order.application.command.usecase.RevertOrderToPendingUseCase;
import com.shoppingcart.order.domain.entity.Order;

public class OrderCommandService implements CreateOrder, CancelOrder, MarkOrderAsPaid, RevertOrderToPending {

    private final CreateOrderUseCase createOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final MarkOrderAsPaidUseCase markOrderAsPaidUseCase;
    private final RevertOrderToPendingUseCase revertOrderToPendingUseCase;

    public OrderCommandService(OrderCommandRepository orderCommandRepository) {
        this.createOrderUseCase = new CreateOrderUseCase(orderCommandRepository);
        this.cancelOrderUseCase = new CancelOrderUseCase(orderCommandRepository);
        this.markOrderAsPaidUseCase = new MarkOrderAsPaidUseCase(orderCommandRepository);
        this.revertOrderToPendingUseCase = new RevertOrderToPendingUseCase(orderCommandRepository);
    }

    @Override
    public Order create(CreateOrderCommand command) {
        return createOrderUseCase.execute(command);
    }

    @Override
    public Order cancel(Long orderId) {
        return cancelOrderUseCase.execute(orderId);
    }

    @Override
    public Order markAsPaid(Long orderId) {
        return markOrderAsPaidUseCase.execute(orderId);
    }

    @Override
    public Order revertToPending(Long orderId) {
        return revertOrderToPendingUseCase.execute(orderId);
    }
}
