package com.shoppingcart.order.aplicacion.command.usecase;

import java.math.BigDecimal;

import java.util.List;

import com.shoppingcart.order.aplicacion.command.dto.CreateOrderCommand;
import com.shoppingcart.order.aplicacion.command.port.output.OrderCommandRepository;
import com.shoppingcart.order.domain.entity.Customer;
import com.shoppingcart.order.domain.entity.Order;
import com.shoppingcart.order.domain.entity.OrderDetail;
import com.shoppingcart.order.domain.vo.Quantity;

public class CreateOrderUseCase {

    private final OrderCommandRepository orderCommandRepository;

    public CreateOrderUseCase(OrderCommandRepository orderCommandRepository) {
        this.orderCommandRepository = orderCommandRepository;
    }

    public Order execute(CreateOrderCommand command) {
        Customer customer = Customer.reconstitute(
                command.customerId(),
                command.customerName(),
                command.customerEmail());

        List<OrderDetail> details = command.details().stream()
                .map(d -> OrderDetail.create(
                        d.productId(),
                        d.productName(),
                        new Quantity(d.quantity()),
                        BigDecimal.valueOf(d.unitPrice())))
                .toList();

        return orderCommandRepository.save(Order.create(customer, details));
    }

}
