package com.trustscore.trustscoreapi.domain.exceptions;

import com.trustscore.trustscoreapi.domain.enums.ErrorCode;

public class PasswordCannotBeNullException extends DomainException {
    public PasswordCannotBeNullException() {
        super("Senha não informada", ErrorCode.PASSWORD_CANNOT_BE_NULL);
    }
}
