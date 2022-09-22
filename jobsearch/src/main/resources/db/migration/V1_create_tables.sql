CREATE TABLE IF NOT EXISTS clients
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_name   VARCHAR(100) NOT NULL,
    email         VARCHAR(255) UNIQUE NOT NULL,
    api_key       VARCHAR(36) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS positions
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    job_position  VARCHAR(50) NOT NULL,
    location      VARCHAR(50) UNIQUE NOT NULL,
    url           VARCHAR(255) UNIQUE NOT NULL
);