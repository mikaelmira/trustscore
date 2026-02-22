package com.trustscore.trustscoreapi.domain.enums;

public enum ErrorCode {

    INVALID_CPF("INVALID_CPF", ErrorCategory.VALIDATION),
    CPF_ALREADY_EXISTS("CPF_ALREADY_EXISTS", ErrorCategory.CONFLICT),

    EMAIL_ALREADY_EXISTS("EMAIL_ALREADY_EXISTS", ErrorCategory.CONFLICT),
    EMAIL_CANNOT_BE_NULL("EMAIL_CANNOT_BE_NULL", ErrorCategory.VALIDATION),
    INVALID_EMAIL_FORMAT("INVALID_EMAIL_FORMAT", ErrorCategory.VALIDATION),

    PASSWORD_CANNOT_BE_NULL("PASSWORD_CANNOT_BE_NULL", ErrorCategory.VALIDATION),
    INVALID_PASSWORD_FORMAT("INVALID_PASSWORD_FORMAT", ErrorCategory.VALIDATION),

    USER_ALREADY_EXISTS("USER_ALREADY_EXISTS", ErrorCategory.CONFLICT),
    ACCOUNT_LOCKED("ACCOUNT_LOCKED", ErrorCategory.FORBIDDEN),
    VALIDATION_TOKEN_ALREADY_USED("VALIDATION_TOKEN_ALREADY_USED", ErrorCategory.CONFLICT),
    VALIDATION_TOKEN_EXPIRED("VALIDATION_TOKEN_EXPIRED", ErrorCategory.CONFLICT),

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
