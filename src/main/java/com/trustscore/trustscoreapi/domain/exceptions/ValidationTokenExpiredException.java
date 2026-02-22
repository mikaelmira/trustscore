package com.trustscore.trustscoreapi.domain.exceptions;

import com.trustscore.trustscoreapi.domain.enums.ErrorCode;

public class ValidationTokenExpiredException extends DomainException {

    public ValidationTokenExpiredException() {
        super("O token expirou", ErrorCode.VALIDATION_TOKEN_EXPIRED);
    }
}
