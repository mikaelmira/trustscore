package com.trustscore.trustscoreapi.infrastructure.mappers;

import com.trustscore.trustscoreapi.domain.entities.User;
import com.trustscore.trustscoreapi.domain.valueobjects.Cpf;
import com.trustscore.trustscoreapi.domain.valueobjects.Email;
import com.trustscore.trustscoreapi.domain.valueobjects.HashedPassword;
import com.trustscore.trustscoreapi.infrastructure.dtos.users.CreateUserRequestDTO;
import com.trustscore.trustscoreapi.infrastructure.persistence.entities.UserJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toDomain(UserJpaEntity jpaEntity);
    UserJpaEntity toJpaEntity(User domain);

    User toDomain(CreateUserRequestDTO dto);

    default Cpf mapCpf(String value) {
        return value == null ? null : new Cpf(value);
    }

    default String mapCpf(Cpf cpf) {
        return cpf == null ? null : cpf.value();
    }

    default Email mapEmail(String value) {
        return value == null ? null : new Email(value);
    }

    default String mapEmail(Email email) {
        return email == null ? null : email.value();
    }

    default HashedPassword mapPassword(String value) {
        return value == null ? null : new HashedPassword(value);
    }

    default String mapPassword(HashedPassword password) {
        return password == null ? null : password.value();
    }
}
