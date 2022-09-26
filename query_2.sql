USE labor_sql;

SELECT name 
FROM passenger
WHERE substring_index(name, ' ', -1) NOT LIKE 'J%'; 