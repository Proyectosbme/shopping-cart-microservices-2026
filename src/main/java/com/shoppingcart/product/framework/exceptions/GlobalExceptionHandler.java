package com.shoppingcart.product.framework.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shoppingcart.product.domain.exceptions.ProductNotFoundException;
import com.shoppingcart.product.domain.exceptions.ProductValidationException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductValidationException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidation(ProductValidationException ex,
            HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(
                400, "Datos Inválidos", "DATOS_INVALIDOS",
                ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound(ProductNotFoundException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(
                404, "Recurso No Encontrado", "RECURSO_NO_ENCONTRADO",
                ex.getMessage(), request.getRequestURI()));
    }

    // Errores de validación en @RequestBody con anotaciones @NotBlank, @Min, etc.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        List<String> details = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .toList();
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(
                400, "Datos Inválidos", "DATOS_INVALIDOS",
                "Los campos enviados no cumplen con las validaciones requeridas.",
                request.getRequestURI(), details));
    }

    // JSON malformado o tipo de dato incorrecto
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotReadable(HttpMessageNotReadableException ex,
            HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(
                400, "Error de Formato JSON", "ERROR_FORMATO_DATOS",
                "El JSON enviado tiene un formato incorrecto o un tipo de dato inválido.",
                request.getRequestURI()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneric(Exception ex, HttpServletRequest request) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponseDTO(
                500, "Error Interno", "ERROR_INTERNO",
                "Ocurrió un error inesperado en el servidor.",
                request.getRequestURI()));
    }
}
