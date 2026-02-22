package com.trustscore.trustscoreapi.domain.entities;

import com.trustscore.trustscoreapi.domain.enums.ValidationTokenType;
import com.trustscore.trustscoreapi.domain.exceptions.ValidationTokenAlreadyUsedException;
import com.trustscore.trustscoreapi.domain.exceptions.ValidationTokenExpiredException;

import java.time.Instant;
import java.util.UUID;

public class ValidationToken {

    private UUID id;
    private String token;
    private ValidationTokenType type;
    private Instant expiresAt;
    private boolean used;
    private Instant createdAt;
    private Instant usedAt;

    public ValidationToken(
            String token,
            ValidationTokenType type,
            Instant expiresAt
    ) {
        this.id = null;
        this.token = token;
        this.type = type;
        this.expiresAt = expiresAt;
        this.used = false;
        this.createdAt = Instant.now();
        this.usedAt = null;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ValidationTokenType getType() {
        return type;
    }

    public void setType(ValidationTokenType type) {
        this.type = type;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUsedAt() {
        return usedAt;
    }

    public void setUsedAt(Instant usedAt) {
        this.usedAt = usedAt;
    }

    public void markAsUsed(Instant now) {
        if (used) {
            throw new ValidationTokenAlreadyUsedException();
        }

        if (isExpired(now)) {
            throw new ValidationTokenExpiredException();
        }

        this.used = true;
        this.usedAt = now;
    }

    public boolean isExpired(Instant now) {
        return now.isAfter(expiresAt);
    }

}
