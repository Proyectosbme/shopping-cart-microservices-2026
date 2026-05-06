package com.shoppingcart.order.application.command.port.output;

public interface ProductValidationPort {
    Double getProductPrice(Long productId);
}
