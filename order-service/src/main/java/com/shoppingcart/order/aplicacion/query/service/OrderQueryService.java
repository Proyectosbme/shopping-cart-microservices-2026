package com.shoppingcart.order.aplicacion.query.service;

import java.util.List;

import com.shoppingcart.order.aplicacion.query.port.input.GetOrder;
import com.shoppingcart.order.aplicacion.query.port.input.ListOrdersByCustomer;
import com.shoppingcart.order.aplicacion.query.port.output.OrderQueryRepository;
import com.shoppingcart.order.aplicacion.query.usecase.GetOrderUseCase;
import com.shoppingcart.order.aplicacion.query.usecase.ListOrdersByCustomerUseCase;
import com.shoppingcart.order.domain.entity.Order;

public class OrderQueryService implements GetOrder, ListOrdersByCustomer {

    private final GetOrderUseCase getOrderUseCase;
    private final ListOrdersByCustomerUseCase listOrdersByCustomerUseCase;

    public OrderQueryService(OrderQueryRepository orderQueryRepository) {
        this.getOrderUseCase = new GetOrderUseCase(orderQueryRepository);
        this.listOrdersByCustomerUseCase = new ListOrdersByCustomerUseCase(orderQueryRepository);
    }

    @Override
    public Order getById(Long orderId) {
        return getOrderUseCase.execute(orderId);
    }

    @Override
    public List<Order> listByCustomer(Long customerId) {
        return listOrdersByCustomerUseCase.execute(customerId);
    }

}
