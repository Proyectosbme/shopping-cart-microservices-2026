package com.shoppingcart.product.aplicacion.query.port.input;


import java.util.List;

import com.shoppingcart.product.domain.entity.Product;

public interface ListProducts {
    List<Product> execute();
}
