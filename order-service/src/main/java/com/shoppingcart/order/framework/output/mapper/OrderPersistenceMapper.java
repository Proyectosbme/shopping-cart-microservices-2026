package com.shoppingcart.order.framework.output.mapper;

import java.util.List;

import com.shoppingcart.order.domain.entity.Customer;
import com.shoppingcart.order.domain.entity.Order;
import com.shoppingcart.order.domain.entity.OrderDetail;
import com.shoppingcart.order.domain.vo.Quantity;
import com.shoppingcart.order.framework.output.persistence.entidad.OrderDetailJpaEntity;
import com.shoppingcart.order.framework.output.persistence.entidad.OrderJpaEntity;

public class OrderPersistenceMapper {

    public static OrderJpaEntity toJpa(Order order) {
        OrderJpaEntity entity = new OrderJpaEntity();
        entity.setId(order.getId().value());
        entity.setCustomerId(order.getCustomer().getId());
        entity.setCustomerName(order.getCustomer().getName());
        entity.setCustomerEmail(order.getCustomer().getEmail());
        entity.setStatus(order.getStatus());
        entity.setCreatedAt(order.getCreatedAt());

        List<OrderDetailJpaEntity> details = order.getDetails().stream()
                .map(d -> toDetailJpa(d, entity))
                .toList();
        entity.setDetails(details);

        return entity;
    }

    public static Order toDomain(OrderJpaEntity entity) {
        Customer customer = Customer.reconstitute(
                entity.getCustomerId(),
                entity.getCustomerName(),
                entity.getCustomerEmail());

        List<OrderDetail> details = entity.getDetails().stream()
                .map(OrderPersistenceMapper::toDetailDomain)
                .toList();

        return Order.reconstitute(
                entity.getId(),
                customer,
                details,
                entity.getStatus(),
                entity.getCreatedAt());
    }

    private static OrderDetailJpaEntity toDetailJpa(OrderDetail detail, OrderJpaEntity order) {
        OrderDetailJpaEntity entity = new OrderDetailJpaEntity();
        entity.setId(detail.getId());
        entity.setOrder(order);
        entity.setProductId(detail.getProductId());
        entity.setProductName(detail.getProductName());
        entity.setQuantity(detail.getQuantity().value());
        entity.setUnitPrice(detail.getUnitPrice());
        return entity;
    }

    private static OrderDetail toDetailDomain(OrderDetailJpaEntity entity) {
        return OrderDetail.reconstitute(
                entity.getId(),
                entity.getProductId(),
                entity.getProductName(),
                new Quantity(entity.getQuantity()),
                entity.getUnitPrice());
    }
}
