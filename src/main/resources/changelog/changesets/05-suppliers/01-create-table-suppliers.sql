--liquibase formatted sql
--changeset biju:2

CREATE TABLE IF NOT EXISTS suppliers(
                                        suppliers_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        supplier_name VARCHAR(100) NOT NULL,
    company_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(10) NOT NULL,
    address VARCHAR(200) NOT NULL,
    location VARCHAR(200) NOT NULL,
    distance VARCHAR(255) NOT NULL
    );