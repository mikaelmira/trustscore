package com.trustscore.trustscoreapi.domain.enums;

public enum UserStatus {
    AWAITING_EMAIL_VERIFIED,
    ACTIVE,
    DISABLED,
    BLOCKED,
    DELETED;

    public boolean blocksAccess() {
        return this == BLOCKED;
    }
}
