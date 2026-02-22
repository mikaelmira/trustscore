package com.trustscore.trustscoreapi.domain.utils;

import com.trustscore.trustscoreapi.domain.valueobjects.RawPassword;

public interface PasswordHasher {
    String hash(RawPassword rawPassword);
    boolean matches(String rawPassword, String hashedPassword);
}
