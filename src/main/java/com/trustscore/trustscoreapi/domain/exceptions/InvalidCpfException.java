package com.trustscore.trustscoreapi.domain.exceptions;

import com.trustscore.trustscoreapi.domain.enums.ErrorCode;

public class InvalidCpfException extends DomainException {

    public InvalidCpfException() {
        super("CPF inválido", ErrorCode.INVALID_CPF);
    }
}
