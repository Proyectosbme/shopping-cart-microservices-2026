package com.shoppingcart.order.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.shoppingcart.order.application.command.port.output.OrderCommandRepository;
import com.shoppingcart.order.application.command.service.OrderCommandService;
import com.shoppingcart.order.application.query.port.output.OrderQueryRepository;
import com.shoppingcart.order.application.query.service.OrderQueryService;
import com.shoppingcart.order.framework.output.client.adapters.ProductClientAdapter;

@Configuration
public class ApplicationConfig {

    @Value("${product.service.url}")
    private String productServiceUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ProductClientAdapter productClientAdapter(RestTemplate restTemplate) {
        return new ProductClientAdapter(restTemplate, productServiceUrl);
    }

    @Bean
    public OrderCommandService orderCommandService(OrderCommandRepository orderCommandRepository,
            ProductClientAdapter productClientAdapter) {
        return new OrderCommandService(orderCommandRepository, productClientAdapter);
    }

    @Bean
    public OrderQueryService orderQueryService(OrderQueryRepository orderQueryRepository) {
        return new OrderQueryService(orderQueryRepository);
    }
}
