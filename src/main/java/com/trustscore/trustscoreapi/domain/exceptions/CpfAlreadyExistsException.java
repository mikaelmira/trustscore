package com.trustscore.trustscoreapi.domain.exceptions;

import com.trustscore.trustscoreapi.domain.enums.ErrorCode;

import java.util.List;

public class CpfAlreadyExistsException extends DomainException {
    public CpfAlreadyExistsException() {
        super(
                "Erro de conflito",
                List.of(
                        new ValidationError(
                                "cpf",
                                "CPF já cadastrado",
                                ErrorCode.CPF_ALREADY_EXISTS
                        )
                )
        );
    }
}
