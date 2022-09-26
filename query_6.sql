USE labor_sql;

SELECT DATE_FORMAT(date, "%d.%m.%Y")
AS new_date_format FROM income;