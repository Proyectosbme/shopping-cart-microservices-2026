package com.shoppingcart.product.aplicacion.query.usecase;

import com.shoppingcart.product.aplicacion.query.port.output.ProductRepository;
import com.shoppingcart.product.domain.entity.Product;
import com.shoppingcart.product.domain.exceptions.ProductNotFoundException;
import com.shoppingcart.product.domain.vo.ProductId;

public class GetProductUseCase {

    private final ProductRepository productRepository;

    public GetProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(ProductId id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id.value()));
    }

    
}
