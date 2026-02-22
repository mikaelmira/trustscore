package com.trustscore.trustscoreapi.domain.exceptions;

import com.trustscore.trustscoreapi.domain.enums.ErrorCode;

public class ValidationTokenAlreadyUsedException extends DomainException {

    public ValidationTokenAlreadyUsedException() {
        super("O token já foi utilizado", ErrorCode.VALIDATION_TOKEN_ALREADY_USED);
    }
}
