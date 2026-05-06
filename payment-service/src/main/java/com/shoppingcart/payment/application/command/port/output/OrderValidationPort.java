package com.shoppingcart.payment.application.command.port.output;

import com.shoppingcart.payment.framework.output.client.dto.OrderClientDto;

public interface OrderValidationPort {
    OrderClientDto getOrderIfValid(Long orderId);
}
