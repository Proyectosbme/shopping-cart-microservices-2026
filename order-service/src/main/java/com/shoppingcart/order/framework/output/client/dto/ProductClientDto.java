package com.shoppingcart.order.framework.output.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductClientDto(
    Long id,
    String title,
    Double price,
    String description,
    String category,
    @JsonProperty("image")
    String image
) {
}
