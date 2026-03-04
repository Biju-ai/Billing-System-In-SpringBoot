--liquibase formatted sql
--changeset biju:1

CREATE TABLE IF NOT EXISTS html_templates(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    template TEXT,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    template_name VARCHAR(255)

);