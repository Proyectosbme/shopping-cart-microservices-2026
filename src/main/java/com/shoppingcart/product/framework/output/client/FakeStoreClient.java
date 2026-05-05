package com.shoppingcart.product.framework.output.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shoppingcart.product.framework.output.client.dto.FakeStoreProductResponse;

@FeignClient(name = "fakestore-client", url = "${fakestore.base-url}")
public interface FakeStoreClient {

    @GetMapping("/products")
    List<FakeStoreProductResponse> getAllProducts();

    @GetMapping("/products/{id}")
    FakeStoreProductResponse getProductById(@PathVariable("id") Long id);

    @GetMapping("/products/category/{category}")
    List<FakeStoreProductResponse> getProductsByCategory(@PathVariable("category") String category);
}
