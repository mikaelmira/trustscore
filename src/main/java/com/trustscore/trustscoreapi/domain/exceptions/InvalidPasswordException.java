package com.trustscore.trustscoreapi.domain.exceptions;

import com.trustscore.trustscoreapi.domain.enums.ErrorCode;

public class InvalidPasswordException extends DomainException {
    public InvalidPasswordException(String message) {
        super(message, ErrorCode. INVALID_PASSWORD_FORMAT);
    }
}
