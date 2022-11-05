USE labor_sql;

SELECT DISTINCT maker
FROM product
WHERE type = 'PC' and maker = SOME(SELECT maker FROM product WHERE type = 'Laptop');