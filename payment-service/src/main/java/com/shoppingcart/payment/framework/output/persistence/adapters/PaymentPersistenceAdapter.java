package com.shoppingcart.payment.framework.output.persistence.adapters;

import java.util.List;

import com.shoppingcart.payment.application.command.port.output.PaymentCommandRepository;
import com.shoppingcart.payment.application.query.port.output.PaymentQueryRepository;
import com.shoppingcart.payment.domain.entity.Payment;
import com.shoppingcart.payment.domain.exceptions.PaymentNotFoundException;
import com.shoppingcart.payment.domain.vo.PaymentStatus;
import com.shoppingcart.payment.framework.output.mapper.PaymentPersistenceMapper;
import com.shoppingcart.payment.framework.output.persistence.repository.PaymentJpaRepository;

public class PaymentPersistenceAdapter implements PaymentCommandRepository, PaymentQueryRepository {

    private final PaymentJpaRepository jpaRepository;

    public PaymentPersistenceAdapter(PaymentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Payment save(Payment payment) {
        return PaymentPersistenceMapper.toDomain(
                jpaRepository.save(PaymentPersistenceMapper.toJpa(payment)));
    }

    @Override
    public Payment findById(Long id) {
        return jpaRepository.findById(id)
                .map(PaymentPersistenceMapper::toDomain)
                .orElseThrow(() -> new PaymentNotFoundException(id));
    }

    @Override
    public boolean existsActivePaymentForOrder(Long orderId) {
        return jpaRepository.existsByOrderIdAndStatusIn(orderId,
                List.of(PaymentStatus.PENDING, PaymentStatus.APPROVED));
    }

    @Override
    public List<Payment> findByOrderId(Long orderId) {
        return jpaRepository.findByOrderId(orderId)
                .stream()
                .map(PaymentPersistenceMapper::toDomain)
                .toList();
    }
}
