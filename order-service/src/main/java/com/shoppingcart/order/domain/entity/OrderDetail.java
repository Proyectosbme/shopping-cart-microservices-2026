package com.shoppingcart.order.domain.entity;

import java.math.BigDecimal;

import com.shoppingcart.order.domain.vo.Quantity;

public class OrderDetail {

    private final Long id;
    private final Long productId;
    private final String productName;
    private final Quantity quantity;
    private final BigDecimal unitPrice;

    private OrderDetail(Long id, Long productId, String productName,
            Quantity quantity, BigDecimal unitPrice) {
        if (productId == null)
            throw new IllegalArgumentException("El id del producto no puede ser nulo");
        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("El precio unitario no puede ser negativo");
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    // Factory method — nuevo detalle de orden
    public static OrderDetail create(Long productId, String productName,
            Quantity quantity, BigDecimal unitPrice) {
        return new OrderDetail(null, productId, productName, quantity, unitPrice);
    }

    // Factory method — reconstruir desde persistencia
    public static OrderDetail reconstitute(Long id, Long productId, String productName,
            Quantity quantity, BigDecimal unitPrice) {
        return new OrderDetail(id, productId, productName, quantity, unitPrice);
    }

    public BigDecimal subtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity.value()));
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}
