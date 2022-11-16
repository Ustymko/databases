USE new_post_lab5;
DROP FUNCTION IF EXISTS max_parcel_price;
DELIMITER //
CREATE FUNCTION max_parcel_price(some_client_id BIGINT) RETURNS double
    DETERMINISTIC
BEGIN
	DECLARE max_price DOUBLE;
SELECT MAX(parcel_price) INTO max_price FROM orders WHERE receiver_client_id = some_client_id;
RETURN max_price;
END //
