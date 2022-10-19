CREATE SCHEMA IF NOT EXISTS aircraft_tests;
SET SCHEMA aircraft_tests;

CREATE TABLE IF NOT EXISTS aircraft_tests.Aircraft (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    status VARCHAR(20),
    name VARCHAR(255)
);

INSERT INTO Aircraft(status, name) VALUES('active', 'Airbus 380');

SELECT * FROM AIRCRAFT;