CREATE TABLE car_bodies (
id SERIAL PRIMARY KEY,
name VARCHAR(30) NOT NULL
);

CREATE TABLE transmissions (
id SERIAL PRIMARY KEY,
name VARCHAR(30) NOT NULL
);

CREATE TABLE engines (
id SERIAL PRIMARY KEY,
name VARCHAR(30) NOT NULL
);

CREATE TABLE cars (
id SERIAL PRIMARY KEY,
name VARCHAR(30) NOT NULL,
id_body_car INT references car_bodies(id) NOT NULL,
id_transmission INT references transmissions(id) NOT NULL,
id_engine INT references engines(id) NOT NULL
);

INSERT INTO car_bodies(name) VALUES('car_body_1');
INSERT INTO car_bodies(name) VALUES('car_body_2');
INSERT INTO car_bodies(name) VALUES('car_body_3');
INSERT INTO car_bodies(name) VALUES('car_body_4');
INSERT INTO car_bodies(name) VALUES('car_body_5');

INSERT INTO transmissions(name) VALUES('transmission_1');
INSERT INTO transmissions(name) VALUES('transmission_2');
INSERT INTO transmissions(name) VALUES('transmission_3');
INSERT INTO transmissions(name) VALUES('transmission_4');
INSERT INTO transmissions(name) VALUES('transmission_5');
INSERT INTO transmissions(name) VALUES('transmission_6');


INSERT INTO engines(name) VALUES('engine_1');
INSERT INTO engines(name) VALUES('engine_2');
INSERT INTO engines(name) VALUES('engine_3');
INSERT INTO engines(name) VALUES('engine_4');
INSERT INTO engines(name) VALUES('engine_5');
INSERT INTO engines(name) VALUES('engine_6');
INSERT INTO engines(name) VALUES('engine_7');


INSERT INTO cars(name, id_body_car, id_transmission, id_engine) VALUES('car_1', 1, 1, 1);
INSERT INTO cars(name, id_body_car, id_transmission, id_engine) VALUES('car_2', 2, 2, 2);
INSERT INTO cars(name, id_body_car, id_transmission, id_engine) VALUES('car_3', 3, 3, 3);
INSERT INTO cars(name, id_body_car, id_transmission, id_engine) VALUES('car_4', 3, 3, 3);

--Вывести список всех машин и все привязанные к ним детали
SELECT c.name, b.name, t.name, e.name  FROM cars AS c LEFT OUTER JOIN car_bodies AS b ON c.id_body_car = b.id
LEFT OUTER JOIN transmissions AS t ON t.id = c.id_transmission
LEFT OUTER JOIN engines AS e ON e.id = c.id_engine;


--Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач
select car_bodies.name, transmissions.name, engines.name from car_bodies
FULL OUTER  JOIN   cars on cars.id_body_car=car_bodies.id
FULL OUTER  JOIN transmissions ON cars.id_transmission = transmissions.id 
FULL OUTER  JOIN engines on engines.id=cars.id_engine
WHERE cars.id is NULL;
