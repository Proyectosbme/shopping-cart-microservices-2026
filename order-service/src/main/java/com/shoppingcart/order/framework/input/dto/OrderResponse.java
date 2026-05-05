package com.shoppingcart.order.framework.input.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        Long customerId,
        String customerName,
        String customerEmail,
        String status,
        LocalDateTime createdAt,
        BigDecimal total,
        List<OrderDetailResponse> details
) {
    public record OrderDetailResponse(
            Long id,
            Long productId,
            String productName,
            int quantity,
            BigDecimal unitPrice,
            BigDecimal subtotal
    ) {}
}
