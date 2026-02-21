package com.trustscore.trustscoreapi.domain.usecases.user;

import com.trustscore.trustscoreapi.domain.entities.User;

public interface CreateUserUseCase {
    User execute(User user);
}
