--liquibase formatted sql
--changeset biju:1

CREATE TABLE IF NOT EXISTS order_items(
          orders_id BIGINT AUTO_INCREMENT PRIMARY KEY,
          quantity INT NOT NULL,
          price DOUBLE NOT NULL,
          products_id BIGINT,
          order_id BIGINT,
          CONSTRAINT fk_orderitem_product FOREIGN KEY (products_id) REFERENCES products(product_id),
          CONSTRAINT fk_orderitem_order FOREIGN KEY (order_id) REFERENCES orders(order_id)

);