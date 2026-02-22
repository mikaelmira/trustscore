package com.trustscore.trustscoreapi.infrastructure.persistence.entities;

import com.trustscore.trustscoreapi.domain.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_tb_user_email", columnList = "email"),
                @Index(name = "idx_tb_user_cpf", columnList = "cpf"),
                @Index(name = "idx_tb_user_status", columnList = "status"),
                @Index(name = "idx_tb_user_deleted_at", columnList = "deleted_at")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_tb_user_email", columnNames = "email"),
                @UniqueConstraint(name = "uk_tb_user_cpf", columnNames = "cpf")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 200)
    private String email;

    @Column(name = "cpf", nullable = false, unique = true, updatable = false, columnDefinition = "TEXT")
    private String cpf;

    @Column(name = "first_cpf_digits", nullable = false, updatable = false, length = 3)
    private String firstCpfDigits;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(name = "email_verified", nullable = false)
    private boolean emailVerified;

    @Column(name = "email_verified_at")
    private Instant emailVerifiedAt;

    @Column(name = "profile_picture", columnDefinition = "TEXT")
    private String profilePicture;

    @Column(name = "last_login_ip", columnDefinition = "TEXT")
    private String lastLoginIp;

    @Column(name = "last_login_date")
    private Instant lastLoginDate;

    @Column(name = "attempts_failures", nullable = false)
    private int attemptsFailures;

    @Column(name = "lock_time")
    private Instant lockTime;

    @Column(name = "mfa_enabled")
    private boolean mfaEnabled;

    @Column(name = "mfa_secret", columnDefinition = "TEXT")
    private String mfaSecret;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

}
