--liquibase formatted sql
--changeset biju:5

CREATE TABLE IF NOT EXISTS supplier_msg(
                                           supplier_msg_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           suppliers_id BIGINT NOT NULL,
                                           remarks VARCHAR(500) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    template TEXT,

    CONSTRAINT fk_supplier_msg_supplier
    FOREIGN KEY (suppliers_id)
    REFERENCES suppliers(suppliers_id)
    );