package com.shoppingcart.auth.framework.input.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.auth.aplicacion.command.dto.LoginCommand;
import com.shoppingcart.auth.aplicacion.command.dto.RegisterCommand;
import com.shoppingcart.auth.aplicacion.command.port.input.LoginUserPort;
import com.shoppingcart.auth.aplicacion.command.port.input.RegisterUserPort;
import com.shoppingcart.auth.framework.input.dto.AuthResponseDTO;
import com.shoppingcart.auth.framework.input.dto.LoginRequestDTO;
import com.shoppingcart.auth.framework.input.dto.RegisterRequestDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUserPort registerUserPort;
    private final LoginUserPort loginUserPort;

    public AuthController(RegisterUserPort registerUserPort, LoginUserPort loginUserPort) {
        this.registerUserPort = registerUserPort;
        this.loginUserPort = loginUserPort;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
        String token = registerUserPort.execute(new RegisterCommand(request.email(), request.password()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponseDTO(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        String token = loginUserPort.execute(new LoginCommand(request.email(), request.password()));
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}
