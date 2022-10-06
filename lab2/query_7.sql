USE labor_sql;

SELECT model, COUNT(*) AS amount,
CASE
	WHEN AVG(price) < 800 THEN AVG(price)
END AS avg_price
FROM pc
GROUP BY model;

SELECT model, COUNT(*) AS amount, AVG(price) AS avg_price
FROM pc
GROUP BY model
HAVING AVG(price) < 800;