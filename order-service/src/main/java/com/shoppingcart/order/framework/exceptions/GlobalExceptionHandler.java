package com.shoppingcart.order.framework.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shoppingcart.order.domain.exceptions.InvalidProductException;
import com.shoppingcart.order.domain.exceptions.OrderAlreadyCancelledException;
import com.shoppingcart.order.domain.exceptions.OrderNotFoundException;
import com.shoppingcart.order.domain.exceptions.PriceMismatchException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound(OrderNotFoundException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(
                404, "Recurso No Encontrado", "RECURSO_NO_ENCONTRADO",
                ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(OrderAlreadyCancelledException.class)
    public ResponseEntity<ErrorResponseDTO> handleAlreadyCancelled(OrderAlreadyCancelledException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(
                409, "Conflicto", "ORDEN_YA_CANCELADA",
                ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidProduct(InvalidProductException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(
                400, "Producto Inválido", "PRODUCTO_INVALIDO",
                ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(PriceMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> handlePriceMismatch(PriceMismatchException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(
                409, "Precio No Coincide", "PRECIO_NO_COINCIDE",
                ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgument(IllegalArgumentException ex,
            HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(
                400, "Datos Inválidos", "DATOS_INVALIDOS",
                ex.getMessage(), request.getRequestURI()));
    }

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
