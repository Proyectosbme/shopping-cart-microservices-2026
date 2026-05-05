package com.shoppingcart.payment.aplicacion.command.port.output;

public interface OrderStatusPort {
    void markAsPaid(Long orderId);
    void revertToPending(Long orderId);
}
