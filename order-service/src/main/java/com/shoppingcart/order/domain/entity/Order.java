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

    public static Order create(Customer customer, List<OrderDetail> details) {
        if (details == null || details.isEmpty())
            throw new IllegalArgumentException("Order must have at least one detail");
        return new Order(new OrderId(null), customer, details, OrderStatus.PENDING, LocalDateTime.now());
    }

    public static Order reconstitute(Long id, Customer customer,
            List<OrderDetail> details, OrderStatus status, LocalDateTime createdAt) {
        return new Order(new OrderId(id), customer, details, status, createdAt);
    }

    public void confirm() {
        if (this.status != OrderStatus.PENDING)
            throw new IllegalStateException("Only PENDING orders can be confirmed");
        this.status = OrderStatus.CONFIRMED;
    }

    public void cancel() {
        if (this.status == OrderStatus.CANCELLED)
            throw new OrderAlreadyCancelledException(this.id);
        if (this.status == OrderStatus.PAID)
            throw new IllegalStateException("A paid order cannot be cancelled");
        this.status = OrderStatus.CANCELLED;
    }

    public void markAsPaid() {
        if (this.status != OrderStatus.PENDING && this.status != OrderStatus.CONFIRMED)
            throw new IllegalStateException("Only PENDING or CONFIRMED orders can be marked as paid");
        this.status = OrderStatus.PAID;
    }

    public void revertToPending() {
        if (this.status != OrderStatus.PAID)
            throw new IllegalStateException("Only PAID orders can be reverted to pending");
        this.status = OrderStatus.PENDING;
    }

    public BigDecimal total() {
        return details.stream()
                .map(OrderDetail::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public OrderId getId() { return id; }
    public Customer getCustomer() { return customer; }
    public List<OrderDetail> getDetails() { return Collections.unmodifiableList(details); }
    public OrderStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
