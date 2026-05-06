package com.shoppingcart.auth.framework.output.mapper;

import com.shoppingcart.auth.domain.entity.User;
import com.shoppingcart.auth.framework.output.persistence.entity.UserJpaEntity;

public class UserPersistenceMapper {

    private UserPersistenceMapper() {}

    public static UserJpaEntity toJpa(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.getId());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole());
        return entity;
    }

    public static User toDomain(UserJpaEntity entity) {
        return new User(entity.getId(), entity.getEmail(), entity.getPassword(), entity.getRole());
    }
}
