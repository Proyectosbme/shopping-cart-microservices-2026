package com.shoppingcart.payment.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shoppingcart.payment.aplicacion.command.service.PaymentCommandService;
import com.shoppingcart.payment.aplicacion.query.service.PaymentQueryService;
import com.shoppingcart.payment.framework.output.persistence.adapters.PaymentPersistenceAdapter;
import com.shoppingcart.payment.framework.output.persistence.repository.PaymentJpaRepository;

@Configuration
public class ApplicationConfig {

    @Bean
    public PaymentPersistenceAdapter paymentPersistenceAdapter(PaymentJpaRepository jpaRepository) {
        return new PaymentPersistenceAdapter(jpaRepository);
    }

    @Bean
    public PaymentCommandService paymentCommandService(PaymentPersistenceAdapter adapter) {
        return new PaymentCommandService(adapter);
    }

    @Bean
    public PaymentQueryService paymentQueryService(PaymentPersistenceAdapter adapter) {
        return new PaymentQueryService(adapter);
    }
}
