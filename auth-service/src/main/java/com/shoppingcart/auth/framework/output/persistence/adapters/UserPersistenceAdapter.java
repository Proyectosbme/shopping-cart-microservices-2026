package com.shoppingcart.auth.framework.output.persistence.adapters;

import java.util.Optional;

import com.shoppingcart.auth.aplicacion.command.port.output.SaveUserPort;
import com.shoppingcart.auth.aplicacion.query.port.output.LoadUserPort;
import com.shoppingcart.auth.domain.entity.User;
import com.shoppingcart.auth.framework.output.mapper.UserPersistenceMapper;
import com.shoppingcart.auth.framework.output.persistence.repository.UserJpaRepository;

public class UserPersistenceAdapter implements SaveUserPort, LoadUserPort {

    private final UserJpaRepository jpaRepository;

    public UserPersistenceAdapter(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        return UserPersistenceMapper.toDomain(
                jpaRepository.save(UserPersistenceMapper.toJpa(user)));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(UserPersistenceMapper::toDomain);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id)
                .map(UserPersistenceMapper::toDomain);
    }
}
