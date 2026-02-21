package com.trustscore.trustscoreapi.domain.usecases.user;

import com.trustscore.trustscoreapi.domain.entities.User;
import com.trustscore.trustscoreapi.domain.enums.UserStatus;
import com.trustscore.trustscoreapi.domain.gateway.UserGateway;
import com.trustscore.trustscoreapi.domain.utils.PasswordHasher;

import java.time.Instant;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserGateway gateway;
    private final PasswordHasher passwordHasher;

    public CreateUserUseCaseImpl(UserGateway gateway, PasswordHasher passwordHasher) {
        this.gateway = gateway;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public User execute(User user) {

        if (gateway.existsByEmail(user.getEmail())) {
            throw new RuntimeException();
        }

        if (gateway.existsByCpf(user.getCpf().value())) {
            throw new RuntimeException();
        }

        String passwordHash = passwordHasher.hash(user.getPassword());

        User userToBeCreated = new User(
                null,
                user.getName(),
                user.getEmail(),
                user.getCpf(), // TODO: Fazer o hash
                passwordHash,
                false,
                null,
                user.getProfilePicture(),
                null,
                null,
                0,
                null,
                false,
                null,
                UserStatus.AWAITING_EMAIL_VERIFIED,
                Instant.now(),
                null,
                null
        );

        return gateway.createUser(userToBeCreated);
    }
}
