package com.shoppingcart.payment.framework.output.client.adapters;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.shoppingcart.payment.aplicacion.command.port.output.OrderStatusPort;
import com.shoppingcart.payment.aplicacion.command.port.output.OrderValidationPort;
import com.shoppingcart.payment.domain.exceptions.OrderNotFoundException;
import com.shoppingcart.payment.domain.exceptions.OrderNotValidForPaymentException;
import com.shoppingcart.payment.framework.output.client.dto.OrderClientDto;

public class OrderClientAdapter implements OrderValidationPort, OrderStatusPort {

    private final RestTemplate restTemplate;
    private final String orderServiceUrl;

    public OrderClientAdapter(RestTemplate restTemplate, String orderServiceUrl) {
        this.restTemplate = restTemplate;
        this.orderServiceUrl = orderServiceUrl;
    }

    @Override
    public OrderClientDto getOrderIfValid(Long orderId) {
        try {
            OrderClientDto order = restTemplate.getForObject(orderServiceUrl + "/" + orderId, OrderClientDto.class);
            if (order == null) throw new OrderNotFoundException(orderId);
            if ("CANCELLED".equals(order.status()) || "PAID".equals(order.status()))
                throw new OrderNotValidForPaymentException(orderId, order.status());
            return order;
        } catch (HttpClientErrorException.NotFound e) {
            throw new OrderNotFoundException(orderId);
        }
    }

    @Override
    public void markAsPaid(Long orderId) {
        restTemplate.patchForObject(orderServiceUrl + "/" + orderId + "/pay", null, Void.class);
    }

    @Override
    public void revertToPending(Long orderId) {
        restTemplate.patchForObject(orderServiceUrl + "/" + orderId + "/revert-payment", null, Void.class);
    }
}
