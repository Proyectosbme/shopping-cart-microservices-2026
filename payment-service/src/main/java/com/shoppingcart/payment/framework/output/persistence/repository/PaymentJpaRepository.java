package com.shoppingcart.payment.framework.output.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingcart.payment.domain.vo.PaymentStatus;
import com.shoppingcart.payment.framework.output.persistence.entidad.PaymentJpaEntity;

public interface PaymentJpaRepository extends JpaRepository<PaymentJpaEntity, Long> {
    List<PaymentJpaEntity> findByOrderId(Long orderId);

    boolean existsByOrderIdAndStatusIn(Long orderId, List<PaymentStatus> statuses);
}
