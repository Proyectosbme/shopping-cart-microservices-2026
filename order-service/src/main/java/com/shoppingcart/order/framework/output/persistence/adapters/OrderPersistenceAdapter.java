package com.shoppingcart.order.framework.output.persistence.adapters;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.shoppingcart.order.application.command.port.output.OrderCommandRepository;
import com.shoppingcart.order.application.query.port.output.OrderQueryRepository;
import com.shoppingcart.order.domain.entity.Order;
import com.shoppingcart.order.domain.vo.OrderId;
import com.shoppingcart.order.framework.output.mapper.OrderPersistenceMapper;
import com.shoppingcart.order.framework.output.persistence.entity.OrderJpaEntity;
import com.shoppingcart.order.framework.output.persistence.repository.OrderJpaRepository;

@Component
public class OrderPersistenceAdapter implements OrderCommandRepository, OrderQueryRepository {

    private final OrderJpaRepository orderJpaRepository;

    public OrderPersistenceAdapter(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return orderJpaRepository.findById(id.value())
                .map(OrderPersistenceMapper::toDomain);
    }

    @Override
    public Order save(Order order) {
        OrderJpaEntity saved = orderJpaRepository.save(OrderPersistenceMapper.toJpa(order));
        return OrderPersistenceMapper.toDomain(saved);
    }

    @Override
    public List<Order> findByCustomerId(Long customerId) {
        return orderJpaRepository.findByCustomerId(customerId).stream()
                .map(OrderPersistenceMapper::toDomain)
                .toList();
    }
}
