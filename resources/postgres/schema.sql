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