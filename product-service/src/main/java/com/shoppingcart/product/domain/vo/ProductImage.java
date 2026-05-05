package com.shoppingcart.product.domain.vo;

import com.shoppingcart.product.domain.exceptions.ProductValidationException;

public record ProductImage(String url) {

    public ProductImage {
        if (url == null || url.isBlank())
            throw new ProductValidationException("image", "URL cannot be blank");
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            throw new ProductValidationException("image", "URL must start with http:// or https://");
    }
}
