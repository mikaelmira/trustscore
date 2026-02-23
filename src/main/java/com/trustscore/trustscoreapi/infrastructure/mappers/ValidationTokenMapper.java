package com.trustscore.trustscoreapi.infrastructure.mappers;

import com.trustscore.trustscoreapi.domain.entities.ValidationToken;
import com.trustscore.trustscoreapi.infrastructure.persistence.entities.ValidationTokenJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ValidationTokenMapper {
    ValidationToken toDomain(ValidationTokenJpaEntity jpaEntity);
    ValidationTokenJpaEntity toJpaEntity(ValidationToken domain);
}
