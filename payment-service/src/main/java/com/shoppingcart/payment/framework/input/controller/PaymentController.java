package com.shoppingcart.payment.framework.input.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.payment.application.command.port.input.ProcessPayment;
import com.shoppingcart.payment.application.command.port.input.RefundPayment;
import com.shoppingcart.payment.application.query.port.input.GetPayment;
import com.shoppingcart.payment.application.query.port.input.GetPaymentsByOrder;
import com.shoppingcart.payment.framework.input.dto.PaymentResponse;
import com.shoppingcart.payment.framework.input.dto.ProcessPaymentRequest;
import com.shoppingcart.payment.framework.input.mapper.PaymentHttpMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final ProcessPayment processPayment;
    private final RefundPayment refundPayment;
    private final GetPayment getPayment;
    private final GetPaymentsByOrder getPaymentsByOrder;

    public PaymentController(ProcessPayment processPayment, RefundPayment refundPayment,
            GetPayment getPayment, GetPaymentsByOrder getPaymentsByOrder) {
        this.processPayment = processPayment;
        this.refundPayment = refundPayment;
        this.getPayment = getPayment;
        this.getPaymentsByOrder = getPaymentsByOrder;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> process(@Valid @RequestBody ProcessPaymentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PaymentHttpMapper.toResponse(processPayment.process(PaymentHttpMapper.toCommand(request))));
    }

    @PatchMapping("/{id}/refund")
    public ResponseEntity<PaymentResponse> refund(@PathVariable Long id) {
        return ResponseEntity.ok(PaymentHttpMapper.toResponse(refundPayment.refund(id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(PaymentHttpMapper.toResponse(getPayment.getById(id)));
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getByOrder(@RequestParam Long orderId) {
        return ResponseEntity.ok(getPaymentsByOrder.getByOrderId(orderId)
                .stream().map(PaymentHttpMapper::toResponse).toList());
    }
}
