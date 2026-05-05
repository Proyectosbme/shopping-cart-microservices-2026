package com.shoppingcart.product.domain.entity;


import com.shoppingcart.product.domain.vo.Category;
import com.shoppingcart.product.domain.vo.Money;
import com.shoppingcart.product.domain.vo.ProductId;
import com.shoppingcart.product.domain.vo.ProductImage;

public class Product {

    private final ProductId id;
    private final String title;
    private final Money price;
    private final String description;
    private final Category category;
    private final ProductImage image;

    private Product(ProductId id, String title, Money price,
            String description, Category category, ProductImage image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
    }

    public static Product reconstituir(Long id, String title, double price,
            String description, String category, String imageUrl) {
        return new Product(
                new ProductId(id),
                title,
                Money.of(price),
                description,
                new Category(category),
                new ProductImage(imageUrl));
    }

    // Getters
    public ProductId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Money getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public ProductImage getImage() {
        return image;
    }
}
