package com.trustscore.trustscoreapi.domain.enums;

public enum UserStatus {
    AWAITING_EMAIL_VERIFIED,
    ACTIVE,
    BLOCKED;

    public boolean blocksAccess() {
        return this == BLOCKED;
    }
}
