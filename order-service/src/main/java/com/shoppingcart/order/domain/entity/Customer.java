package com.shoppingcart.order.domain.entity;

public class Customer {

    private final Long id;
    private final String name;
    private final String email;

    private Customer(Long id, String name, String email) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío");
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("El email del cliente no puede estar vacío");
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Factory method — reconstruir desde persistencia
    public static Customer reconstitute(Long id, String name, String email) {
        return new Customer(id, name, email);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
