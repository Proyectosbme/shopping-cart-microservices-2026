package com.shoppingcart.order.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.shoppingcart.order.domain.exceptions.OrderAlreadyCancelledException;
import com.shoppingcart.order.domain.vo.OrderId;
import com.shoppingcart.order.domain.vo.OrderStatus;

public class Order {

    private final OrderId id;
    private final Customer customer;
    private final List<OrderDetail> details;
    private OrderStatus status;
    private final LocalDateTime createdAt;

    private Order(OrderId id, Customer customer,
            List<OrderDetail> details, OrderStatus status,
            LocalDateTime createdAt) {
        this.id = id;
        this.customer = customer;
        this.details = new ArrayList<>(details);
        this.status = status;
        this.createdAt = createdAt;
    }

    // Factory method — nueva orden
    public static Order create(Customer customer, List<OrderDetail> details) {
        if (details == null || details.isEmpty())
            throw new IllegalArgumentException("La orden debe tener al menos un detalle");
        return new Order(
                new OrderId(null),
                customer,
                details,
                OrderStatus.PENDING,
                LocalDateTime.now());
    }

    // Factory method — reconstruir desde persistencia
    public static Order reconstitute(Long id, Customer customer,
            List<OrderDetail> details,
            OrderStatus status, LocalDateTime createdAt) {
        return new Order(new OrderId(id), customer, details, status, createdAt);
    }

    // Lógica de negocio
    public void confirm() {
        if (this.status != OrderStatus.PENDING)
            throw new IllegalStateException("Solo las órdenes en estado PENDIENTE pueden confirmarse");
        this.status = OrderStatus.CONFIRMED;
    }

    public void cancel() {
        if (this.status == OrderStatus.CANCELLED)
            throw new OrderAlreadyCancelledException(this.id);
        if (this.status == OrderStatus.PAID)
            throw new IllegalStateException("No se puede cancelar una orden que ya fue pagada");
        this.status = OrderStatus.CANCELLED;
    }

    public void markAsPaid() {
        if (this.status != OrderStatus.CONFIRMED)
            throw new IllegalStateException("Solo las órdenes en estado CONFIRMADO pueden marcarse como pagadas");
        this.status = OrderStatus.PAID;
    }

    public BigDecimal total() {
        return details.stream()
                .map(OrderDetail::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public OrderId getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderDetail> getDetails() {
        return Collections.unmodifiableList(details);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
