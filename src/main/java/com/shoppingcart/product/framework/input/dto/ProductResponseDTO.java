package com.shoppingcart.product.framework.input.dto;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Long id,
        String title,
        BigDecimal price,
        String description,
        String category,
        String image) {
}
