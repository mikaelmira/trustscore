package com.trustscore.trustscoreapi.infrastructure.exceptions;

import com.trustscore.trustscoreapi.domain.enums.ErrorCode;

public class TokenHashingErrorException extends InfrastructureException {
    public TokenHashingErrorException() {
        super("Erro ao criptografar o token", ErrorCode.INTERNAL_ERROR);
    }
}
