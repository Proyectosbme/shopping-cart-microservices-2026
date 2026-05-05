package com.shoppingcart.order.framework.output.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingcart.order.framework.output.persistence.entidad.OrderJpaEntity;

public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, Long> {
    List<OrderJpaEntity> findByCustomerId(Long customerId);
}
