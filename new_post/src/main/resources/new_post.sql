CREATE DATABASE IF NOT EXISTS new_post;
USE new_post;


-- Dropping tables

DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `courier`;
DROP TABLE IF EXISTS `operator`;
DROP TABLE IF EXISTS `department`;
DROP TABLE IF EXISTS `city`;
DROP TABLE IF EXISTS `region`;
DROP TABLE IF EXISTS `account`;
DROP TABLE IF EXISTS `client`;


-- Creating tables

CREATE TABLE `client`(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(35) NOT NULL,
    surname VARCHAR(35) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    address VARCHAR(70) NULL
);

CREATE TABLE `account`(
	client_id BIGINT PRIMARY KEY,
    username VARCHAR(35) NOT NULL,
    password VARCHAR(35) NOT NULL,
    email_address VARCHAR(35) NOT NULL
);

CREATE TABLE `region`(
	region VARCHAR(30) PRIMARY KEY
);

CREATE TABLE `city`(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
	city VARCHAR(30) NOT NULL,
	region_name VARCHAR(30) NOT NULL
);

CREATE TABLE `department`(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    city_id BIGINT NOT NULL,
    address VARCHAR(50) NOT NULL,
    number INT NOT NULL
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

CREATE TABLE `order`(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sender_client_id BIGINT NOT NULL,
    receiver_client_id BIGINT NOT NULL,
    sender_department_id BIGINT NOT NULL,
    receiver_department_id BIGINT NOT NULL,
    sender_operator_id BIGINT NOT NULL,
    receiver_operator_id BIGINT NOT NULL,
    courier_between_departments_id BIGINT NOT NULL,
    courier_on_spot_id BIGINT DEFAULT NULL,
    planned_sending_datetime DATETIME NOT NULL,
    planned_receiving_datetime DATETIME NOT NULL,
    actual_sending_datetime DATETIME NULL,
    actual_receiving_datetime DATETIME NULL,
    parcel_price FLOAT NOT NULL,
    delivery_price FLOAT NOT NULL,
    total_price FLOAT AS (parcel_price + delivery_price)
);


-- Altering tables

ALTER TABLE `account`
	ADD UNIQUE INDEX username_index (username),

	ADD CONSTRAINT fk_client
		FOREIGN KEY(client_id)
		REFERENCES client(id);

ALTER TABLE `city`
	ADD CONSTRAINT fk_region
		FOREIGN KEY(region_name)
		REFERENCES region(region);

ALTER TABLE `department`
	ADD INDEX city_index (city_id),
    ADD INDEX number_index (number),

	ADD CONSTRAINT fk_city
		FOREIGN KEY(city_id)
		REFERENCES city(id);

ALTER TABLE `operator`
	ADD INDEX operators_department_index (department_id),

	ADD CONSTRAINT fk_department_oper
		FOREIGN KEY(department_id)
		REFERENCES department(id);

ALTER TABLE `courier`
	ADD INDEX couriers_department_index (department_id),

	ADD CONSTRAINT fk_department_cour
		FOREIGN KEY(department_id)
		REFERENCES department(id);

ALTER TABLE `order`
	ADD INDEX sender_index (sender_client_id),
    ADD INDEX receiver_index (receiver_client_id),
    ADD INDEX sender_dep_index (sender_department_id),
    ADD INDEX receiver_dep_index (receiver_department_id),
    ADD INDEX sender_oper_index (sender_operator_id),
    ADD INDEX receiver_oper_index (receiver_operator_id),

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
		REFERENCES operator(id);


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

INSERT INTO `account` (client_id, username, password, email_address)
VALUES
	(1, "Incandescent", "2&n35!ZKH5zU", "gihir26621@ishyp.com"),
    (2, "Belizard", "aJ64Uo!K8euW", "lionikur@kimsangun.com"),
    (3, "Alphastrike", "0S4ZbQs84X#I", "drozdanya@stackinglayers.com"),
    (4, "OgreMan", "&6lG1Bv4Jh%W", "njknjk@omdiaco.com"),
    (5, "Kerplunk", "GVOL0g10R2%3", "dstockto@yt-google.com"),
    (6, "Pelfox", "T%3C2uY#K7ii", "boeg11@btcmod.com"),
    (7, "Moonlighter", "4%aWj4851NHt", "q4yfupt3@newshnb.com"),
    (8, "RingRaid", "04GigRB#9oL7", "sachsejack@gasssmail.com"),
    (9, "WhistleStop", "KESuy49*7YL!", "arthurcshiba@btcmod.com"),
    (10, "ScarlettMama", "Fo59&o!Lz44r", "milyoshina@galvanitrieste.it"),
    (11, "AtomicX", "LF1*37v8dNem", "fatalreign@galvanitrieste.it");

INSERT INTO	`region` (region)
VALUES
	("Lviv state"),
    ("Kyiv state"),
    ("Ternopil state"),
    ("Rivne state");

INSERT INTO `city` (city, region_name)
VALUES
	("Lviv", "Lviv state"),
    ("Drohobych", "Lviv state"),
    ("Stryi", "Lviv state"),
    ("Kyiv", "Kyiv state"),
    ("Irpin", "Kyiv state"),
    ("Ternopil", "Ternopil state"),
    ("Rivne", "Rivne state");

INSERT INTO `department` (city_id, address, number)
VALUES
	(1, "Uhorska, 22", 3),
    (1, "Truskavetska, 15", 4),
    (1, "Chornovola, 16d", 13),
    (3, "Poshtova, 5", 3),
    (4, "Pryvokzalna, 12", 32),
    (4, "Verhovynna, 69", 4),
    (4, "Biloruska, 28a", 24),
    (5, "Novooskolska, 6a", 5),
    (6, "Ruska, 15", 2),
    (7, "Kyivska, 21", 8);

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

INSERT INTO `order` (sender_client_id, receiver_client_id,
					sender_department_id, receiver_department_id,
                    sender_operator_id, receiver_operator_id,
                    courier_between_departments_id, courier_on_spot_id,
                    planned_sending_datetime, planned_receiving_datetime,
                    actual_sending_datetime, actual_receiving_datetime,
                    parcel_price, delivery_price)
VALUES
	(1, 4, 1, 4, 1, 5, 1, DEFAULT, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 1 DAY,
							CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 1 DAY, 100, 20),
	(9, 10, 4, 5, 5, 8, 4, 5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 1 DAY,
							CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 1 DAY + INTERVAL 1 HOUR, 150, 45),
	(7, 5, 7, 9, 1, 2, 9, DEFAULT, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 1 DAY,
							CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 1 DAY + INTERVAL 15 MINUTE, 2400, 180),
	(5, 6, 9, 3, 6, 4, 1, DEFAULT, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 2 DAY,
							CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 2 DAY + INTERVAL 9 MINUTE, 500, 78),
	(6, 8, 2, 8, 2, 9, 2, 9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 1 DAY + INTERVAL 15 MINUTE,
							CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 1 DAY + INTERVAL 50 MINUTE, 750, 90),
	(10, 3, 2, 8, 2, 9, 2, DEFAULT, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 3 DAY,
							CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 3 DAY + INTERVAL 10 MINUTE, 1000, 150),
	(9, 1, 4, 9, 5, 9, 4, DEFAULT, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 3 DAY + INTERVAL 15 MINUTE,
							CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 3 DAY + INTERVAL 30 MINUTE, 640, 80),
	(8, 10, 7, 9, 1, 2, 9, DEFAULT, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 1 DAY + INTERVAL 15 MINUTE,
							CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 1 DAY + INTERVAL 20 MINUTE, 300, 49),
	(5, 10, 4, 9, 5, 9, 4, DEFAULT, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 2 DAY + INTERVAL 15 MINUTE,
							CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP() + INTERVAL 2 DAY + INTERVAL 45 MINUTE, 1200, 210);
