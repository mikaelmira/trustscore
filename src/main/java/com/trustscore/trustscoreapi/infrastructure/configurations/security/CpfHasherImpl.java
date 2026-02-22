package com.trustscore.trustscoreapi.infrastructure.configurations.security;

import com.trustscore.trustscoreapi.domain.utils.CpfHasher;
import com.trustscore.trustscoreapi.domain.valueobjects.Cpf;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class CpfHasherImpl implements CpfHasher {

    private final String pepper;
    private final SecureRandom secureRandom = new SecureRandom();

    private static final int ITERATIONS = 4;
    private static final int MEMORY = 65536;
    private static final int PARALLELISM = 2;

    public CpfHasherImpl(@Value("${pepper.secret}") String pepper) {
        this.pepper = pepper;
    }

    @Override
    public Cpf hash(String rawCpf) {
        Argon2 argon2 = Argon2Factory.create();

        String valueToHash = rawCpf + pepper;

        String hash = argon2.hash(ITERATIONS, MEMORY, PARALLELISM, valueToHash.toCharArray());

        return new Cpf(hash);
    }

    @Override
    public boolean verify(String rawCpf, String storedHash) {
        Argon2 argon2 = Argon2Factory.create();
        String valueToVerify = rawCpf + pepper;
        return argon2.verify(storedHash, valueToVerify.toCharArray());
    }
}
