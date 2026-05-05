package com.shoppingcart.payment.domain.entity;

import java.math.BigDecimal;

public class OrderDetail {

    private final Long id;
    private final Long productId;
    private final String productName;
    private final Integer quantity;
    private final BigDecimal unitPrice;

    private OrderDetail(Long id, Long productId, String productName, Integer quantity, BigDecimal unitPrice) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public static OrderDetail reconstitute(Long id, Long productId, String productName,
            Integer quantity, BigDecimal unitPrice) {
        return new OrderDetail(id, productId, productName, quantity, unitPrice);
    }

    public Long getId() { return id; }
    public Long getProductId() { return productId; }
    public String getProductName() { return productName; }
    public Integer getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
}
