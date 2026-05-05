package com.shoppingcart.order.framework.input.controller;

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

import com.shoppingcart.order.aplicacion.command.port.input.CancelOrder;
import com.shoppingcart.order.aplicacion.command.port.input.CreateOrder;
import com.shoppingcart.order.aplicacion.command.port.input.MarkOrderAsPaid;
import com.shoppingcart.order.aplicacion.command.port.input.RevertOrderToPending;
import com.shoppingcart.order.aplicacion.query.port.input.GetOrder;
import com.shoppingcart.order.aplicacion.query.port.input.ListOrdersByCustomer;
import com.shoppingcart.order.framework.input.dto.CreateOrderRequest;
import com.shoppingcart.order.framework.input.dto.OrderResponse;
import com.shoppingcart.order.framework.input.mapper.OrderHttpMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final CreateOrder createOrder;
    private final CancelOrder cancelOrder;
    private final MarkOrderAsPaid markOrderAsPaid;
    private final RevertOrderToPending revertOrderToPending;
    private final GetOrder getOrder;
    private final ListOrdersByCustomer listOrdersByCustomer;

    public OrderController(CreateOrder createOrder, CancelOrder cancelOrder,
            MarkOrderAsPaid markOrderAsPaid, RevertOrderToPending revertOrderToPending,
            GetOrder getOrder, ListOrdersByCustomer listOrdersByCustomer) {
        this.createOrder = createOrder;
        this.cancelOrder = cancelOrder;
        this.markOrderAsPaid = markOrderAsPaid;
        this.revertOrderToPending = revertOrderToPending;
        this.getOrder = getOrder;
        this.listOrdersByCustomer = listOrdersByCustomer;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody CreateOrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(OrderHttpMapper.toResponse(createOrder.create(OrderHttpMapper.toCommand(request))));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<OrderResponse> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(OrderHttpMapper.toResponse(cancelOrder.cancel(id)));
    }

    @PatchMapping("/{id}/pay")
    public ResponseEntity<OrderResponse> pay(@PathVariable Long id) {
        return ResponseEntity.ok(OrderHttpMapper.toResponse(markOrderAsPaid.markAsPaid(id)));
    }

    @PatchMapping("/{id}/revert-payment")
    public ResponseEntity<OrderResponse> revertPayment(@PathVariable Long id) {
        return ResponseEntity.ok(OrderHttpMapper.toResponse(revertOrderToPending.revertToPending(id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(OrderHttpMapper.toResponse(getOrder.getById(id)));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> listByCustomer(@RequestParam Long customerId) {
        return ResponseEntity.ok(listOrdersByCustomer.listByCustomer(customerId)
                .stream().map(OrderHttpMapper::toResponse).toList());
    }
}
