package com.trustscore.trustscoreapi.infrastructure.exceptions;

import com.trustscore.trustscoreapi.domain.enums.ErrorCode;

public class InfrastructureException extends RuntimeException {

    private final ErrorCode errorCode;

    protected InfrastructureException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}