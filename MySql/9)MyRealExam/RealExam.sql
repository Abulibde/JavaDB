CREATE DATABASE `airlines_db`;

USE `airlines_db`;

## 1. Create tables

CREATE TABLE `countries`
(
    `id`          INT PRIMARY KEY AUTO_INCREMENT,
    `name`        VARCHAR(30) NOT NULL UNIQUE,
    `description` TEXT,
    `currency`    VARCHAR(5)  NOT NULL
);

CREATE TABLE `airplanes`
(
    `id`                  INT PRIMARY KEY AUTO_INCREMENT,
    `model`               VARCHAR(50)    NOT NULL UNIQUE,
    `passengers_capacity` INT            NOT NULL,
    `tank_capacity`       DECIMAL(19, 2) NOT NULL,
    `cost`                DECIMAL(19, 2) NOT NULL

);

CREATE TABLE `passengers`
(
    `id`         INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(30) NOT NULL,
    `last_name`  VARCHAR(30) NOT NULL,
    `country_id` INT         NOT NULL,
    CONSTRAINT `fk_passengers_countries`
        FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`)
);

CREATE TABLE `flights`
(
    `id`                  INT PRIMARY KEY AUTO_INCREMENT,
    `flight_code`         VARCHAR(30) NOT NULL UNIQUE,
    `departure_country`   INT         NOT NULL,
    `destination_country` INT         NOT NULL,
    `airplane_id`         INT         NOT NULL,
    `has_delay`           BOOLEAN,
    `departure`           DATETIME,
    CONSTRAINT `fk_flights_countries_departure`
        FOREIGN KEY (`departure_country`) REFERENCES `countries` (`id`),
    CONSTRAINT `fk_flights_countries_destination`
        FOREIGN KEY (`destination_country`) REFERENCES `countries` (`id`),
    CONSTRAINT `fk_flights_airplanes`
        FOREIGN KEY (`airplane_id`) REFERENCES `airplanes` (`id`)
);

CREATE TABLE `flights_passengers`
(
    `flight_id`    INT,
    `passenger_id` INT,
    CONSTRAINT `fk_fl_ps_flights`
        FOREIGN KEY (`flight_id`) REFERENCES `flights` (`id`),
    CONSTRAINT `fk_fl_ps_passengers`
        FOREIGN KEY (`passenger_id`) REFERENCES `passengers` (`id`)
);

## 2. Insert
INSERT INTO `airplanes`(`model`, `passengers_capacity`, `tank_capacity`, `cost`)
SELECT CONCAT(REVERSE(p.`first_name`), '797'),
       LENGTH(p.`last_name`) * 17,
       p.`id` * 790,
       LENGTH(p.`first_name`) * 50.6
FROM passengers AS p
WHERE p.id <= 5;

## 3. Update
UPDATE `flights`
SET `airplane_id` = `airplane_id` + 1
WHERE `departure_country` = (SELECT `id` FROM countries WHERE `name` = 'Armenia');

## 4. Delete
DELETE f
FROM `flights` AS f
         LEFT JOIN flights_passengers AS fp ON f.id = fp.flight_id
WHERE fp.`passenger_id` IS NULL;

## 5. Airplanes
SELECT a.`id`, a.`model`, a.`passengers_capacity`, a.`tank_capacity`, a.`cost`
FROM `airplanes` AS a
ORDER BY a.`cost` DESC, a.`id` DESC;

## 6. 06.	Flights from 2022
SELECT `flight_code`, `departure_country`, `airplane_id`, `departure`
FROM `flights`
WHERE YEAR(departure) = 2022
ORDER BY `airplane_id`, `flight_code`
LIMIT 20;

## 7. Private flights
SELECT CONCAT(UPPER(LEFT(p.last_name, 2)), p.country_id) AS `flight_code`,
       CONCAT(p.first_name, ' ', p.last_name)            AS `full_name`,
       p.country_id
FROM `passengers` AS p
         LEFT JOIN flights_passengers AS fp ON p.id = fp.passenger_id
WHERE fp.flight_id IS NULL
ORDER BY p.country_id;

## 8. Leading destinations
SELECT c.name, c.currency, COUNT(fp.passenger_id) AS booked_tickets
FROM flights AS f
         JOIN countries AS c ON c.id = f.destination_country
         LEFT JOIN flights_passengers AS fp ON f.id = fp.flight_id
GROUP BY f.destination_country
HAVING booked_tickets >= 20
ORDER BY booked_tickets DESC;

## 9. Parts 0f the day
SELECT flight_code,
       departure,

       CASE
           WHEN 5 <= HOUR(departure) AND HOUR(departure) < 12 THEN 'Morning'
           WHEN 12 <= HOUR(departure) AND HOUR(departure) < 17 THEN 'Afternoon'
           WHEN 17 <= HOUR(departure) AND HOUR(departure) < 21 THEN 'Evening'
           ELSE 'Night'
           END AS day_part
FROM flights
ORDER BY flight_code DESC;

## 10.	Number of flights
CREATE FUNCTION udf_count_flights_from_country(country VARCHAR(50))
    RETURNS INT
    DETERMINISTIC
BEGIN
    DECLARE flights_count INT;
    SET flights_count := (SELECT count(f.flight_code) AS flights_count
                          FROM flights AS f
                                   JOIN countries AS c ON c.id = f.departure_country
                          WHERE c.name = country
                          GROUP BY f.departure_country);
    RETURN flights_count;
END;

## 11. Delay flight
CREATE PROCEDURE udp_delay_flight(code VARCHAR(50))
BEGIN
    UPDATE flights AS f
    SET f.has_delay = 1,
        departure = adddate(departure, INTERVAL 30 MINUTE)
    WHERE f.flight_code = code;
END;

