package com.trustscore.trustscoreapi.infrastructure.configurations.security;

import com.trustscore.trustscoreapi.domain.utils.CpfHasher;
import com.trustscore.trustscoreapi.domain.valueobjects.Cpf;
import com.trustscore.trustscoreapi.infrastructure.exceptions.CpfHashingErrorException;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CpfHasherImpl implements CpfHasher {

    private final String secret;
    private final String algorithm;

    public CpfHasherImpl(
            @Value("${pepper.secret}") String secret,
            @Value("${pepper.algorithm}") String algorithm
    ) {
        this.secret = secret;
        this.algorithm = algorithm;
    }

    @Override
    public Cpf hash(String rawCpf) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            String value = rawCpf + secret;

            byte[] hashBytes = digest.digest(value.getBytes(StandardCharsets.UTF_8));

            return new Cpf(bytesToHex(hashBytes));

        } catch (NoSuchAlgorithmException e) {
            throw new CpfHashingErrorException();
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
