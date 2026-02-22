package com.trustscore.trustscoreapi.domain.exceptions;

import com.trustscore.trustscoreapi.domain.enums.ErrorCode;

public class EmailCannotBeNullException extends DomainException {
    public EmailCannotBeNullException() {
        super("E-mail não informado", ErrorCode.EMAIL_CANNOT_BE_NULL);
    }
}
