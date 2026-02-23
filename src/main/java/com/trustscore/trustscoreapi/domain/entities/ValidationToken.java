package com.trustscore.trustscoreapi.domain.entities;

import com.trustscore.trustscoreapi.domain.enums.ValidationTokenType;
import com.trustscore.trustscoreapi.domain.exceptions.ValidationTokenAlreadyUsedException;
import com.trustscore.trustscoreapi.domain.exceptions.ValidationTokenExpiredException;
import com.trustscore.trustscoreapi.domain.utils.TokenGenerator;
import com.trustscore.trustscoreapi.domain.utils.TokenHasher;
import com.trustscore.trustscoreapi.domain.valueobjects.GeneratedToken;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class ValidationToken {

    private UUID id;
    private String token;
    private User user;
    private ValidationTokenType type;
    private Instant expiresAt;
    private boolean used;
    private Instant createdAt;
    private Instant usedAt;

    public ValidationToken(
            String token,
            User user,
            ValidationTokenType type,
            Instant expiresAt
    ) {
        this.id = null;
        this.token = token;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public static GeneratedToken createEmailValidation(
            User user,
            TokenHasher hasher
    ) {
        String rawToken = TokenGenerator.generate();
        String hashed = hasher.hash(rawToken);

        ValidationToken validationToken = new ValidationToken(
                hashed,
                user,
                ValidationTokenType.EMAIL_VALIDATION,
                Instant.now().plus(24, ChronoUnit.HOURS)
        );

        return new GeneratedToken(
                validationToken,
                rawToken
        );
    }

}
