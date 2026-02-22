package com.trustscore.trustscoreapi.domain.valueobjects;

import com.trustscore.trustscoreapi.domain.exceptions.InvalidPasswordException;
import com.trustscore.trustscoreapi.domain.exceptions.PasswordCannotBeNullException;

public record RawPassword(String value) {

    private static final int MIN_LENGTH = 8;

    public RawPassword {
        if (value == null || value.isBlank()) {
            throw new PasswordCannotBeNullException();
        }

        if (value.length() < MIN_LENGTH) {
            throw new InvalidPasswordException("A senha deve conter no mínimo 8 caracteres");
        }

        if (value.chars().noneMatch(Character::isUpperCase)) {
            throw new InvalidPasswordException("A senha deve conter no mínimo 1 letra maiuscula");
        }

        if (value.chars().noneMatch(Character::isLowerCase)) {
            throw new InvalidPasswordException("A senha deve conter no mínimo 1 letra minuscula");
        }

        if (value.chars().noneMatch(Character::isDigit)) {
            throw new InvalidPasswordException("A senha deve conter no mínimo 1 número");
        }

        if (value.chars().allMatch(Character::isLetterOrDigit)) {
            throw new InvalidPasswordException("A senha deve conter no mínimo 1 caracter especial");
        }

    }
}
