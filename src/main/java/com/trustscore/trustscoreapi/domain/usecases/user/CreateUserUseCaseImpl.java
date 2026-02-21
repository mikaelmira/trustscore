package com.trustscore.trustscoreapi.domain.usecases.user;

import com.trustscore.trustscoreapi.domain.entities.User;
import com.trustscore.trustscoreapi.domain.enums.UserStatus;
import com.trustscore.trustscoreapi.domain.gateway.UserGateway;
import com.trustscore.trustscoreapi.domain.utils.CpfHasher;
import com.trustscore.trustscoreapi.domain.utils.PasswordHasher;
import com.trustscore.trustscoreapi.domain.valueobjects.Cpf;

import java.time.Instant;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserGateway gateway;
    private final PasswordHasher passwordHasher;
    private final CpfHasher cpfHasher;

    public CreateUserUseCaseImpl(UserGateway gateway, PasswordHasher passwordHasher, CpfHasher cpfHasher) {
        this.gateway = gateway;
        this.passwordHasher = passwordHasher;
        this.cpfHasher = cpfHasher;
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

        Cpf cpfHash = cpfHasher.hash(user.getCpf().value());

        String firstCpfDigits = user.getCpf().value().substring(0, 3);

        User userToBeCreated = new User(
                null,
                user.getName(),
                user.getEmail(),
                cpfHash,
                firstCpfDigits,
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
