USE labor_sql;

SELECT maker, product.model, product.type, 
CASE
	WHEN product.model = pc.model THEN pc.price
    WHEN product.model = laptop.model THEN laptop.price
    WHEN product.model = printer.model THEN printer.price
END AS price
FROM product, pc, laptop, printer
WHERE maker='B' AND (product.model = pc.model OR product.model = laptop.model OR product.model = printer.model)
GROUP BY product.model;
