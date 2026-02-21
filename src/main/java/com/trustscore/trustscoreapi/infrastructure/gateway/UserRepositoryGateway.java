package com.trustscore.trustscoreapi.infrastructure.gateway;

import com.trustscore.trustscoreapi.domain.entities.User;
import com.trustscore.trustscoreapi.domain.gateway.UserGateway;
import com.trustscore.trustscoreapi.infrastructure.mappers.UserMapper;
import com.trustscore.trustscoreapi.infrastructure.persistence.entities.UserJpaEntity;
import com.trustscore.trustscoreapi.infrastructure.persistence.repositories.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryGateway implements UserGateway {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public User createUser(User user) {
        UserJpaEntity userJpa = userMapper.toJpaEntity(user);
        UserJpaEntity savedUserJpa = userJpaRepository.save(userJpa);
        return userMapper.toDomain(savedUserJpa);
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
