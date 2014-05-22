DROP TABLE IF EXISTS motocicles;
CREATE TABLE motocicles (
			id_motocicles INT(11) NOT NULL AUTO_INCREMENT,
			patent INT,
			cc INT,
			CONSTRAINT pk_motocicles PRIMARY KEY (id_motocicles),
		    CONSTRAINT fk_motocicles_vehicle FOREIGN KEY (patent) REFERENCES vehicles (patents) ON DELETE CASCADE ON UPDATE CASCADE);