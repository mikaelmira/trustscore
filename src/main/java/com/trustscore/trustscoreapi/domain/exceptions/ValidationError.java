package com.trustscore.trustscoreapi.domain.exceptions;

import com.trustscore.trustscoreapi.domain.enums.ErrorCode;

public record ValidationError(
        String field,
        String message,
        ErrorCode code
) {}