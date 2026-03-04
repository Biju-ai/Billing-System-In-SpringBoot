--liquibase formatted sql
--changeset biju:1

CREATE TABLE IF NOT EXISTS user_login_email(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    created_at DATETIME NOT NULL,
    is_send BOOLEAN,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_user_email FOREIGN KEY (user_id) REFERENCES users(user_id)

)