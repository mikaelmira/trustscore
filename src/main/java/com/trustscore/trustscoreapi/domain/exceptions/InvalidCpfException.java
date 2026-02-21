package com.trustscore.trustscoreapi.domain.exceptions;

import com.trustscore.trustscoreapi.domain.enums.ErrorCode;

import java.util.List;

public class InvalidCpfException extends DomainException {

    public InvalidCpfException() {
        super(
                "Erro de validação",
                List.of(
                        new ValidationError(
                                "cpf",
                                "CPF inválido",
                                ErrorCode.INVALID_CPF
                        )
                )
        );
    }
}
