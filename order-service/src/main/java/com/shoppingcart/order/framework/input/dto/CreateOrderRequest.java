package com.shoppingcart.order.framework.input.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateOrderRequest(

        @NotNull(message = "Customer id is required")
        Long customerId,

        @NotBlank(message = "Customer name is required")
        String customerName,

        @NotBlank(message = "Customer email is required")
        @Email(message = "Customer email is not valid")
        String customerEmail,

        @NotEmpty(message = "Order must have at least one detail")
        @Valid
        List<OrderDetailRequest> details

) {
    public record OrderDetailRequest(

            @NotNull(message = "Product id is required")
            Long productId,

            @NotBlank(message = "Product name is required")
            String productName,

            @Positive(message = "Quantity must be greater than zero")
            int quantity,

            @Positive(message = "Unit price must be greater than zero")
            double unitPrice
    ) {}
}
