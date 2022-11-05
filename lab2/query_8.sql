USE labor_sql;

SELECT @var:=maker AS maker, (SELECT COUNT(*) FROM product WHERE type='PC' AND maker = @var) AS pc,
	(SELECT COUNT(*) FROM product WHERE type='Printer' AND maker = @var) AS printer,
    (SELECT COUNT(*) FROM product WHERE type='Laptop' AND maker = @var) AS laptop
FROM product
GROUP BY maker
ORDER BY maker;