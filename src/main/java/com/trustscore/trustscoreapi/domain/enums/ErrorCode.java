package com.trustscore.trustscoreapi.domain.enums;

public enum ErrorCode {

    INVALID_CPF("USER_001"),
    CPF_ALREADY_EXISTS("USER_002"),
    EMAIL_ALREADY_EXISTS("USER_003"),
    USER_ALREADY_EXISTS("USER_004"),
    ACCOUNT_LOCKED("USER_005");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
