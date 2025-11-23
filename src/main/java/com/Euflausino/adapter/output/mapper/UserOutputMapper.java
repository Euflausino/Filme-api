package com.Euflausino.adapter.output.mapper;

import com.Euflausino.infra.security.repository.JpaUserEntity;
import com.Euflausino.infra.security.entity.User;

import java.util.List;

public class UserOutputMapper {

    public static User toEntity (JpaUserEntity jpaUserEntity) {
        return new User(
                jpaUserEntity.getId(),
                jpaUserEntity.getUsername(),
                jpaUserEntity.getPassword(),
                jpaUserEntity.getEmail()
        );
    }

    public static JpaUserEntity toDto (User uerEntity) {
        return new JpaUserEntity(
                uerEntity.getUsername(),
                uerEntity.getPassword(),
                uerEntity.getEmail()
        );
    }

    public static List<User> toEntityList(List<JpaUserEntity> jpaUserEntityList) {
        return jpaUserEntityList.stream()
                .map(UserOutputMapper::toEntity).toList();
    }

}
