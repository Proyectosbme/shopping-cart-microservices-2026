package com.shoppingcart.payment.framework.output.mapper;

import com.shoppingcart.payment.domain.entity.Payment;
import com.shoppingcart.payment.framework.output.persistence.entidad.PaymentJpaEntity;

public class PaymentPersistenceMapper {

    private PaymentPersistenceMapper() {}

    public static PaymentJpaEntity toJpa(Payment payment) {
        PaymentJpaEntity entity = new PaymentJpaEntity();
        entity.setId(payment.getId());
        entity.setOrderId(payment.getOrderId());
        entity.setCustomerId(payment.getCustomerId());
        entity.setAmount(payment.getAmount());
        entity.setPaymentMethod(payment.getPaymentMethod());
        entity.setStatus(payment.getStatus());
        entity.setCreatedAt(payment.getCreatedAt());
        return entity;
    }

    public static Payment toDomain(PaymentJpaEntity entity) {
        return Payment.reconstitute(
                entity.getId(),
                entity.getOrderId(),
                entity.getCustomerId(),
                entity.getAmount(),
                entity.getPaymentMethod(),
                entity.getStatus(),
                entity.getCreatedAt());
    }
}
