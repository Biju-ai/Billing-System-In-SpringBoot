--liquibase formatted sql
--changeset biju:1
CREATE TABLE IF NOT EXISTS orders(
           order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
           total_amount DOUBLE NOT NULL,
           order_date DATETIME,
           user_id BIGINT,
           CONSTRAINT fk_order_user FOREIGN KEY (user_id) REFERENCES users(user_id)


);