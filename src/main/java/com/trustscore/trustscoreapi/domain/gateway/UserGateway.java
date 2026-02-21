package com.trustscore.trustscoreapi.domain.gateway;

import com.trustscore.trustscoreapi.domain.entities.User;

public interface UserGateway {
    User createUser(User user);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}
