package com.shoppingcart.product.aplicacion.query.usecase;

import java.util.List;

import com.shoppingcart.product.aplicacion.query.port.output.ProductRepository;
import com.shoppingcart.product.domain.entity.Product;

public class ListProductsByCategoryUseCase {
    
      private final ProductRepository productRepository;

    public ListProductsByCategoryUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public  List<Product> execute(String category){
        return productRepository.findByCategory(category);
    }
}
