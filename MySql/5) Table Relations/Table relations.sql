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


#                   people
# person_id	        first_name	        salary	    passport_id
#   1  	            Roberto            	43300.00	    102
#   2	            Tom             	56100.00	    103
#   3	            Yana	            60200.00	    101
INSERT INTO `people` (`person_id`, `first_name`, `salary`, `passport_id`)
VALUES (1, 'Roberto', 43300.00, 102),
       (2, 'Tom', 56100.00, 103),
       (3, 'Yana', 60200.00, 101);

ALTER TABLE `people`
    ADD CONSTRAINT `fk_people_passports`
        FOREIGN KEY (`passport_id`)
            REFERENCES `passports` (passport_id);

CREATE TABLE `manufacturers`
(
    `manufacturers_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name`             VARCHAR(50),
    `established_on`   DATE
);

CREATE TABLE `models`
(
    `model_id`        INT PRIMARY KEY AUTO_INCREMENT,
    `name`            VARCHAR(50) UNIQUE,
    `manifactured_id` INT
);
