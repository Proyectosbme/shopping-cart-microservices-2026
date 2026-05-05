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

    private static final String BAD_REQUEST = "Bad Request";

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound(OrderNotFoundException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(
                404, "Not Found", "ORDER_NOT_FOUND",
                ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(OrderAlreadyCancelledException.class)
    public ResponseEntity<ErrorResponseDTO> handleAlreadyCancelled(OrderAlreadyCancelledException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(
                409, "Conflict", "ORDER_ALREADY_CANCELLED",
                ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidProduct(InvalidProductException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(
                400, BAD_REQUEST, "INVALID_PRODUCT",
                ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(PriceMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> handlePriceMismatch(PriceMismatchException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(
                409, "Conflict", "PRICE_MISMATCH",
                ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgument(IllegalArgumentException ex,
            HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(
                400, BAD_REQUEST, "INVALID_ARGUMENT",
                ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        List<String> details = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .toList();
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(
                400, BAD_REQUEST, "VALIDATION_ERROR",
                "Request fields failed validation.",
                request.getRequestURI(), details));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotReadable(HttpMessageNotReadableException ex,
            HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(
                400, BAD_REQUEST, "INVALID_JSON_FORMAT",
                "The request body has an invalid format or an unsupported value.",
                request.getRequestURI()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneric(Exception ex, HttpServletRequest request) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponseDTO(
                500, "Internal Server Error", "INTERNAL_ERROR",
                "An unexpected error occurred.",
                request.getRequestURI()));
    }
}
