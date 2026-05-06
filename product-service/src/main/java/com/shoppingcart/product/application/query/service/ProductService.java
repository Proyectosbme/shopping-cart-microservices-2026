package com.shoppingcart.product.application.query.service;



import com.shoppingcart.product.application.query.port.input.GetProduct;
import com.shoppingcart.product.application.query.port.input.ListProductsByCategory;
import com.shoppingcart.product.application.query.port.input.ListProducts;
import com.shoppingcart.product.application.query.port.output.ProductRepository;
import com.shoppingcart.product.application.query.usecase.GetProductUseCase;
import com.shoppingcart.product.application.query.usecase.ListProductsByCategoryUseCase;
import com.shoppingcart.product.application.query.usecase.ListProductsUseCase;
import com.shoppingcart.product.domain.entity.Product;
import com.shoppingcart.product.domain.vo.ProductId;

import java.util.List;

public class ProductService implements
        ListProducts,
        GetProduct,
        ListProductsByCategory {

    private final GetProductUseCase getProductUseCase;
    private final ListProductsUseCase listProductsUseCase;
    private final ListProductsByCategoryUseCase listProductsByCategory;

    

    public ProductService(ProductRepository productRepository) {
        this.getProductUseCase = new GetProductUseCase(productRepository);
        this.listProductsUseCase = new ListProductsUseCase(productRepository);
        this.listProductsByCategory = new ListProductsByCategoryUseCase(productRepository);
    }



    @Override
    public List<Product> execute(String category) {
       return this.listProductsByCategory.execute(category);
    }



    @Override
    public Product execute(ProductId id) {
       return this.getProductUseCase.execute(id);
    }



    @Override
    public List<Product> execute() {
       return this.listProductsUseCase.execute();
    }


}
