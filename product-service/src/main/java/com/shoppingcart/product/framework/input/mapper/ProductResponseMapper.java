package com.shoppingcart.product.framework.input.mapper;

import org.springframework.stereotype.Component;

import com.shoppingcart.product.domain.entity.Product;
import com.shoppingcart.product.framework.input.dto.ProductResponseDTO;

@Component
public class ProductResponseMapper {

    public ProductResponseDTO toDTO(Product product) {
        return new ProductResponseDTO(
                product.getId().value(),
                product.getTitle(),
                product.getPrice().amount(),
                product.getDescription(),
                product.getCategory().name(),
                product.getImage().url());
    }
}
