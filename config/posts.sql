DROP TABLE IF EXISTS posts; 
CREATE TABLE posts(
		   id_posts INT(11) NOT NULL AUTO_INCREMENT,
	       id_user INT(11),
		   id_question INT(11),
		   patent INT,
		   description VARCHAR(3000),
		   CONSTRAINT pk_posts PRIMARY KEY (id_posts),
		   CONSTRAINT fk_user_post FOREIGN KEY (id_user) REFERENCES users (id_users),  
		   CONSTRAINT fk_vehicle_post FOREIGN KEY (patent) REFERENCES vehicles (patents),  
		   CONSTRAINT fk_question_post FOREIGN KEY (id_question) REFERENCES questions (id_questions) ON DELETE CASCADE ON UPDATE CASCADE);