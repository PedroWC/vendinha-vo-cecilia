CREATE DATABASE IF NOT EXISTS vendinha_db;

USE vendinha_db;

-- Tabela: products
CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    price DOUBLE,
    quantity_in_stock INT,
    image MEDIUMBLOB
);

-- Tabela: users
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    UNIQUE KEY email_unique (email)
);