--Task - delete duplicates from table.

--Create table.
CREATE TABLE users(
id SERIAL PRIMARY KEY,
name VARCHAR(20));

--Add elements to the table.
INSERT INTO users(name) VALUES('Duke');
INSERT INTO users(name) VALUES('Alex');
INSERT INTO users(name) VALUES('Duke');
INSERT INTO users(name) VALUES('Kate');
INSERT INTO users(name) VALUES('Duke');


--Decision #1 - delete all duplicates from the table.
DELETE FROM users WHERE name = (SELECT name
                     FROM users
                     group by name
                     HAVING count(*) > 1);

---Decision №2 (create table and add unique elements).

--Create table
CREATE TABLE user1(
name VARCHAR(20)
);

--Add unique in the user1 from users.
INSERT INTO user1 (name) SELECT DISTINCT users.name FROM users;

--Clear users.
DELETE FROM users

--Copy elements in the users from user1.
INSERT INTO users (name) SELECT name FROM user1

-- Drop user1.
Drop Table user1
                   