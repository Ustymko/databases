USE labor_sql;

SELECT maker 
FROM pc, product 
WHERE (pc.model = product.model AND 
maker = SOME(SELECT maker FROM product WHERE type = 'Printer') 
AND speed = (SELECT MAX(speed) FROM pc));