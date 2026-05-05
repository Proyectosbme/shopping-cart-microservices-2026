package com.shoppingcart.product.domain.exceptions;

public class ProductValidationException extends RuntimeException {

    public ProductValidationException(String mensaje) {
        super(mensaje);
    }

    // "Validación fallida en 'price': debe ser mayor a cero"
    public ProductValidationException(String campo, String mensaje) {
        super("Validación fallida en '" + campo + "': " + mensaje);
    }
}
