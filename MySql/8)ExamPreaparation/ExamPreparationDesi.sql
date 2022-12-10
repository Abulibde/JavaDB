CREATE DATABASE softuni_imdb;

USE softuni_imdb;


# 1. Create database

create table `countries`
(
    `id`        INT PRIMARY KEY AUTO_INCREMENT,
    `name`      VARCHAR(30) NOT NULL UNIQUE,
    `continent` VARCHAR(30) NOT NULL,
    `currency`  VARCHAR(5)  NOT NULL
);

CREATE TABLE `genres`
(
    `id`   INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL UNIQUE
);


CREATE TABLE actors
(
    `id`         INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name`  VARCHAR(50) NOT NULL,
    `birthdate`  DATE        NOT NULL,
    `height`     INT,
    `awards`     INT,
    `country_id` INT         NOT NULL,
    CONSTRAINT `fk_actors_countries`
        FOREIGN KEY (country_id) REFERENCES `countries` (`id`)
);

CREATE TABLE `movies_additional_info`
(
    `id`          INT PRIMARY KEY AUTO_INCREMENT,
    rating        DECIMAL(10, 2) NOT NULL,
    runtime       INT            NOT NULL,
    picture_url   VARCHAR(80)    NOT NULL,
    budget        DECIMAL(10, 2),
    release_date  DATE           NOT NULL,
    has_subtitles BOOLEAN,
    `description` TEXT
);

CREATE TABLE `movies`
(
    `id`            INT PRIMARY KEY AUTO_INCREMENT,
    `title`         VARCHAR(70) NOT NULL UNIQUE,
    `country_id`    INT         NOT NULL,
    `movie_info_id` INT         NOT NULL UNIQUE,
    CONSTRAINT `fk_movies_countries`
        FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`),
    CONSTRAINT `fk_movies_movie_info`
        FOREIGN KEY (`movie_info_id`) REFERENCES `movies_additional_info` (`id`)
);

CREATE TABLE `movies_actors`
(
    `movie_id` INT,
    `actor_id` INT,
    CONSTRAINT `fk_movies_actors_movies`
        FOREIGN KEY (`movie_id`) REFERENCES movies (`id`),
    CONSTRAINT `fk_movies_actors_actors`
        FOREIGN KEY (`actor_id`) REFERENCES actors (`id`)
);

CREATE TABLE `genres_movies`
(
    `genre_id` INT,
    `movie_id` INT,
    CONSTRAINT `pk_genres_movies_genres`
        FOREIGN KEY (`genre_id`) REFERENCES `genres` (`id`),
    CONSTRAINT `pk_genres_movies_movies`
        FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`)
);


# 2. Insert

INSERT INTO `actors`(`first_name`, `last_name`, `birthdate`, `height`, `awards`, `country_id`)
SELECT REVERSE(`first_name`),
       REVERSE(`last_name`),
       DATE_SUB(birthdate, INTERVAL 2 DAY),
       `height` + 10,
       `country_id`,
       3
FROM `actors`
WHERE `id` <= 10;

#INSERT INTO actors (first_name, last_name, birthdate, height, awards, country_id)
#SELECT reverse(first_name),
#      reverse(last_name),
#       date_sub(birthdate, INTERVAL 2 DAY),
#       height + 10,
#       country_id,
#       (SELECT id FROM countries WHERE `name` LIKE 'Armenia')
#FROM actors
#WHERE id <= 10;

# 3. Update
UPDATE `movies_additional_info`
SET `runtime` = `runtime` - 10
WHERE id BETWEEN 15 AND 25;

# 04.	Delete
DELETE c
FROM `countries` AS c
         LEFT JOIN `movies` AS m ON c.`id` = m.`country_id`
WHERE m.`country_id` IS NULL;

# 05.	Countries
SELECT c.id, c.name, c.continent, c.currency
FROM countries AS c
ORDER BY c.currency DESC, c.id;

# 06.	Old movies
SELECT mai.id, m.title, mai.runtime, mai.budget, mai.release_date
FROM movies_additional_info AS mai
         JOIN movies m on mai.id = m.movie_info_id
WHERE year(release_date) BETWEEN 1996 AND 1999
ORDER BY mai.runtime, mai.id
LIMIT 20;

# 07.	Movie casting
SELECT CONCAT(a.first_name, ' ', a.last_name)                         AS full_name,
       CONCAT(REVERSE(a.last_name), LENGTH(a.last_name), '@cast.com') AS mail,
       2022 - YEAR(a.birthdate)                                       AS age,
       a.height
FROM actors AS a
         LEFT JOIN movies_actors ma on a.id = ma.actor_id
WHERE ma.movie_id IS NULL
ORDER BY a.height;

# 08.	International festival
SELECT c.name, COUNT(m.title) AS movies_count
FROM countries AS c
         LEFT JOIN movies m on c.id = m.country_id
GROUP BY c.name
HAVING movies_count >= 7
ORDER BY c.name DESC;

# 09.	Rating system
SELECT m.title,
       CASE
           WHEN mai.rating <= 4 THEN 'poor'
           WHEN mai.rating <= 7 THEN 'good'
           ELSE 'excellent'
           END                               AS rating,
       IF(mai.has_subtitles, 'english', '-') AS subtitles,
       mai.budget
FROM movies_additional_info AS mai
         JOIN movies m on mai.id = m.movie_info_id
ORDER BY mai.budget DESC;

# 10.	History movies
CREATE FUNCTION udf_actor_history_movies_count(full_name VARCHAR(50))
    RETURNS INT
    DETERMINISTIC
BEGIN
    DECLARE history_movies INT;
    SET history_movies := (SELECT count(ma.movie_id)
                           FROM movies_actors AS ma
                                    JOIN actors AS a ON ma.actor_id = a.id
                                    JOIN genres_movies AS gm ON gm.movie_id = ma.movie_id
                                    JOIN genres AS g ON gm.genre_id = g.id
                           WHERE concat_ws(' ', a.first_name, a.last_name) = full_name
                             AND g.`name` = 'History'
                           GROUP BY ma.actor_id);
    RETURN history_movies;
END;




