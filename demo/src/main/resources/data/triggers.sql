USE new_post_lab5;

DROP TRIGGER IF EXISTS phone_number_not_zeros;
DROP TRIGGER IF EXISTS only_these_names;
DROP TRIGGER IF EXISTS courier_was_hired;
DROP TRIGGER IF EXISTS check_unique_id;
DROP TRIGGER IF EXISTS check_available_update;
DROP TRIGGER IF EXISTS check_available_delete;

DELIMITER //

CREATE TRIGGER phone_number_not_zeros
    BEFORE INSERT
    ON operator
    FOR EACH ROW
BEGIN
    IF (NEW.phone_number REGEXP("00$")) THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Operators can not have phone numbers with two 0 in the end";
    END IF;
END //


CREATE TRIGGER only_these_names
    BEFORE INSERT
    ON client
    FOR EACH ROW
BEGIN
    IF (NEW.name NOT REGEXP("(Igor|Oleg|Yana)")) THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Not valid name";
    END IF;
END //


CREATE TRIGGER courier_was_hired
    AFTER INSERT
    ON courier
    FOR EACH ROW
BEGIN
    INSERT INTO courier_got_hired(courier_id, time_stamp) VALUES (NEW.id, NOW());
END //


CREATE TRIGGER check_unique_id
    BEFORE INSERT
    ON client
    FOR EACH ROW
BEGIN
    DECLARE done BOOLEAN DEFAULT FALSE;
    DECLARE new_client_phone_number VARCHAR(12);
    DECLARE cursor_client CURSOR FOR SELECT id FROM client;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cursor_client;

    my_loop: LOOP
        FETCH cursor_client INTO new_client_phone_number;
        IF done = TRUE THEN LEAVE my_loop;
        END IF;

        IF NEW.phone_number = new_client_phone_number THEN
            SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Client with this id already exists";
        END IF;
    END LOOP;
    CLOSE cursor_client;
END //


CREATE TRIGGER check_available_update
    BEFORE UPDATE
    ON client
    FOR EACH ROW
BEGIN
    DECLARE done BOOLEAN DEFAULT FALSE;
    DECLARE update_sender_client_id BIGINT;
    DECLARE update_receiver_client_id BIGINT;
    DECLARE cursor_client CURSOR FOR SELECT sender_client_id, receiver_client_id FROM orders;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cursor_client;

    update_loop: LOOP
        FETCH cursor_client INTO update_sender_client_id, update_receiver_client_id;
        IF done = TRUE THEN LEAVE update_loop;
        END IF;

        IF (OLD.id = update_sender_client_id OR OLD.id = update_receiver_client_id) THEN
            SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "This client is used in orders table";
        END IF;
    END LOOP;
    CLOSE cursor_client;
END //


CREATE TRIGGER check_available_delete
    BEFORE DELETE
    ON client
    FOR EACH ROW
BEGIN
    DECLARE done BOOLEAN DEFAULT FALSE;
    DECLARE update_sender_client_id BIGINT;
    DECLARE update_receiver_client_id BIGINT;
    DECLARE cursor_client CURSOR FOR SELECT sender_client_id, receiver_client_id FROM orders;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cursor_client;

    delete_loop: LOOP
        FETCH cursor_client INTO update_sender_client_id, update_receiver_client_id;
        IF done = TRUE THEN LEAVE delete_loop;
        END IF;

        IF (OLD.id = update_sender_client_id OR OLD.id = update_receiver_client_id) THEN
            SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "This client is used in orders table";
        END IF;
    END LOOP;
CLOSE cursor_client;
END //
