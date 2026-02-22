package com.trustscore.trustscoreapi.domain.exceptions;

import com.trustscore.trustscoreapi.domain.enums.ErrorCode;


public class CpfAlreadyExistsException extends DomainException {
    public CpfAlreadyExistsException() {
        super("CPF já cadastrado", ErrorCode.CPF_ALREADY_EXISTS);
    }
}
