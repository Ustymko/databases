DROP DATABASE IF EXISTS new_post_lab5;
CREATE DATABASE new_post_lab5;
USE new_post_lab5;


-- Dropping tables

DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS `courier`;
DROP TABLE IF EXISTS `operator`;
DROP TABLE IF EXISTS `department`;
DROP TABLE IF EXISTS `client`;


-- Creating tables

CREATE TABLE `client`(
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(35) NOT NULL,
                         surname VARCHAR(35) NOT NULL,
                         phone_number VARCHAR(20) NOT NULL,
                         address VARCHAR(70) NULL
);

CREATE TABLE `department`(
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             address VARCHAR(50) NOT NULL,
                             number INT NOT NULL,
                             city VARCHAR(30) NOT NULL,
                             region VARCHAR(30) NOT NULL
);

CREATE TABLE `operator`(
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           department_id BIGINT NOT NULL,
                           name VARCHAR(30) NOT NULL,
                           surname VARCHAR(30) NOT NULL,
                           phone_number VARCHAR(15) NOT NULL
);

CREATE TABLE `courier`(
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          department_id BIGINT NOT NULL,
                          name VARCHAR(30) NOT NULL,
                          surname VARCHAR(30) NOT NULL,
                          phone_number VARCHAR(15) NOT NULL
);

CREATE TABLE orders(
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        sender_client_id BIGINT NOT NULL,
                        receiver_client_id BIGINT NOT NULL,
                        sender_department_id BIGINT NOT NULL,
                        receiver_department_id BIGINT NOT NULL,
                        sender_operator_id BIGINT NOT NULL,
                        receiver_operator_id BIGINT NOT NULL,
                        courier_between_departments_id BIGINT NOT NULL,
                        courier_on_spot_id BIGINT NOT NULL,
                        sending_datetime DATETIME NOT NULL,
                        receiving_datetime DATETIME NOT NULL,
                        parcel_price FLOAT NOT NULL,
                        delivery_price FLOAT NOT NULL
);


-- Altering tables

ALTER TABLE `department`
    ADD INDEX number_index (number);

ALTER TABLE `operator`

	ADD CONSTRAINT fk_department_oper
		FOREIGN KEY(department_id)
		REFERENCES department(id);

ALTER TABLE `courier`

	ADD CONSTRAINT fk_department_cour
		FOREIGN KEY(department_id)
		REFERENCES department(id);

ALTER TABLE orders

	ADD CONSTRAINT fk_client_1
		FOREIGN KEY(sender_client_id)
		REFERENCES client(id),
	ADD CONSTRAINT fk_client_2
		FOREIGN KEY(receiver_client_id)
		REFERENCES client(id),
	ADD CONSTRAINT fk_department_1
		FOREIGN KEY(sender_department_id)
		REFERENCES department(id),
	ADD CONSTRAINT fk_department_2
		FOREIGN KEY(receiver_department_id)
		REFERENCES department(id),
	ADD CONSTRAINT fk_operator_1
		FOREIGN KEY(sender_operator_id)
		REFERENCES operator(id),
	ADD CONSTRAINT fk_operator_2
		FOREIGN KEY(receiver_operator_id)
		REFERENCES operator(id),
    ADD CONSTRAINT fk_courier_1
        FOREIGN KEY(courier_between_departments_id)
            REFERENCES courier(id),
    ADD CONSTRAINT fk_courier_2
        FOREIGN KEY(courier_on_spot_id)
            REFERENCES courier(id);


-- Inserting data

INSERT INTO `client` (name, surname, phone_number, address)
VALUES
    ("Andrei", "Gates", "+1 582-500-6235", "358 Clark Street"),
    ("Fardeen", "Krueger", "+1 810-327-6243", "1375 Romines Mill Road"),
    ("Cindy", "Haynes", "+1 582-400-9186", "4280 Tully Street"),
    ("Akshay", "Vinson", "+1 220-691-2875", "1626 Steve Hunt Road"),
    ("Bertha", "Barlow", "+1 812-330-7049", "1867 Progress Way"),
    ("Samah", "Bone", "+1 215-904-9487", "2569 Richison Drive"),
    ("Zainab", "Broadhurst", "+1 301-372-5426", "4844 Grove Avenue"),
    ("Martin", "Lennon", "+1 218-428-9994", "1563 Bombardier Way"),
    ("Piper", "Delaney", "+1 505-644-4765", "2626 Railroad Street"),
    ("Fariha", "Harrington", "+1 407-632-1949", "2055 Elk Rd Little"),
    ("Sharna", "Mccaffrey", "+1 582-682-4203", "187 Bingamon Branch Road");

INSERT INTO `department` (address, number, city, region)
VALUES
    ("Uhorska, 22", 3, "Lviv", "Lviv state"),
    ("Truskavetska, 15", 4, "Lviv", "Lviv state"),
    ("Chornovola, 16d", 13, "Lviv", "Lviv state"),
    ("Poshtova, 5", 3, "Stryi", "Lviv state"),
    ("Pryvokzalna, 12", 32, "Kyiv", "Kyiv state"),
    ("Verhovynna, 69", 4, "Kyiv", "Kyiv state"),
    ("Biloruska, 28a", 24, "Kyiv", "Kyiv state"),
    ("Novooskolska, 6a", 5, "Irpin", "Kyiv state"),
    ("Ruska, 15", 2, "Ternopil", "Ternopil state"),
    ("Kyivska, 21", 8, "Rivne", "Rivne state");

INSERT INTO `operator` (department_id, name, surname, phone_number)
VALUES
    (1, "Hamaad", "Browne", "+1 205-736-6804"),
    (2, "Ariah", "Coleman", "+1 505-683-2634"),
    (6, "Sanya", "Broughton", "+1 229-950-1449"),
    (3, "Jerry", "Justice", "+1 582-282-9842"),
    (4, "Amna", "Terrell", "+1 505-783-6694"),
    (9, "Umaima", "Joyce", "+1 505-644-8818"),
    (10, "Madelaine", "Levine", "+1 582-300-8820"),
    (5, "Marcel", "Allison", "+1 331-482-8888"),
    (8, "Naveed", "Shaw", "+1 231-777-2245");

INSERT INTO `courier` (department_id, name, surname, phone_number)
VALUES
    (1, "Neha", "Whyte", "+1 205-778-7415"),
    (2, "Rodney", "Goulding", "+1 582-222-1233"),
    (3, "Kevin", "Stanton", "+1 206-982-5429"),
    (4, "Stanley", "Herman", "+1 201-879-6758"),
    (5, "Ralph", "Daniels", "+1 225-268-8459"),
    (7, "Imani", "Hartman", "+1 582-842-6831"),
    (8, "Zeynep", "Clements", "+1 505-574-8531"),
    (9, "Briony", "Thorne", "+1 252-213-1851");

INSERT INTO orders (sender_client_id, receiver_client_id,
                     sender_department_id, receiver_department_id,
                     sender_operator_id, receiver_operator_id,
                     courier_between_departments_id, courier_on_spot_id,
                     sending_datetime, receiving_datetime,
                     parcel_price, delivery_price)
VALUES
    (1, 4, 1, 4, 1, 5, 1, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 1 DAY, 100, 20),
    (9, 10, 4, 5, 5, 8, 4, 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 1 DAY, 150, 45),
    (7, 5, 7, 9, 1, 2, 8, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 1 DAY, 2400, 180),
    (5, 6, 9, 3, 6, 4, 1, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 2 DAY, 500, 78),
    (6, 8, 2, 8, 2, 9, 2, 8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 1 DAY + INTERVAL 15 MINUTE, 750, 90),
    (10, 3, 2, 8, 2, 9, 2, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 3 DAY, 1000, 150),
    (9, 1, 4, 9, 5, 9, 4, 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 3 DAY + INTERVAL 15 MINUTE, 640, 80),
    (8, 10, 7, 9, 1, 2, 8, 6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 1 DAY + INTERVAL 15 MINUTE, 300, 49),
    (5, 10, 4, 9, 5, 9, 4, 8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 2 DAY + INTERVAL 15 MINUTE, 1200, 210);
