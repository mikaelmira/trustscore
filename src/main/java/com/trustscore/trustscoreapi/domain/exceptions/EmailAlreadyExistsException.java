package com.trustscore.trustscoreapi.domain.exceptions;

import com.trustscore.trustscoreapi.domain.enums.ErrorCode;

public class EmailAlreadyExistsException extends DomainException {
    public EmailAlreadyExistsException() {
        super("E-mail já cadastrado", ErrorCode.EMAIL_ALREADY_EXISTS);
    }
}
