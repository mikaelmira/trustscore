package com.trustscore.trustscoreapi.domain.valueobjects;

import com.trustscore.trustscoreapi.domain.exceptions.EmailCannotBeNullException;
import com.trustscore.trustscoreapi.domain.exceptions.InvalidEmailFormatException;

public record Email(String value) {

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    public Email {
        if (value == null || value.isBlank()) {
            throw new EmailCannotBeNullException();
        }

        value = value.trim().toLowerCase();

        if (!value.matches(EMAIL_REGEX)) {
            throw new InvalidEmailFormatException();
        }
    }
}
