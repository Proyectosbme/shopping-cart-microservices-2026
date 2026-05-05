package com.shoppingcart.payment.framework.output.client.dto;

import java.math.BigDecimal;

public record OrderClientDto(
        Long id,
        Long customerId,
        String status,
        BigDecimal total
) {}
