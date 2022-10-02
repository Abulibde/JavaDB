CREATE DATABASE `Table Relations`;
USE `Table Relations`;


# 01. One-To-One Relationship
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

# 02. One-To-Many Relationship
CREATE TABLE `manufacturers`
(
    `manufacturer_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name`            VARCHAR(50) NOT NULL UNIQUE,
    `established_on`  DATE
);


INSERT INTO `manufacturers`(`name`, `established_on`)
VALUES ('BMW', '1916-03-01'),
       ('Tesla', '2003-01-01'),
       ('Lada', '1966-05-01');

CREATE TABLE `models`
(
    `model_id`        INT PRIMARY KEY AUTO_INCREMENT,
    `name`            VARCHAR(70) NOT NULL,
    `manufacturer_id` INT,
    CONSTRAINT fk_models_manufacturers
        FOREIGN KEY (`manufacturer_id`)
            REFERENCES `manufacturers` (`manufacturer_id`)
);

INSERT INTO `models`(`model_id`, `name`, `manufacturer_id`)
VALUES (101, 'X1', 1),
       (102, 'I6', 1),
       (103, 'Model S', 2),
       (104, 'Model X', 2),
       (105, 'Model 3', 2),
       (106, 'Nova', 3);

# 03. Many-To-Many Relationship
CREATE TABLE `students`
(
    `student_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name`       VARCHAR(70) NOT NULL
);

INSERT INTO `students`(`name`)
VALUES ('Mila'),
       ('Toni'),
       ('Ron');

CREATE TABLE `exams`
(
    `exam_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name`    VARCHAR(70) NOT NULL
);
ALTER TABLE `exams`
    AUTO_INCREMENT = 101;

INSERT INTO `exams`(`name`)
VALUES ('Spring MVC'),
       ('Neo4j'),
       ('Oracle 11g');

CREATE TABLE `students_exams`
(
    `student_id` INT NOT NULL,
    `exam_id`    INT NOT NULL,

    CONSTRAINT pk
        PRIMARY KEY (`student_id`, `exam_id`),

    CONSTRAINT fk_students
        FOREIGN KEY (`student_id`)
            REFERENCES `students` (student_id),

    CONSTRAINT fk_exams
        FOREIGN KEY (`exam_id`)
            REFERENCES exams (exam_id)

);





