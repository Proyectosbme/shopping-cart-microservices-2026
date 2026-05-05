package com.shoppingcart.order.domain.exceptions;

public class InvalidProductException extends RuntimeException {

    public InvalidProductException(String mensaje) {
        super(mensaje);
    }

    public InvalidProductException(Long productId) {
        super("Producto con ID " + productId + " no existe o no está disponible");
    }
}
