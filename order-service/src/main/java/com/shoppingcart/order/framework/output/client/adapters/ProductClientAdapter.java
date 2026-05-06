package com.shoppingcart.order.framework.output.client.adapters;

import java.util.Optional;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.shoppingcart.order.application.command.port.output.ProductValidationPort;
import com.shoppingcart.order.framework.output.client.dto.ProductClientDto;

public class ProductClientAdapter implements ProductValidationPort {

    private final RestTemplate restTemplate;
    private final String productServiceUrl;

    public ProductClientAdapter(RestTemplate restTemplate, String productServiceUrl) {
        this.restTemplate = restTemplate;
        this.productServiceUrl = productServiceUrl;
    }

    public Optional<ProductClientDto> getProductById(Long productId) {
        try {
            String url = productServiceUrl + "/" + productId;
            ProductClientDto product = restTemplate.getForObject(url, ProductClientDto.class);
            return Optional.ofNullable(product);
        } catch (RestClientException e) {
            throw new RuntimeException("Error fetching product with ID: " + productId, e);
        }
    }

    public Double getProductPrice(Long productId) {
        return getProductById(productId)
                .map(ProductClientDto::price)
                .orElseThrow(() -> new RuntimeException(
                        "Product with ID " + productId + " not found in Product Service"));
    }
}
