package com.shoppingcart.product.aplicacion.query.port.output;

import java.util.List;
import java.util.Optional;

import com.shoppingcart.product.domain.entity.Product;
import com.shoppingcart.product.domain.vo.ProductId;

public interface ProductRepository {
    List<Product> findAll();

    Optional<Product> findById(ProductId id);

    List<Product> findByCategory(String category);
}