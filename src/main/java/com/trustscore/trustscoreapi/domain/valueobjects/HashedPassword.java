package com.trustscore.trustscoreapi.domain.valueobjects;

import com.trustscore.trustscoreapi.domain.exceptions.PasswordCannotBeNullException;

public record HashedPassword(String value) {

    public HashedPassword {
        if (value == null || value.isBlank()) {
            throw new PasswordCannotBeNullException();
        }
    }
}
