--liquibase formatted sql
--changeset biju:1

INSERT INTO role(role)
VALUES ('ADMIN'),('CASHIER');
