package com.shoppingcart.product.application.query.port.input;


import java.util.List;

import com.shoppingcart.product.domain.entity.Product;

public interface ListProducts {
    List<Product> execute();
}
