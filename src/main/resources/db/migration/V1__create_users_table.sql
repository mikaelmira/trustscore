CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(150) NOT NULL,
    email VARCHAR(200) NOT NULL,
    cpf TEXT NOT NULL,
    first_cpf_digits VARCHAR(3) NOT NULL,

    password TEXT NOT NULL,

    email_verified BOOLEAN NOT NULL DEFAULT FALSE,
    email_verified_at TIMESTAMP WITH TIME ZONE,

    profile_picture TEXT,

    last_login_ip TEXT,
    last_login_date TIMESTAMP WITH TIME ZONE,

    attempts_failures INTEGER NOT NULL DEFAULT 0,
    lock_time TIMESTAMP WITH TIME ZONE,

    mfa_enabled BOOLEAN NOT NULL DEFAULT FALSE,
    mfa_secret TEXT,

    status VARCHAR(50) NOT NULL,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE,
    deleted_at TIMESTAMP WITH TIME ZONE,

    CONSTRAINT uk_users_email UNIQUE (email),
    CONSTRAINT uk_users_cpf UNIQUE (cpf)
);

CREATE INDEX idx_users_status ON users(status);
CREATE INDEX idx_users_deleted_at ON users(deleted_at);
