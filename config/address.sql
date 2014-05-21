DROP TABLE IF EXISTS address; 
CREATE TABLE address(
			id_address INT(11) NOT NULL AUTO_INCREMENT,
			id_user INT(11),
			direction varchar(56) NOT NULL,
			city varchar(56),
			province varchar(56),
			postal_code varchar(6),
			CONSTRAINT pk_address PRIMARY KEY (id_address));
