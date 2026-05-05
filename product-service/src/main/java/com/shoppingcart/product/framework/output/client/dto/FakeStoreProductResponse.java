package com.shoppingcart.product.framework.output.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FakeStoreProductResponse(
        Long id,
        String title,
        Double price,
        String description,
        String category,
        String image) {
}
