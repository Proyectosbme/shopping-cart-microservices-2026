package com.shoppingcart.auth.application.command.dto;

public record LoginCommand(String email, String password) {
}