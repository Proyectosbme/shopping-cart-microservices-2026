package com.shoppingcart.product.domain.vo;

import com.shoppingcart.product.domain.exceptions.ProductValidationException;

public record Category(String name) {

    public Category {
        if (name == null || name.isBlank())
            throw new ProductValidationException("category", "cannot be blank");
        name = name.trim().toLowerCase();
    }
}
