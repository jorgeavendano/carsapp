DROP TABLE IF EXISTS users;
CREATE TABLE users(
		   id_users INT(11) NOT NULL AUTO_INCREMENT,
		   email VARCHAR(60) UNIQUE,
		   first_name VARCHAR(56),
		   last_name VARCHAR(56),
		   CONSTRAINT pk_users PRIMARY KEY (id_users));