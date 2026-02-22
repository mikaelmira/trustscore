package com.trustscore.trustscoreapi.infrastructure.configurations.security;

import com.trustscore.trustscoreapi.domain.utils.PasswordHasher;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Component;

@Component
public class PasswordHasherImpl implements PasswordHasher {

    private static final int ITERATIONS = 4;
    private static final int MEMORY = 65536;
    private static final int PARALLELISM = 2;

    private final Argon2 argon2;

    public PasswordHasherImpl() {
        this.argon2 = Argon2Factory.create(
                Argon2Factory.Argon2Types.ARGON2id
        );
    }

    @Override
    public String hash(String rawPassword) {
        try {
            return argon2.hash(
                    ITERATIONS,
                    MEMORY,
                    PARALLELISM,
                    rawPassword.toCharArray()
            );
        } finally {
            argon2.wipeArray(rawPassword.toCharArray());
        }
    }

    @Override
    public boolean matches(String rawPassword, String hashedPassword) {
        try {
            return argon2.verify(hashedPassword, rawPassword.toCharArray());
        } finally {
            argon2.wipeArray(rawPassword.toCharArray());
        }
    }
}
