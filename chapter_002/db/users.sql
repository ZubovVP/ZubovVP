CREATE TABLE users (
id SERIAL PRIMARY KEY,
name VARCHAR(25) NOT NULL,
description TEXT NOT NULL,
create_date TIMESTAMP,
id_item VARCHAR(15)
); 