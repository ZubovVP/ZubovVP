--create database
create database system_of_applications;

--create table Categories
create table categories (
	id serial primary key,
	name_category varchar(200) NOT NULL,
	description text
);

--create table Users
create table users (
	id serial primary key,
	login character varying (100) NOT NULL,
	password character varying (100) NOT NULL,
	last_name varchar(50) NOT NULL,
	first_name varchar(50),
	create_date date 
);

--create table Items
create table items (
	id serial primary key,
	name character varying(100) NOT NULL,
	size character varying(50) NOT NULL,
	weight numeric(5,2) NOT NULL,
	id_category integer references categories(id),
	user_id integer references users(id) UNIQUE
);

--create table Roles
create table roles (
	id serial primary key,
	name varchar(100) NOT NULL,
	description text NOT NULL
);

--create table Users_Roles
create table users_roles(
	id serial primary key,
	id_user integer references users(id)NOT NULL,
	id_roles integer references roles(id) NOT NULL
);

--create table rules
create table rules(
	id serial primary key,
	name varchar(200) NOT NULL,
	description text NOT NULL
);

--create table Roles_Rules
create table roles_rules(
	id serial primary key,
	id_role integer NOT NULL,
	id_rule integer Not NULL
);

--crete table Statuses
create table statuses(
	id serial primary key,
	name varchar(100) NOT NULL,
	description text NOT NULL,
	id_item integer references items(id) NOT NULL
);

--create table Attach 
create table attach(
	id serial primary key,
	image varchar 
);

--create table Comments
create table comments(
	id serial primary key,
	id_item integer references items(id),
	text text NOT NULL,
	time_publicate timestamp NOT NULL
);
