package com.shoppingcart.product.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shoppingcart.product.application.query.port.input.GetProductPort;
import com.shoppingcart.product.application.query.port.input.ListProductsPort;
import com.shoppingcart.product.application.query.port.input.ListProductsByCategoryPort;
import com.shoppingcart.product.application.query.port.output.ProductRepository;
import com.shoppingcart.product.application.query.service.ProductService;

@Configuration
public class ApplicationConfig {

    private final ProductRepository productRepository;

    public ApplicationConfig(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Bean
    public ListProductsPort listProducts() {
        return new ProductService(productRepository);
    }

    @Bean
    public GetProductPort getProduct() {
        return new ProductService(productRepository);
    }

    @Bean
    public ListProductsByCategoryPort listProductsByCategory() {
        return new ProductService(productRepository);
    }
}
