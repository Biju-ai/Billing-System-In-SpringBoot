--liquibase formatted sql
--changeset biju:1

CREATE TABLE IF NOT EXISTS cart_item(
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          quantity INT NOT NULL,
                          totalprice DOUBLE NOT NULL,
                          product_id BIGINT,
                          cart_id BIGINT,
                          FOREIGN KEY (product_id) REFERENCES products(product_id),
                          FOREIGN KEY (cart_id) REFERENCES cart(id)
);