--liquibase formatted sql
--changeset biju:1
CREATE TABLE IF NOT EXISTS role(
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     role VARCHAR(255)
);