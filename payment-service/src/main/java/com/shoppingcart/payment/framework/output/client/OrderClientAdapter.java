package com.shoppingcart.payment.framework.output.client;

import com.shoppingcart.payment.aplicacion.command.port.output.OrderValidationPort;
import com.shoppingcart.payment.framework.output.client.dto.OrderClientDto;

public class OrderClientAdapter implements OrderValidationPort{

    @Override
    public OrderClientDto getOrderIfValid(Long orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrderIfValid'");
    }
    
}
