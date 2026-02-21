package com.trustscore.trustscoreapi.infrastructure.persistence.repositories;

import com.trustscore.trustscoreapi.infrastructure.persistence.entities.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}
