package com.shoppingcart.payment.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.shoppingcart.payment.aplicacion.command.service.PaymentCommandService;
import com.shoppingcart.payment.aplicacion.query.service.PaymentQueryService;
import com.shoppingcart.payment.framework.output.client.adapters.OrderClientAdapter;
import com.shoppingcart.payment.framework.output.persistence.adapters.PaymentPersistenceAdapter;
import com.shoppingcart.payment.framework.output.persistence.repository.PaymentJpaRepository;

@Configuration
public class ApplicationConfig {

    @Value("${order.service.url}")
    private String orderServiceUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public OrderClientAdapter orderClientAdapter(RestTemplate restTemplate) {
        return new OrderClientAdapter(restTemplate, orderServiceUrl);
    }

    @Bean
    public PaymentPersistenceAdapter paymentPersistenceAdapter(PaymentJpaRepository jpaRepository) {
        return new PaymentPersistenceAdapter(jpaRepository);
    }

    @Bean
    public PaymentCommandService paymentCommandService(PaymentPersistenceAdapter adapter,
            OrderClientAdapter orderClientAdapter) {
        return new PaymentCommandService(adapter, orderClientAdapter, orderClientAdapter);
    }

    @Bean
    public PaymentQueryService paymentQueryService(PaymentPersistenceAdapter adapter) {
        return new PaymentQueryService(adapter);
    }
}
