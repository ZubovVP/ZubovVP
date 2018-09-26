--create table type
CREATE TABLE types(
id SERIAL primary key,
name VARCHAR(50) NOT NULL
);

--create table products
CREATE TABLE products(
id SERIAL primary key,
name VARCHAR(40) NOT NULL,
type_id INT references types(id),
expired_date DATE NOT NULL,
price INT NOT NULL 
);

--insert counts in the table of types
INSERT INTO types(name) VALUES('СЫР');
INSERT INTO types(name) VALUES('МОЛОКО');
INSERT INTO types(name) VALUES('ХЛЕБ');
INSERT INTO types(name) VALUES('ВОДА');
INSERT INTO types(name) VALUES('МОРОЖЕНОЕ');

--insert counts in the products of types
INSERT INTO products(name, type_id, expired_date, price) VALUES('M', 11, '2018-09-30', 60);
INSERT INTO products(name, type_id, expired_date, price) VALUES('Домик_в_деревне', 11, '2018-10-15', 65);
INSERT INTO products(name, type_id, expired_date, price) VALUES('Простоквашено', 11, '2018-09-28', 56);
INSERT INTO products(name, type_id, expired_date, price) VALUES('Пломбир', 14, '2019-09-28', 25);
INSERT INTO products(name, type_id, expired_date, price) VALUES('Стаканчик', 14, '2019-10-28', 20);
INSERT INTO products(name, type_id, expired_date, price) VALUES('Сыр_Российский', 10, '2018-10-28', 250);
INSERT INTO products(name, type_id, expired_date, price) VALUES('Сыр_Голланский', 10, '2018-11-28', 350);
INSERT INTO products(name, type_id, expired_date, price) VALUES('Батон', 12, '2018-09-30', 24);
INSERT INTO products(name, type_id, expired_date, price) VALUES('Бородинский', 12, '2018-09-30', 30);
INSERT INTO products(name, type_id, expired_date, price) VALUES('Святой_источник', 13, '2020-09-30', 22);
INSERT INTO products(name, type_id, expired_date, price) VALUES('Агуша', 13, '2019-05-30', 45);
INSERT INTO products(name, type_id, expired_date, price) VALUES('Мороженое_сливочное', 14, '2019-10-28', 25);

--1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT * FROM products WHERE type_id = 10;

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT * FROM products WHERE name LIKE '%Мороженое%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM products WHERE '2018-10-25' >= expired_date;

--4. Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM products WHERE price = (SELECT MAX(price) FROM products);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT COUNT(*) FROM products WHERE type_id=10;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT * FROM products WHERE type_id = 10 OR type_id = 11;

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 3 штук. 
SELECT type_id, COUNT(*) AS ModelsCount
FROM products
GROUP BY type_id
HAVING COUNT(*) < 3;
  
--8. Вывести все продукты и их тип.
SELECT p.name, t.name FROM products as p INNER JOIN types as t  on t.id = type_id;



