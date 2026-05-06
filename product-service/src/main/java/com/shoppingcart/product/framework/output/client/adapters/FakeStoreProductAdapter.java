package com.shoppingcart.product.framework.output.client.adapters;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.shoppingcart.product.application.query.port.output.ProductRepository;
import com.shoppingcart.product.domain.entity.Product;
import com.shoppingcart.product.domain.vo.ProductId;
import com.shoppingcart.product.framework.output.client.FakeStoreClient;
import com.shoppingcart.product.framework.output.mapper.FakeStoreProductMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FakeStoreProductAdapter implements ProductRepository {

    private final FakeStoreClient fakeStoreClient;
    private final FakeStoreProductMapper mapper;

    @Override
    public List<Product> findAll() {
        return fakeStoreClient.getAllProducts().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return Optional.ofNullable(fakeStoreClient.getProductById(id.value()))
                .map(mapper::toDomain);
    }

    @Override
    public List<Product> findByCategory(String category) {
        return fakeStoreClient.getProductsByCategory(category).stream()
                .map(mapper::toDomain)
                .toList();
    }
}
