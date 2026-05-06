package com.shoppingcart.order.framework.input.mapper;

import com.shoppingcart.order.application.command.dto.CreateOrderCommand;
import com.shoppingcart.order.domain.entity.Order;
import com.shoppingcart.order.framework.input.dto.CreateOrderRequest;
import com.shoppingcart.order.framework.input.dto.OrderResponse;
import com.shoppingcart.order.framework.input.dto.OrderResponse.OrderDetailResponse;

public class OrderHttpMapper {

    public static CreateOrderCommand toCommand(CreateOrderRequest request) {
        return new CreateOrderCommand(
                request.customerId(),
                request.customerName(),
                request.customerEmail(),
                request.details().stream()
                        .map(d -> new CreateOrderCommand.OrderDetailCommand(
                                d.productId(),
                                d.productName(),
                                d.quantity(),
                                d.unitPrice()))
                        .toList());
    }

    public static OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId().value(),
                order.getCustomer().getId(),
                order.getCustomer().getName(),
                order.getCustomer().getEmail(),
                order.getStatus().name(),
                order.getCreatedAt(),
                order.total(),
                order.getDetails().stream()
                        .map(d -> new OrderDetailResponse(
                                d.getId(),
                                d.getProductId(),
                                d.getProductName(),
                                d.getQuantity().value(),
                                d.getUnitPrice(),
                                d.subtotal()))
                        .toList());
    }
}
