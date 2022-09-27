CREATE DATABASE `minions`;

#create tables in minions
USE `minions`;
CREATE TABLE `minions`
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    age  INT
);

USE `minions`;
CREATE TABLE `towns`
(
    town_id INT PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(255) NOT NULL
);

#change the name of the column Town_id to id
USE `minions`;
alter table towns
    change town_id `id` int auto_increment;

#alter table - set foreign key
USE `minions`;
ALTER TABLE `minions`
    ADD COLUMN `town_id` INT NOT NULL,
    ADD CONSTRAINT `fk_id_minions_towns`
        FOREIGN KEY (`town_id`)
            REFERENCES `towns` (`id`);

#Insert records in the tables
USE `minions`;
INSERT INTO `towns`(`id`, `name`)
VALUES (1, 'Sofia'),
       (2, 'Plovdiv'),
       (3, 'Varna');

USE `minions`;
INSERT INTO `minions`(`id`, `name`, `age`, `town_id`)
VALUES (1, 'Kevin', 22, 1),
       (2, 'Bob', 15, 3),
       (3, 'Steward', NULL, 2);

#Truncate table minions
TRUNCATE TABLE `minions`;

#Drop all tables
DROP TABLE `minions`;
DROP TABLE `towns`;

#Create table people
CREATE TABLE `people`
(
    `id`        INT PRIMARY KEY AUTO_INCREMENT,
    `name`      VARCHAR(200)   NOT NULL,
    `picture`   BLOB,
    `height`    DOUBLE(10, 2),
    `weight`    DOUBLE(10, 2),
    `gender`    ENUM ('m','f') NOT NULL,
    `birthdate` DATE           NOT NULL,
    `biography` TEXT
);

#Fill the table
INSERT INTO `people`(`name`, `gender`, `birthdate`)
VALUES ('Boris', 'm', DATE(NOW())),
       ('Nasko', 'm', DATE(NOW())),
       ('Peter', 'm', DATE(NOW())),
       ('Ivanka', 'f', DATE(NOW())),
       ('Chiko', 'm', DATE(NOW()));

#Create table users
CREATE TABLE `users`
(
    `id`              INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `username`        VARCHAR(30)                    NOT NULL,#required
    `password`        VARCHAR(26)                    NOT NULL,#required
    `profile_picture` BLOB,
    `last_login_time` TIME,
    `is_deleted`      BOOLEAN
);

#Fill the table
INSERT INTO `users`(`username`, `password`)
VALUES ('Vacho', '12345'),
       ('Ivailo', 'dsf'),
       ('Ivan', 'sdcfdsg'),
       ('Gosho', 'agggggg'),
       ('Pesho', 'dgggggddeee');

#Change primary key
ALTER TABLE `users`
    DROP PRIMARY KEY,
    ADD PRIMARY KEY pk_users (`id`, `username`);

#Set Default Value of a Field
ALTER TABLE `users`
    MODIFY COLUMN `last_login_time` DATETIME DEFAULT NOW();

#Set Unique Field
ALTER TABLE `users`
    DROP PRIMARY KEY,
    ADD CONSTRAINT pk_users
        PRIMARY KEY `users` (`id`),
    MODIFY COLUMN `username` VARCHAR(30) UNIQUE;

#13 Basic insert
CREATE DATABASE `soft_uni`;
USE `soft_uni`;

#13.1 create tables
CREATE TABLE `towns`
(
    `id`   INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL
);

CREATE TABLE `addresses`
(
    `id`          INT PRIMARY KEY AUTO_INCREMENT,
    `addres_text` VARCHAR(255),
    `town_id`     INT NOT NULL
);

CREATE TABLE `departments`
(
    `id`   INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL
);

CREATE TABLE `employees`
(
    `id`            INT PRIMARY KEY AUTO_INCREMENT,
    `first_name`    VARCHAR(255) NOT NULL,
    `middle_name`   VARCHAR(255) NOT NULL,
    `last_name`     VARCHAR(255) NOT NULL,
    `job_title`     VARCHAR(255) NOT NULL,
    `department_id` INT          NOT NULL,
    `hire_date`     DATE,
    `salary`        DECIMAL,
    `address_id`    INT          NOT NULL
);

#13.2 insert into tables
INSERT INTO `towns`(`name`)
VALUES ('Sofia'),
       ('Plovdiv'),
       ('Varna'),
       ('Burgas');

INSERT INTO `departments`(`name`)
VALUES ('Engineering'),
       ('Sales'),
       ('Marketing'),
       ('Software Development'),
       ('Quality Assurance');

INSERT INTO `employees` (`first_name`, `middle_name`, `last_name`, `job_title`, `department_id`, `hire_date`, `salary`)
VALUES ('Ivan', 'Ivanov', 'Ivanov', '.NET Developer', 4, '2013/02/01', 3500.00),
       ('Petar', 'Petrov', 'Petrov', 'Senior Engineer', 1, '2004/03/02', 4000.00),
       ('Maria', 'Petrova', 'Ivanova', 'Intern', 5, '2016/08/28', 525.25),
       ('Georgi', 'Terziev', 'Ivanov', 'CEO', 2, '2007/12/09', 3000.00),
       ('Peter', 'Pan', 'Pan', 'Intern', 3, '2016/08/28', 599.88);

# 14. Basic Select All Fields
#14.1 Select all field from towns
SELECT *
FROM `towns`;

#14.2 Select all fields from departments
SELECT *
FROM `departments`;

#14.3 Select all fields from employees
SELECT *
FROM `employees`;


#15.	Basic Select All Fields and Order Them
#15.1 Select all field from towns by name in alphabetical order
SELECT *
FROM `towns`
ORDER BY `name`;

#15.2 Select all fields from departments by alphabetical order
SELECT *
FROM `departments`
ORDER BY `name`;

#14.3 Select all fields from employees by salary in descending order
SELECT *
FROM `employees`
ORDER BY `salary` DESC;














