package com.shoppingcart.product.domain.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String mensaje) {
        super(mensaje);
    }

    // "Product con ID 99 no encontrado"
    public ProductNotFoundException(Long id) {
        super("Product con ID " + id + " no encontrado");
    }
}
