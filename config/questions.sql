DROP TABLE IF EXISTS questions; 
CREATE TABLE questions(
			id_questions INT(11) NOT NULL AUTO_INCREMENT,
			id_user INT(11),
			id_answer INT(11),
			description VARCHAR(3000),
			CONSTRAINT pk_question PRIMARY KEY (id_questions),
			CONSTRAINT fk_answers_questions FOREIGN KEY (id_answer) REFERENCES answers(id_answers) ON DELETE CASCADE ON UPDATE CASCADE,
			CONSTRAINT fk_users_questions FOREIGN KEY (id_user) REFERENCES users(id_users) ON DELETE CASCADE ON UPDATE CASCADE);