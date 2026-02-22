package com.trustscore.trustscoreapi.domain.enums;

public enum ErrorCode {

    INVALID_CPF("INVALID_CPF", ErrorCategory.VALIDATION),
    CPF_ALREADY_EXISTS("CPF_ALREADY_EXISTS", ErrorCategory.CONFLICT),
    EMAIL_ALREADY_EXISTS("EMAIL_ALREADY_EXISTS", ErrorCategory.CONFLICT),
    USER_ALREADY_EXISTS("USER_ALREADY_EXISTS", ErrorCategory.CONFLICT),
    ACCOUNT_LOCKED("ACCOUNT_LOCKED", ErrorCategory.FORBIDDEN),

    INTERNAL_ERROR("INTERNAL_ERROR", ErrorCategory.INTERNAL);

    private final String code;
    private final ErrorCategory category;

    ErrorCode(String code, ErrorCategory category) {
        this.code = code;
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public ErrorCategory getCategory() {
        return category;
    }
}
