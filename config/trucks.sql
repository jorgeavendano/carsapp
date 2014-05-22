DROP TABLE IF EXISTS trucks;
CREATE TABLE trucks (
		  id_trucks INT(11) NOT NULL AUTO_INCREMENT,
		  patent INT,
		  capacity VARCHAR(20),
		  CONSTRAINT pk_trucks PRIMARY KEY (id_trucks),
		  CONSTRAINT fk_trucks_vehicle FOREIGN KEY (patent) REFERENCES vehicles (patents) ON DELETE CASCADE ON UPDATE CASCADE);