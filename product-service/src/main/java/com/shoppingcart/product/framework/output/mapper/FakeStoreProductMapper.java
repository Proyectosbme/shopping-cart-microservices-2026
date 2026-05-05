package com.shoppingcart.product.framework.output.mapper;

import org.springframework.stereotype.Component;

import com.shoppingcart.product.domain.entity.Product;
import com.shoppingcart.product.framework.output.client.dto.FakeStoreProductResponse;

@Component
public class FakeStoreProductMapper {

    public Product toDomain(FakeStoreProductResponse response) {
        return Product.reconstitute(
                response.id(),
                response.title(),
                response.price(),
                response.description(),
                response.category(),
                response.image());
    }
}
