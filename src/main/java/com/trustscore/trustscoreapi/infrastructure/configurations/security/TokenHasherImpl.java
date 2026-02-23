package com.trustscore.trustscoreapi.infrastructure.configurations.security;

import com.trustscore.trustscoreapi.domain.utils.TokenHasher;
import com.trustscore.trustscoreapi.infrastructure.exceptions.TokenHashingErrorException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class TokenHasherImpl implements TokenHasher {

    @Override
    public String hash(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(token.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new TokenHashingErrorException();
        }
    }

    @Override
    public boolean matches(String token, String hashedToken) {
        return hash(token).equals(hashedToken);
    }
}
