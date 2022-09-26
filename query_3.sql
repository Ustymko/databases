USE labor_sql;

SELECT DISTINCT maker
FROM product, laptop
WHERE product.model = laptop.model AND laptop.speed <= 500;

SELECT DISTINCT maker
FROM product JOIN laptop ON product.model = laptop.model
WHERE laptop.speed <= 500;