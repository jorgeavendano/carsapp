-- Integrantes: W - X - Y - Z

DROP TABLE IF EXISTS users;
CREATE TABLE users(
		id_users INT(11) NOT NULL AUTO_INCREMENT,
		email VARCHAR(60) UNIQUE,
		first_name VARCHAR(56),
		last_name VARCHAR(56),
		CONSTRAINT pk_users PRIMARY KEY (id_users));

DROP TABLE IF EXISTS address; 
CREATE TABLE address( 
		id_address INT(11) NOT NULL AUTO_INCREMENT, 
		id_user INT(11), 
		direction varchar(56) NOT NULL, 
		city varchar(56), 
		province varchar(56),
		postal_code varchar(6), 
		CONSTRAINT pk_address PRIMARY KEY (id_address));

DROP TABLE IF EXISTS users_address;
CREATE TABLE users_address(
		id_user INT(11),
		id_addresss INT(11),
		CONSTRAINT fk_address FOREIGN KEY (id_addresss) REFERENCES address (id_address) ON DELETE CASCADE ON UPDATE CASCADE,
		CONSTRAINT fk_users FOREIGN KEY (id_user) REFERENCES users (id_users) ON DELETE CASCADE ON UPDATE CASCADE);

DROP TABLE IF EXISTS vehicles; 
CREATE TABLE vehicles(
		patents INT NOT NULL,
		mark VARCHAR(56),
		model VARCHAR(56),
		id_user INT(11),
		CONSTRAINT pk_vehicle PRIMARY KEY (patents),
		CONSTRAINT fk_vehicle_user FOREIGN KEY (id_user) REFERENCES users(id_users));

DROP TABLE IF EXISTS cars;
CREATE TABLE cars (
		id_cars INT(11) NOT NULL AUTO_INCREMENT,
		patent INT,
		isCoupe BOOL,
		CONSTRAINT pk_cars PRIMARY KEY (id_cars),
		CONSTRAINT fk_cars_vehicle FOREIGN KEY (patent) REFERENCES vehicles (patents) ON DELETE CASCADE ON UPDATE CASCADE);

DROP TABLE IF EXISTS motocicles;
CREATE TABLE motocicles (
		id_motocicles INT(11) NOT NULL AUTO_INCREMENT,
		patent INT,
		cc INT,
		CONSTRAINT pk_motocicles PRIMARY KEY (id_motocicles),
		CONSTRAINT fk_motocicles_vehicle FOREIGN KEY (patent) REFERENCES vehicles (patents) ON DELETE CASCADE ON UPDATE CASCADE);


DROP TABLE IF EXISTS trucks;
CREATE TABLE trucks (
		id_trucks INT(11) NOT NULL AUTO_INCREMENT,
		patent INT,
		capacity VARCHAR(20),
		CONSTRAINT pk_trucks PRIMARY KEY (id_trucks),
		CONSTRAINT fk_trucks_vehicle FOREIGN KEY (patent) REFERENCES vehicles (patents) ON DELETE CASCADE ON UPDATE CASCADE);


DROP TABLE IF EXISTS others;
CREATE TABLE others (
		id_others INT(11) NOT NULL AUTO_INCREMENT,
		patent INT,
		transmission VARCHAR(12),
		CONSTRAINT pk_others PRIMARY KEY (id_others),
		CONSTRAINT fk_others_vehicle FOREIGN KEY (patent) REFERENCES vehicles (patents) ON DELETE CASCADE ON UPDATE CASCADE);

DROP TABLE IF EXISTS answers; 
CREATE TABLE answers(
		id_answers INT(11) NOT NULL AUTO_INCREMENT,
		id_user INT(11),
		description VARCHAR(3000),
		CONSTRAINT pk_answers PRIMARY KEY (id_answers),
		CONSTRAINT fk_user_answers FOREIGN KEY (id_user) REFERENCES users(id_users));

DROP TABLE IF EXISTS questions; 
CREATE TABLE questions(
		id_questions INT(11) NOT NULL AUTO_INCREMENT,
		id_user INT(11),
		id_answer INT(11),
		description VARCHAR(3000),
		CONSTRAINT pk_question PRIMARY KEY (id_questions),
		CONSTRAINT fk_answers_questions FOREIGN KEY (id_answer) REFERENCES answers(id_answers) ON DELETE CASCADE ON UPDATE CASCADE,
		CONSTRAINT fk_users_questions FOREIGN KEY (id_user) REFERENCES users(id_users) ON DELETE CASCADE ON UPDATE CASCADE);


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

DROP TABLE IF EXISTS qualify; 
CREATE TABLE qualify(
		id_qualify INT(11) NOT NULL AUTO_INCREMENT,
		id_post INT(11),
		id_user INT(11),
		points INT,
		CONSTRAINT pk_qualify PRIMARY KEY (id_qualify),
		CONSTRAINT fk_posts_qualify FOREIGN KEY (id_post) REFERENCES posts(id_posts),
		CONSTRAINT fk_users_qualify FOREIGN KEY (id_user) REFERENCES users(id_users));
