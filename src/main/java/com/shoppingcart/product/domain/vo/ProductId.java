package com.shoppingcart.product.domain.vo;

import com.shoppingcart.product.domain.exceptions.ProductValidationException;

public record ProductId(Long value) {

    public ProductId {
        if (value == null || value <= 0)
            throw new ProductValidationException("id", "debe ser un número positivo");
    }
}