package com.trustscore.trustscoreapi.domain.exceptions;

import com.trustscore.trustscoreapi.domain.enums.ErrorCode;

import java.util.List;

public class EmailAlreadyExistsException extends DomainException {
    public EmailAlreadyExistsException() {
        super(
                "Conflito de negócio",
                List.of(
                        new ValidationError(
                                "email",
                                "E-mail já cadastrado",
                                ErrorCode.EMAIL_ALREADY_EXISTS
                        )
                )
        );
    }
}
