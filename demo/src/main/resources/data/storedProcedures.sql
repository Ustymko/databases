USE new_post_lab5;

DROP PROCEDURE IF EXISTS insert_client;
DROP PROCEDURE IF EXISTS insert_10_clients;
DROP PROCEDURE IF EXISTS find_max_parcel_price_for_client;
DROP PROCEDURE IF EXISTS dynamic_procedure;

DELIMITER //

CREATE PROCEDURE insert_client(client_name VARCHAR (35), client_surname VARCHAR (35),
                               client_phone_number VARCHAR (20), client_address VARCHAR (70))
BEGIN
    INSERT INTO `client` (name, surname, phone_number, address)
    VALUES (client_name, client_surname, client_phone_number, client_address);
END //


CREATE PROCEDURE insert_10_clients()
BEGIN
    DECLARE iterator INT DEFAULT 0;
    SET iterator = 1;
    WHILE iterator <= 10 DO
        INSERT INTO `client` (name, surname, phone_number, address)
        VALUES (CONCAT('Name', iterator), CONCAT('Surname', iterator),
                CONCAT('PhoneNumber', iterator), CONCAT('Address', iterator));
        SET iterator = iterator + 1;
    END WHILE;
END //


CREATE PROCEDURE find_max_parcel_price_for_client(receiver_client_id BIGINT)
BEGIN
    SELECT max_parcel_price(receiver_client_id) AS max_price;
END //


CREATE PROCEDURE dynamic_procedure()
BEGIN
    DECLARE isDone BOOLEAN DEFAULT false;

    DECLARE some_address VARCHAR(50);
    DECLARE some_number INT;
    DECLARE some_city VARCHAR(30);
    DECLARE some_region VARCHAR(30);

    DECLARE department_cursor CURSOR
    FOR SELECT address, number, city, region FROM department;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET isDone = TRUE;
    OPEN department_cursor;

    data_loop: LOOP
                FETCH department_cursor INTO some_address, some_number, some_city, some_region;
                IF isDone = TRUE THEN
                    LEAVE data_loop;
                END IF;
                SET @createDB = CONCAT('CREATE DATABASE ', some_city, some_number);
    PREPARE query1 FROM @createDB;
    EXECUTE query1;
    DEALLOCATE PREPARE query1;
    END LOOP;
    CLOSE department_cursor;
END //
