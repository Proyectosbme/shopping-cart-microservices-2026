package com.shoppingcart.product.framework.input.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.product.application.query.port.input.GetProduct;
import com.shoppingcart.product.application.query.port.input.ListProducts;
import com.shoppingcart.product.application.query.port.input.ListProductsByCategory;
import com.shoppingcart.product.domain.vo.ProductId;
import com.shoppingcart.product.framework.input.dto.ProductResponseDTO;
import com.shoppingcart.product.framework.input.mapper.ProductResponseMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ListProducts listProducts;
    private final GetProduct getProduct;
    private final ListProductsByCategory listProductsByCategory;
    private final ProductResponseMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        return ResponseEntity.ok(
                listProducts.execute().stream()
                        .map(mapper::toDTO)
                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDTO(getProduct.execute(new ProductId(id))));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponseDTO>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(
                listProductsByCategory.execute(category).stream()
                        .map(mapper::toDTO)
                        .toList());
    }
}
