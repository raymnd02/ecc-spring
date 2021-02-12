CREATE DATABASE spring
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE person (
    person_id SERIAL NOT NULL PRIMARY KEY,
    last_name VARCHAR(255)NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255),
    suffix_name VARCHAR(255),
    title_name VARCHAR(255),
    street_no INTEGER,
    barangay VARCHAR(255),
    municipality_city VARCHAR(255),
    zipcode INTEGER,
    birthday DATE,
    grade_weighted_average DOUBLE PRECISION,
    date_hired DATE,
    current_employed BOOLEAN
);

CREATE TABLE contact (
    contact_id SERIAL NOT NULL PRIMARY KEY,
    land_line NUMERIC,
    mobile_number NUMERIC,
    email VARCHAR(255),
    person_id INTEGER,
    FOREIGN KEY (person_id)
        REFERENCES person (person_id) 
);

CREATE TABLE role (
    role_id SERIAL NOT NULL PRIMARY KEY,
    role VARCHAR(255)
);

CREATE TABLE person_role (
    person_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (person_id)
        REFERENCES person (person_id),
    FOREIGN KEY (role_id)
        REFERENCES role (role_id)
);