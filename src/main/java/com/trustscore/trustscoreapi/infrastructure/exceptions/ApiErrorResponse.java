package com.trustscore.trustscoreapi.infrastructure.exceptions;

import java.time.Instant;

public record ApiErrorResponse(
        String code,
        String message,
        int status,
        String traceId,
        Instant timestamp
) {}
