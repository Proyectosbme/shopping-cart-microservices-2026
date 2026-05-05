package com.shoppingcart.order.domain.entity;

public class Customer {

    private final Long id;
    private final String name;
    private final String email;

    private Customer(Long id, String name, String email) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Customer name cannot be blank");
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Customer email cannot be blank");
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static Customer reconstitute(Long id, String name, String email) {
        return new Customer(id, name, email);
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
