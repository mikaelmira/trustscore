package com.trustscore.trustscoreapi.domain.exceptions;

import com.trustscore.trustscoreapi.domain.enums.ErrorCode;

public class InvalidEmailFormatException extends DomainException {
    public InvalidEmailFormatException() {
        super("E-mail inválido", ErrorCode.INVALID_EMAIL_FORMAT);
    }
}
