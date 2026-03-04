--liquibase formatted sql
--changeset biju:1

CREATE TABLE IF NOT EXISTS products(
                         product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         product_name VARCHAR(100) NOT NULL,
                         product_price INT NOT NULL,
                         product_category VARCHAR(100) NOT NULL,
                         stock_levels INT NOT NULL,
                         expiry_date DATE NOT NULL,
                         product_quantity INT NOT NULL,
                         size CHAR(1) NULL,
                         is_deleted BOOLEAN,
                         expiry_alert_days INT NOT NULL,
                         low_stock_threshold INT NOT NULL

);