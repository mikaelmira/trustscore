package com.trustscore.trustscoreapi.domain.enums;

public enum UserStatus {
    ACTIVE,
    BLOCKED;

    public boolean blocksAccess() {
        return this == BLOCKED;
    }
}
