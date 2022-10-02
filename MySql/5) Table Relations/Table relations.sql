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
    `passport_id`     INT AUTO_INCREMENT PRIMARY KEY,
    `passport_number` VARCHAR(255)
);

# passports
# passport_id	passport_number
#    101            N34FG21B
#    102	        K65LO4R7
#    103	        ZE657QP2
INSERT INTO `passports`(`passport_id`, `passport_number`)
VALUES (101, 'N34FG21B'),
       (102, 'K65LO4R7'),
       (103, 'ZE657QP2');
