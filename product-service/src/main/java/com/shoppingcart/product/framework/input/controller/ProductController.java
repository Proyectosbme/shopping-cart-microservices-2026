package com.shoppingcart.product.framework.input.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.product.application.query.port.input.GetProductPort;
import com.shoppingcart.product.application.query.port.input.ListProductsPort;
import com.shoppingcart.product.application.query.port.input.ListProductsByCategoryPort;
import com.shoppingcart.product.domain.vo.ProductId;
import com.shoppingcart.product.framework.input.dto.ProductResponseDTO;
import com.shoppingcart.product.framework.input.mapper.ProductResponseMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Products", description = "Product catalog")
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ListProductsPort listProducts;
    private final GetProductPort getProduct;
    private final ListProductsByCategoryPort listProductsByCategory;
    private final ProductResponseMapper mapper;

    @Operation(summary = "List all products")
    @ApiResponse(responseCode = "200", description = "List of products")
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        return ResponseEntity.ok(
                listProducts.execute().stream()
                        .map(mapper::toDTO)
                        .toList());
    }

    @Operation(summary = "Get product by ID")
    @ApiResponse(responseCode = "200", description = "Product found")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDTO(getProduct.execute(new ProductId(id))));
    }

    @Operation(summary = "List products by category")
    @ApiResponse(responseCode = "200", description = "List of products in the category")
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponseDTO>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(
                listProductsByCategory.execute(category).stream()
                        .map(mapper::toDTO)
                        .toList());
    }
}
