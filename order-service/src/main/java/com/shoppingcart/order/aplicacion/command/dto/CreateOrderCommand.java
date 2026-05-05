package com.shoppingcart.order.aplicacion.command.dto;

import java.util.List;

public record CreateOrderCommand(
    Long customerId,
    String customerName,
    String customerEmail,
    List<OrderDetailCommand> details
) {
    public record OrderDetailCommand(
        Long productId,
        String productName,
        int quantity,
        double unitPrice
    ) {}
}
