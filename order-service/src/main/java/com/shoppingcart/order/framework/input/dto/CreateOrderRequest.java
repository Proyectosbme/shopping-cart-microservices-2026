package com.shoppingcart.order.framework.input.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateOrderRequest(

        @NotNull(message = "El id del cliente es obligatorio")
        Long customerId,

        @NotBlank(message = "El nombre del cliente es obligatorio")
        String customerName,

        @NotBlank(message = "El email del cliente es obligatorio")
        @Email(message = "El email del cliente no es válido")
        String customerEmail,

        @NotEmpty(message = "La orden debe tener al menos un detalle")
        @Valid
        List<OrderDetailRequest> details

) {
    public record OrderDetailRequest(

            @NotNull(message = "El id del producto es obligatorio")
            Long productId,

            @NotBlank(message = "El nombre del producto es obligatorio")
            String productName,

            @Positive(message = "La cantidad debe ser mayor a cero")
            int quantity,

            @Positive(message = "El precio unitario debe ser mayor a cero")
            double unitPrice
    ) {}
}
