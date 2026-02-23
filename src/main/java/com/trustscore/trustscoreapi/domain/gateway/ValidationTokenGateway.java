package com.trustscore.trustscoreapi.domain.gateway;

import com.trustscore.trustscoreapi.domain.entities.ValidationToken;

public interface ValidationTokenGateway {
    void saveValidationToken(ValidationToken validationToken);
}
