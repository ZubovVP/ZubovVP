
CREATE TABLE users (
id SERIAL PRIMARY KEY,
name_user VARCHAR(30) NOT NULL
);

CREATE TABLE devices (
id SERIAL PRIMARY KEY,
name_device VARCHAR(30) NOT NULL
);

CREATE TABLE type_operation (
id SERIAL PRIMARY KEY,
name_type VARCHAR(30) NOT NULL
);

CREATE TABLE operations (
id SERIAL PRIMARY KEY,
id_device INT references devices(id) NOT NULL,
id_user INT references users(id) NOT NULL,
id_type INT references type_operation(id) NOT NULL,
amount INT NOT NULL,
date DATE NOT NULL
);


INSERT INTO users(name_user) VALUES('user_1');


INSERT INTO devices(name_device) VALUES('device_1');



INSERT INTO type_operation(name_type) VALUES('scan');
INSERT INTO type_operation(name_type) VALUES('print');
INSERT INTO type_operation(name_type) VALUES('fax');
INSERT INTO type_operation(name_type) VALUES('copy');



INSERT INTO operations(id_device, id_user, id_type, amount,  date) VALUES(1, 1, 2, 10, '1.01.2001');

SELECT o.id, d.name_device, u.name_user, t.name_type, o.amount, o.date FROM operations AS o
INNER JOIN devices AS d ON o.id_device = d.id
INNER JOIN users AS u ON o.id_user = u.id
INNER JOIN type_operation AS t ON o.id_type = t.id
