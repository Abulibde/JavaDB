CREATE DATABASE `Table Relations`;
USE `Table Relations`;

CREATE TABLE `people`
(
    `person_id`   INT AUTO_INCREMENT PRIMARY KEY,
    `first_name`  VARCHAR(50),
    `salary`      DECIMAL(10, 2),
    `passport_id` INT UNIQUE
);

CREATE TABLE `passports`
(
    `passport_id` INT AUTO_INCREMENT PRIMARY KEY,
    `passport_number` VARCHAR(255)
);

