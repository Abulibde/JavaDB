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




