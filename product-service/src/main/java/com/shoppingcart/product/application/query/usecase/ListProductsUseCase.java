package com.shoppingcart.product.application.query.usecase;

import java.util.List;

import com.shoppingcart.product.application.query.port.output.ProductRepository;
import com.shoppingcart.product.domain.entity.Product;

public class ListProductsUseCase {

    private final ProductRepository productRepository;

    public ListProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public  List<Product> execute(){
        return productRepository.findAll();
    }
}
