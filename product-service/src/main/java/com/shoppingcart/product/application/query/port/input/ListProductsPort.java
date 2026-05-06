package com.shoppingcart.product.application.query.port.input;


import java.util.List;

import com.shoppingcart.product.domain.entity.Product;

public interface ListProductsPort {
    List<Product> execute();
}
