package com.shoppingcart.auth.domain.entity;

import com.shoppingcart.auth.domain.vo.Email;
import com.shoppingcart.auth.domain.vo.Password;
import com.shoppingcart.auth.domain.vo.Role;

public class User {

    private Long id;
    private Email email;
    private Password password;
    private Role role;

    // Constructor for creating a new user (plain text password — validated)
    public User(String email, String password) {
        this.email = new Email(email);
        this.password = Password.ofPlainText(password);
        this.role = Role.USER;
    }

    // Constructor for reconstructing from the database (hashed password — no length validation)
    public User(Long id, String email, String password, Role role) {
        this.id = id;
        this.email = new Email(email);
        this.password = Password.ofHash(password);
        this.role = role;
    }

    // Business rule: promote user to admin
    public void promoteToAdmin() {
        this.role = Role.ADMIN;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email.value();
    }

    public String getPassword() {
        return password.value();
    }

    public Role getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }
}