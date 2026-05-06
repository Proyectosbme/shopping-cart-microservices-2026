package com.shoppingcart.order.application.command.port.input;

import com.shoppingcart.order.domain.entity.Order;

public interface RevertOrderToPending {
    Order revertToPending(Long orderId);
}
