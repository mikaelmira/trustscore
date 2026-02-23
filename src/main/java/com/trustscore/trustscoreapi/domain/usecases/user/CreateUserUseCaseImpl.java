package com.trustscore.trustscoreapi.domain.usecases.user;

import com.trustscore.trustscoreapi.domain.entities.User;
import com.trustscore.trustscoreapi.domain.entities.ValidationToken;
import com.trustscore.trustscoreapi.domain.enums.UserStatus;
import com.trustscore.trustscoreapi.domain.exceptions.CpfAlreadyExistsException;
import com.trustscore.trustscoreapi.domain.exceptions.EmailAlreadyExistsException;
import com.trustscore.trustscoreapi.domain.exceptions.InvalidCpfException;
import com.trustscore.trustscoreapi.domain.gateway.UserGateway;
import com.trustscore.trustscoreapi.domain.gateway.ValidationTokenGateway;
import com.trustscore.trustscoreapi.domain.utils.CpfHasher;
import com.trustscore.trustscoreapi.domain.utils.PasswordHasher;
import com.trustscore.trustscoreapi.domain.utils.TokenHasher;
import com.trustscore.trustscoreapi.domain.valueobjects.Cpf;
import com.trustscore.trustscoreapi.domain.valueobjects.GeneratedToken;
import com.trustscore.trustscoreapi.domain.valueobjects.HashedPassword;
import com.trustscore.trustscoreapi.domain.valueobjects.RawPassword;

import java.time.Instant;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserGateway userGateway;
    private final ValidationTokenGateway validationTokenGateway;
    private final PasswordHasher passwordHasher;
    private final CpfHasher cpfHasher;
    private final TokenHasher tokenHasher;

    public CreateUserUseCaseImpl(
            UserGateway userGateway,
            ValidationTokenGateway validationTokenGateway,
            PasswordHasher passwordHasher,
            CpfHasher cpfHasher,
            TokenHasher tokenHasher
    ) {
        this.userGateway = userGateway;
        this.validationTokenGateway = validationTokenGateway;
        this.passwordHasher = passwordHasher;
        this.cpfHasher = cpfHasher;
        this.tokenHasher = tokenHasher;
    }

    @Override
    public User execute(User user) {

        if (!user.getCpf().isValid()) {
            throw new InvalidCpfException();
        }

        if (userGateway.existsByEmail(user.getEmail().value())) {
            throw new EmailAlreadyExistsException();
        }

        Cpf cpfHash = cpfHasher.hash(user.getCpf().value());

        if (userGateway.existsByCpf(cpfHash.value())) {
            throw new CpfAlreadyExistsException();
        }

        RawPassword rawPassword = new RawPassword(user.getPassword().value());
        String hash = passwordHasher.hash(rawPassword);
        HashedPassword hashedPassword = new HashedPassword(hash);

        String firstCpfDigits = user.getCpf().value().substring(0, 3);

        User userToBeCreated = new User(
                null,
                user.getName(),
                user.getEmail(),
                cpfHash,
                firstCpfDigits,
                hashedPassword,
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

        User createdUser = userGateway.createUser(userToBeCreated);

        GeneratedToken generatedToken = ValidationToken.createEmailValidation(
                user,
                tokenHasher
        );

        validationTokenGateway.saveValidationToken(generatedToken.validationToken());

        return createdUser;
    }
}
