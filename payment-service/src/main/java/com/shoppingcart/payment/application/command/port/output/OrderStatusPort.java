package com.shoppingcart.payment.application.command.port.output;

public interface OrderStatusPort {
    void markAsPaid(Long orderId);
    void revertToPending(Long orderId);
}
