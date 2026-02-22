package com.trustscore.trustscoreapi.domain.entities;

import com.trustscore.trustscoreapi.domain.enums.UserStatus;
import com.trustscore.trustscoreapi.domain.valueobjects.Cpf;
import com.trustscore.trustscoreapi.domain.valueobjects.Email;
import com.trustscore.trustscoreapi.domain.valueobjects.HashedPassword;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

public class User {

    private static final int MAX_TEMP_ATTEMPTS = 3;
    private static final int MAX_BLOCK_ATTEMPTS = 5;
    private static final Duration TEMP_LOCK_DURATION = Duration.ofMinutes(30);

    private Long id;
    private String name;
    private Email email;
    private Cpf cpf;
    private String firstCpfDigits;
    private HashedPassword password;
    private boolean emailVerified;
    private Instant emailVerifiedAt;
    private String profilePicture;
    private String lastLoginIp;
    private Instant lastLoginDate;
    private int attemptsFailures;
    private Instant lockTime;
    private boolean mfaEnabled;
    private String mfaSecret;
    private UserStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    public User() {
    }

    public User(
            Long id,
            String name,
            Email email,
            Cpf cpf,
            String firstCpfDigits,
            HashedPassword password,
            boolean emailVerified,
            Instant emailVerifiedAt,
            String profilePicture,
            String lastLoginIp,
            Instant lastLoginDate,
            int attemptsFailures,
            Instant lockTime,
            boolean mfaEnabled,
            String mfaSecret,
            UserStatus status,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.firstCpfDigits = firstCpfDigits;
        this.password = password;
        this.emailVerified = emailVerified;
        this.emailVerifiedAt = emailVerifiedAt;
        this.profilePicture = profilePicture;
        this.lastLoginIp = lastLoginIp;
        this.lastLoginDate = lastLoginDate;
        this.attemptsFailures = attemptsFailures;
        this.lockTime = lockTime;
        this.mfaEnabled = mfaEnabled;
        this.mfaSecret = mfaSecret;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public String getFirstCpfDigits() {
        return firstCpfDigits;
    }

    public void setFirstCpfDigits(String firstCpfDigits) {
        this.firstCpfDigits = firstCpfDigits;
    }

    public HashedPassword getPassword() {
        return password;
    }

    public void setPassword(HashedPassword password) {
        this.password = password;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Instant getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Instant emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Instant getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Instant lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public int getAttemptsFailures() {
        return attemptsFailures;
    }

    public void setAttemptsFailures(int attemptsFailures) {
        this.attemptsFailures = attemptsFailures;
    }

    public Instant getLockTime() {
        return lockTime;
    }

    public void setLockTime(Instant lockTime) {
        this.lockTime = lockTime;
    }

    public void setMfaEnabled(boolean mfaEnabled) {
        this.mfaEnabled = mfaEnabled;
    }

    public String getMfaSecret() {
        return mfaSecret;
    }

    public void setMfaSecret(String mfaSecret) {
        this.mfaSecret = mfaSecret;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createAt) {
        this.createdAt = createAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

    public void markEmailAsVerified() {
        this.emailVerified = true;
        this.emailVerifiedAt = Instant.now();
    }

    public boolean isLocked() {
        return isTemporarilyLocked() || status.blocksAccess();
    }

    private boolean isTemporarilyLocked() {
        return lockTime != null && Instant.now().isBefore(lockTime);
    }

    public boolean isMfaEnabled() {
        return mfaEnabled;
    }

    public void registerLogin(String ip) {
        lastLoginDate = Instant.now();
        lastLoginIp = ip;
    }

    public boolean hasExceededMaxAttempts(int max) {
        return attemptsFailures > max;
    }

    public void registerFailedAttempt() {
        attemptsFailures++;

        if (attemptsFailures >= MAX_BLOCK_ATTEMPTS) {
            block();
            return;
        }

        if (attemptsFailures >= MAX_TEMP_ATTEMPTS) {
            lockTemporarily();
        }
    }

    private void lockTemporarily() {
        lockTime = Instant.now().plus(TEMP_LOCK_DURATION);
    }

    private void block() {
        status = UserStatus.BLOCKED;
    }

    public void registerSuccessfulLogin() {
        attemptsFailures = 0;
        lockTime = null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
