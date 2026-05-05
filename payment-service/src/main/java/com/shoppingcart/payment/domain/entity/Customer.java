package com.shoppingcart.payment.domain.entity;

public class Customer {

    private final Long id;
    private final String name;
    private final String email;

    private Customer(Long id, String name, String email) {
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
