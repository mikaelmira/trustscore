package com.trustscore.trustscoreapi.infrastructure.persistence.repositories;

import com.trustscore.trustscoreapi.domain.enums.ValidationTokenType;
import com.trustscore.trustscoreapi.infrastructure.persistence.entities.ValidationTokenJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ValidationTokenJpaRepository extends JpaRepository<ValidationTokenJpaEntity, UUID> {
    Optional<ValidationTokenJpaEntity> findByTokenAndType(String token, ValidationTokenType type);
}
