/* CREATE TABLE company
(
id integer NOT NULL,
name character varying,
CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
id integer NOT NULL,
name character varying,
company_id integer,
CONSTRAINT person_pkey PRIMARY KEY (id)
); */


--names of all persons that are NOT in the company with id = 5
SELECT p.name, c.name FROM person AS p
LEFT JOIN company AS c
ON p.company_id = c.id
WHERE c.id != 5;

--company name for each person
SELECT p.name, c.name FROM person AS p
LEFT JOIN company AS c
ON p.company_id = c.id


--Select the name of the company with the maximum number of persons + number of persons in this company
SELECT c.name, count(p.company_id) FROM company AS c
LEFT JOIN person AS p ON c.id = p.company_id
GROUP BY c.name
ORDER BY  count(p.company_id) DESC LIMIT 1