DROP TABLE IF EXISTS qualify; 
CREATE TABLE qualify(
			id_qualify INT(11) NOT NULL AUTO_INCREMENT,
			id_post INT(11),
			id_user INT(11),
			points INT,
			CONSTRAINT pk_qualify PRIMARY KEY (id_qualify),
			CONSTRAINT fk_posts_qualify FOREIGN KEY (id_post) REFERENCES posts(id_posts),
			CONSTRAINT fk_users_qualify FOREIGN KEY (id_user) REFERENCES users(id_users));