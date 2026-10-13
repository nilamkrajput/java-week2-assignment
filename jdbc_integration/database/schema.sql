CREATE DATABASE IF NOT EXISTS employee_management;

USE employee_management;

CREATE TABLE IF NOT EXISTS employees (
                                         id INT PRIMARY KEY AUTO_INCREMENT,
                                         name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    department VARCHAR(100) NOT NULL,
    designation VARCHAR(100),
    salary DECIMAL(10,2),
    phone VARCHAR(20),
    joining_date DATE
    );