
--create table Categories
CREATE TABLE categories (
	id serial PRIMARY KEY,
	name_category VARCHAR(200) NOT NULL,
	description text
);

--create table Users
CREATE TABLE users (
	id serial PRIMARY KEY,
	login CHARACTER VARYING(100) NOT NULL,
	password CHARACTER VARYING(100) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	first_name VARCHAR(50),
	create_date DATE
);

--create table Items
CREATE TABLE items (
	id serial PRIMARY KEY,
	name CHARACTER VARYING(100) NOT NULL,
	dimensions CHARACTER VARYING(50) NOT NULL,
	weight NUMERIC(5,2) NOT NULL,
	id_category INTEGER REFERENCES categories(id),
	user_id INTEGER REFERENCES users(id) UNIQUE
);

--create table Roles
CREATE TABLE roles (
	id serial PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	description text NOT NULL
);

--create table Users_Roles
CREATE TABLE users_roles(
	id serial PRIMARY KEY,
	id_user INTEGER REFERENCES users(id)NOT NULL,
	id_roles INTEGER REFERENCES roles(id) NOT NULL
);

--create table rules
CREATE TABLE rules(
	id serial PRIMARY KEY,
	name varchar(200) NOT NULL,
	description text NOT NULL
);

--create table Roles_Rules
CREATE TABLE roles_rules(
	id serial PRIMARY KEY,
	id_role INTEGER NOT NULL,
	id_rule INTEGER Not NULL
);

--crete table Statuses
CREATE TABLE statuses(
	id serial PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	description text NOT NULL,
	id_item INTEGER REFERENCES items(id) NOT NULL
);

--create table Attach 
CREATE TABLE attach(
	id serial PRIMARY KEY,
	image BLOB
);

--create table Comments
CREATE TABLE comments(
	id serial PRIMARY KEY,
	id_item INTEGER REFERENCES items(id),
	text text NOT NULL,
	time_publicate TIMESTAMP NOT NULL
);
