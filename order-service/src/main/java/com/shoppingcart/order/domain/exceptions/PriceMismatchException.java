package com.shoppingcart.order.domain.exceptions;

public class PriceMismatchException extends RuntimeException {

    public PriceMismatchException(Long productId, Double expectedPrice, Double actualPrice) {
        super("El precio del producto " + productId + " no coincide. Esperado: " + expectedPrice 
              + ", Actual: " + actualPrice);
    }

    public PriceMismatchException(String mensaje) {
        super(mensaje);
    }
}
