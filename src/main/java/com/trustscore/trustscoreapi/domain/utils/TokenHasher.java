package com.trustscore.trustscoreapi.domain.utils;

public interface TokenHasher {
    String hash(String token);
    boolean matches(String token, String hashedToken);
}
