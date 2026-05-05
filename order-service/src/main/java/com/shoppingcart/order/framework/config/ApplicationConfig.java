package com.shoppingcart.order.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shoppingcart.order.aplicacion.command.port.output.OrderCommandRepository;
import com.shoppingcart.order.aplicacion.command.service.OrderCommandService;
import com.shoppingcart.order.aplicacion.query.port.output.OrderQueryRepository;
import com.shoppingcart.order.aplicacion.query.service.OrderQueryService;

@Configuration
public class ApplicationConfig {

    @Bean
    public OrderCommandService orderCommandService(OrderCommandRepository orderCommandRepository) {
        return new OrderCommandService(orderCommandRepository);
    }

    @Bean
    public OrderQueryService orderQueryService(OrderQueryRepository orderQueryRepository) {
        return new OrderQueryService(orderQueryRepository);
    }
}
