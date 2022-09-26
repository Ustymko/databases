USE labor_sql;

SELECT name, numGuns, bore, displacement, type,
country, launched, classes.class FROM ships, classes
WHERE ships.class = classes.class AND
CASE WHEN numGuns = 9 THEN 1 ELSE 0 END +
CASE WHEN bore = 16 THEN 1 ELSE 0 END +
CASE WHEN displacement = 46000 THEN 1 ELSE 0 END +
CASE WHEN type = 'bb' THEN 1 ELSE 0 END +
CASE WHEN country = 'Japan' THEN 1 ELSE 0 END +
CASE WHEN launched = 1916 THEN 1 ELSE 0 END +
CASE WHEN ships.class = 'Revenge' THEN 1 ELSE 0 END
>=3;

SELECT name, numGuns, bore, displacement, type,
country, launched, classes.class FROM ships
JOIN classes ON ships.class = classes.class
WHERE
CASE WHEN numGuns = 9 THEN 1 ELSE 0 END +
CASE WHEN bore = 16 THEN 1 ELSE 0 END +
CASE WHEN displacement = 46000 THEN 1 ELSE 0 END +
CASE WHEN type = 'bb' THEN 1 ELSE 0 END +
CASE WHEN country = 'Japan' THEN 1 ELSE 0 END +
CASE WHEN launched = 1916 THEN 1 ELSE 0 END +
CASE WHEN ships.class = 'Revenge' THEN 1 ELSE 0 END
>=3;