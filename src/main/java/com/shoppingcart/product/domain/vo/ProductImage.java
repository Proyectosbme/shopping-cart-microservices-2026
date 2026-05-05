package com.shoppingcart.product.domain.vo;

import com.shoppingcart.product.domain.exceptions.ProductValidationException;

public record ProductImage(String url) {

    public ProductImage {
        if (url == null || url.isBlank())
            throw new ProductValidationException("image", "la URL no puede estar vacía");
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            throw new ProductValidationException("image", "la URL debe comenzar con http:// o https://");
    }
}
