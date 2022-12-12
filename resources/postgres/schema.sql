CREATE DATABASE lestro;
CREATE SCHEMA lestro;
CREATE TABLE lestro.customers (
    id serial PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    mail VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(200) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE lestro.address_data (
    id serial PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    mail VARCHAR(50) UNIQUE NOT NULL,
    street VARCHAR(30),
    house_no VARCHAR(5),
    apartment_no VARCHAR(5),
    zip_code VARCHAR(6),
    city VARCHAR(30)
);
CREATE TABLE lestro.employees (
    id serial PRIMARY KEY,
    mail VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(200) NOT NULL,
    authority VARCHAR(50) NOT NULL
);
--pwd: zaq12wsx
INSERT INTO lestro.employees (id, mail, password, authority)
    VALUES (1, 'jan.kowalski@lestro.pl', '$2a$10$5eyQNa99eiAXREIGlNWw6eqfDCncquVPLigsPrYabuBe3/43idCRC', 'EMPLOYEE');
--pwd: zaq13edc
INSERT INTO lestro.employees (id, mail, password, authority)
    VALUES (2, 'joanna.nowak@lestro.pl', '$2a$10$df9cAfyYyjZNRCn/VxLoveUTK06tEi9YFN8fKZswfeczeHkBsd34O', 'EMPLOYEE');
--pwd: zaq14rfv
INSERT INTO lestro.employees (id, mail, password, authority)
    VALUES (3, 'krzysztof.kowalczyk@lestro.pl', '$2a$10$84Tdbs/0C57UR/KxBJjA/u20yrEzmXaSx3OA6LpDi8QHTbWJkCZPO', 'EMPLOYEE');