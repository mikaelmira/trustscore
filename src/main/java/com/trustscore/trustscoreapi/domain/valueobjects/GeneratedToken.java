package com.trustscore.trustscoreapi.domain.valueobjects;

import com.trustscore.trustscoreapi.domain.entities.ValidationToken;

public record GeneratedToken(
        ValidationToken validationToken,
        String rawToken
) {}