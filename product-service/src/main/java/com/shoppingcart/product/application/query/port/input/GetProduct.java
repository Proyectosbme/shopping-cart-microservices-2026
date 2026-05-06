package com.shoppingcart.product.application.query.port.input;

import com.shoppingcart.product.domain.entity.Product;
import com.shoppingcart.product.domain.vo.ProductId;

public interface GetProduct {
    Product execute(ProductId id);
}
