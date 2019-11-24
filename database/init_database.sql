-- DROP DATABASE timetrack;
--
-- Create database is currently not run by docker image,
-- instead inherited postgresl docker file create something as database
--
-- CREATE DATABASE timetrack
--  WITH OWNER = postgres
--       TEMPLATE template0
--       ENCODING = 'UTF8'
--       TABLESPACE = pg_default
--       LC_COLLATE = 'en_US.UTF-8'
--       LC_CTYPE = 'en_US.UTF-8'
--       CONNECTION LIMIT = -1;

-- CREATE  SCHEMA

-- CREATE SCHEMA IF NOT EXISTS timetrack AUTHORIZATION timetrack;

-- SET SCHEMA 'timetrack';


-- CREATE TABLES

CREATE TABLE IF NOT EXISTS example_table (
  id BIGSERIAL NOT NULL,
  name varchar(256) NOT NULL,
  CONSTRAINT example_table_id PRIMARY KEY (id)
);


CREATE SEQUENCE example_table_id_sequence START 1;

-- INSERT TEST DATA

INSERT INTO example_table
  (id,name) VALUES
  (1,'john doe'),
  (2,'jim doe'),
  (3,'josh doe');
ALTER SEQUENCE example_table_id_sequence RESTART WITH 4;

