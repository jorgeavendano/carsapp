DROP TABLE IF EXISTS others;
CREATE TABLE others (
			id_others INT(11) NOT NULL AUTO_INCREMENT,
			patent INT,
			transmission VARCHAR(12),
			CONSTRAINT pk_others PRIMARY KEY (id_others),
			CONSTRAINT fk_others_vehicle FOREIGN KEY (patent) REFERENCES vehicles (patents) ON DELETE CASCADE ON UPDATE CASCADE);