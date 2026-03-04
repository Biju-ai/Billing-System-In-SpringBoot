--liquibase formatted sql
--changeset biju:1

CREATE TABLE  IF NOT EXISTS cart(
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     userlogin_id BIGINT,
                     FOREIGN KEY (userlogin_id) REFERENCES users(user_id)

)