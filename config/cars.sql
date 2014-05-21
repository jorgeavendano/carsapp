DROP TABLE IF EXISTS cars;
CREATE TABLE cars (
		  id_cars INT(11) NOT NULL AUTO_INCREMENT,
		  patent INT,
		  CONSTRAINT pk_cars PRIMARY KEY (id_cars),
		  CONSTRAINT fk_cars_vehicle FOREIGN KEY (patent) REFERENCES vehicles (patents) ON DELETE CASCADE ON UPDATE CASCADE);