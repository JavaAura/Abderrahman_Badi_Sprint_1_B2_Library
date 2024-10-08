CREATE DATABASE library;

USE library;

CREATE TABLE document
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publication_date DATE NOT NULL,
    page_numbers INTEGER NOT NULL
);

CREATE TABLE book
(
	number INTEGER NOT NULL
) INHERITS (document) ;

CREATE TABLE magazine
(
   isbn INTEGER NOT NULL
) INHERITS (document) ;

CREATE TABLE scientific_journal
(
 	field VARCHAR(255)
) INHERITS (document) ;

CREATE TABLE university_thesis
(
   field VARCHAR(255)
) INHERITS (document) ;

CREATE TABLE "user"
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
	registration_number VARCHAR(255)
);

CREATE TABLE student
(
  	class VARCHAR(255),
	major VARCHAR(255),
	
) INHERITS (user) ;


CREATE TABLE professor
(
   	department VARCHAR(255),
) INHERITS (user) ;

CREATE TYPE reservation_status AS ENUM ('Active', 'Canceled');

CREATE TABLE reservation
(
   	reservation_date DATE,
	reservation_status reservation_status,
	is_borrowed BOOLEAN,
	document_id INT,  
    	user_id INT,
) ;


-- Trigger and functions

CREATE OR REPLACE FUNCTION check_foreign_key()
RETURNS TRIGGER AS $$
DECLARE
  document_exists BOOLEAN;
  user_exists BOOLEAN;
BEGIN
  SELECT EXISTS (
    SELECT 1 FROM document WHERE id = NEW.document_id
	UNION
	SELECT 1 FROM book WHERE id = NEW.document_id
	UNION
    SELECT 1 FROM magazine WHERE id = NEW.document_id
	UNION
    SELECT 1 FROM scientific_journal WHERE id = NEW.document_id
	UNION
    SELECT 1 FROM university_thesis WHERE id = NEW.document_id
  ) INTO document_exists;
  
  SELECT EXISTS (
    SELECT 1 FROM "user" WHERE id = NEW.user_id
    UNION
    SELECT 1 FROM student WHERE id = NEW.user_id
    UNION
    SELECT 1 FROM professor WHERE id = NEW.user_id
  ) INTO user_exists;

  IF NOT document_exists THEN
    RAISE EXCEPTION 'Foreign key violation: Document ID % does not exist in document or its child tables', NEW.document_id;
  END IF;

  IF NOT user_exists THEN
    RAISE EXCEPTION 'Foreign key violation: User ID % does not exist in user or its child tables', NEW.user_id;
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;


-- ---------------------------------- --

CREATE TRIGGER check_foreign_key_before_insert
BEFORE INSERT OR UPDATE ON reservation
FOR EACH ROW EXECUTE FUNCTION check_foreign_key();






