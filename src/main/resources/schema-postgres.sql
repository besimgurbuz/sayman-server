DROP TABLE IF EXISTS users;
CREATE TABLE users(
    id BIGINT PRIMARY KEY,
    username VARCHAR(100),
    role VARCHAR(100),
    email VARCHAR(255),
    created_at DATE,
    password VARCHAR(300),
    account_non_expired BOOLEAN default true,
    account_non_locked BOOLEAN default true,
    credentials_non_expired BOOLEAN default true,
    enabled BOOLEAN default true
);

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START 1;
