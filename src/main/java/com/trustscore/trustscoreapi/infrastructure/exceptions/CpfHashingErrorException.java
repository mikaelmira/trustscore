package com.trustscore.trustscoreapi.infrastructure.exceptions;

import com.trustscore.trustscoreapi.domain.enums.ErrorCode;

public class CpfHashingErrorException extends InfrastructureException {
    public CpfHashingErrorException() {
        super("Erro ao criptografar o CPF", ErrorCode.INTERNAL_ERROR);
    }
}
