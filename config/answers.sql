DROP TABLE IF EXISTS answers; 
CREATE TABLE answers(
		     id_answers INT(11) NOT NULL AUTO_INCREMENT,
		     id_user INT(11),
		     description VARCHAR(3000),
		     CONSTRAINT pk_answers PRIMARY KEY (id_answers),
		     CONSTRAINT fk_user_answers FOREIGN KEY (id_user) REFERENCES users(id_users));