package com.trustscore.trustscoreapi.domain.exceptions;

import java.util.List;

public abstract class DomainException extends RuntimeException {

    private final List<ValidationError> errors;

    protected DomainException(String message, List<ValidationError> errors) {
        super(message);
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }
}