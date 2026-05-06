package com.shoppingcart.order.application.command.port.input;

import com.shoppingcart.order.domain.entity.Order;

public interface MarkOrderAsPaid {
    Order markAsPaid(Long orderId);
}
