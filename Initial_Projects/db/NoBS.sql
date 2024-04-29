-- create database nobs;

use nobs;

CREATE TABLE product (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    price double,
    quantity int
);

