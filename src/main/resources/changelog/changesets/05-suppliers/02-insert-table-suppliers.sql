--liquibase formatted sql
--changeset biju:3

INSERT INTO suppliers
(supplier_name, company_name, email, phone_number, address, location, distance)
VALUES
    ('Ram Sharma', 'Sharma Traders', 'ip5746915@gmail.com', '9811111111',
     'Kathmandu-10', 'Kathmandu', '5 KM'),

    ('Sita Rai', 'Rai Suppliers', 'sita@gmail.com', '9822222222',
     'Pokhara-8', 'Pokhara', '12 KM'),

    ('Hari Thapa', 'Thapa Wholesale', 'hari@gmail.com', '9833333333',
     'Lalitpur-5', 'Lalitpur', '7 KM'),

    ('Gita Karki', 'Karki Distributors', 'gita@gmail.com', '9844444444',
     'Bhaktapur-2', 'Bhaktapur', '9 KM'),

    ('Bikash KC', 'KC Suppliers', 'bikash@gmail.com', '9855555555',
     'Butwal-6', 'Butwal', '15 KM'),

    ('Anita Gurung', 'Gurung Enterprises', 'anita@gmail.com', '9866666666',
     'Chitwan-4', 'Chitwan', '20 KM');