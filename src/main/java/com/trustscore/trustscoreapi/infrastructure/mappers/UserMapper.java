package com.trustscore.trustscoreapi.infrastructure.mappers;

import com.trustscore.trustscoreapi.domain.entities.User;
import com.trustscore.trustscoreapi.infrastructure.persistence.entities.UserJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toDomain(UserJpaEntity jpaEntity);
    UserJpaEntity toJpaEntity(User domain);
}
