--liquibase  formatted sql
--changeset biju:1
INSERT INTO users(first_name,
                  last_name,
                  email,
                  phone_number,
                  password,
                  date_of_birth,
                  gender,
                  status,
                  verification_token,
                  token_expire_date,
                  is_admin,
                  role_id)
VALUES (
        'biju',
        'shrestha',
        'biju@gmail.com',
        '98818828383',
        '$2a$12$LDgpu4bcuSLW3QaBW78va.Xy7CF7Lw9hcQoRB4t5.G0.VFoTHoqfO',
        '2020-10-20',
        'male',
        'ACTIVE',
        'true',
        CURRENT_TIMESTAMP,
        '1',
        1


       )