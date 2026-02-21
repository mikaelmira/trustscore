package com.trustscore.trustscoreapi.infrastructure.gateway;

import com.trustscore.trustscoreapi.domain.entities.User;
import com.trustscore.trustscoreapi.domain.gateway.UserGateway;
import com.trustscore.trustscoreapi.infrastructure.persistence.repositories.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryGateway implements UserGateway {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return userJpaRepository.existsByCpf(cpf);
    }
}
