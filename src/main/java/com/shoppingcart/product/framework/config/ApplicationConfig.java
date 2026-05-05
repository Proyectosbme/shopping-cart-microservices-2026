package com.shoppingcart.product.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shoppingcart.product.aplicacion.query.port.input.GetProduct;
import com.shoppingcart.product.aplicacion.query.port.input.ListProducts;
import com.shoppingcart.product.aplicacion.query.port.input.ListProductsByCategory;
import com.shoppingcart.product.aplicacion.query.port.output.ProductRepository;
import com.shoppingcart.product.aplicacion.query.service.ProductService;

@Configuration
public class ApplicationConfig {

    private final ProductRepository productRepository;

    public ApplicationConfig(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Bean
    public ListProducts listProducts() {
        return new ProductService(productRepository);
    }

    @Bean
    public GetProduct getProduct() {
        return new ProductService(productRepository);
    }

    @Bean
    public ListProductsByCategory listProductsByCategory() {
        return new ProductService(productRepository);
    }
}
