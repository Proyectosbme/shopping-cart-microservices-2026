package com.shoppingcart.product.domain.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.shoppingcart.product.domain.exceptions.ProductValidationException;

public record Money(BigDecimal amount) {

    public Money {
        if (amount == null)
            throw new ProductValidationException("price", "no puede ser nulo");
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new ProductValidationException("price", "no puede ser negativo");
        amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public static Money of(double value) {
        return new Money(BigDecimal.valueOf(value));
    }

    public boolean isGreaterThan(Money other) {
        return this.amount.compareTo(other.amount) > 0;
    }
}
