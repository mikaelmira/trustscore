package com.trustscore.trustscoreapi.infrastructure.exceptions;

import com.trustscore.trustscoreapi.domain.enums.ErrorCategory;
import com.trustscore.trustscoreapi.domain.exceptions.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ApiErrorResponse> handleDomain(DomainException ex) {
        HttpStatus status = mapStatus(ex.getErrorCode().getCategory());

        return ResponseEntity.status(status)
                .body(buildResponse(
                        ex.getErrorCode().getCode(),
                        ex.getMessage(),
                        status
                ));
    }

    @ExceptionHandler(InfrastructureException.class)
    public ResponseEntity<ApiErrorResponse> handleInfrastructure(InfrastructureException ex) {
        HttpStatus status = mapStatus(ex.getErrorCode().getCategory());

        return ResponseEntity.status(status)
                .body(buildResponse(
                        ex.getErrorCode().getCode(),
                        ex.getMessage(),
                        status
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneric(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildResponse(
                        "INTERNAL_ERROR",
                        "Erro interno do servidor",
                        HttpStatus.INTERNAL_SERVER_ERROR
                ));
    }

    private ApiErrorResponse buildResponse(String code, String message, HttpStatus status) {
        return new ApiErrorResponse(
                code,
                message,
                status.value(),
                UUID.randomUUID().toString(),
                Instant.now()
        );
    }

    private HttpStatus mapStatus(ErrorCategory code) {
        return switch (code) {
            case VALIDATION -> HttpStatus.BAD_REQUEST;
            case CONFLICT -> HttpStatus.CONFLICT;
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case FORBIDDEN -> HttpStatus.FORBIDDEN;
            case INTERNAL -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}