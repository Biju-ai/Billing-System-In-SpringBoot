-- liquibase formatted sql
-- changeset biju:1

CREATE TABLE IF NOT EXISTS users (
                       user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       first_name VARCHAR(50) NOT NULL,
                       last_name VARCHAR(50) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       phone_number VARCHAR(15) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       date_of_birth DATE NOT NULL,
                       gender VARCHAR(50) NOT NULL,
                       is_admin BOOLEAN,
                       role_id BIGINT NOT NULL,
                       status VARCHAR(225) NOT NULL,
                       verification_token VARCHAR(100) NOT NULL,
                       token_expire_date TIMESTAMP NOT NULL,
                       CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES role(id)

);