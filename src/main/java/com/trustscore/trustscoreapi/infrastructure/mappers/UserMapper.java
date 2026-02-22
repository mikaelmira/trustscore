package com.trustscore.trustscoreapi.infrastructure.mappers;

import com.trustscore.trustscoreapi.domain.entities.User;
import com.trustscore.trustscoreapi.domain.valueobjects.Cpf;
import com.trustscore.trustscoreapi.infrastructure.dtos.users.CreateUserRequestDTO;
import com.trustscore.trustscoreapi.infrastructure.persistence.entities.UserJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toDomain(UserJpaEntity jpaEntity);
    UserJpaEntity toJpaEntity(User domain);

    User toDomain(CreateUserRequestDTO dto);

    default Cpf map(String value) {
        if (value == null) return null;
        return new Cpf(value);
    }
    default String map(Cpf cpf) {
        if (cpf == null) return null;
        return cpf.value();
    }
}
