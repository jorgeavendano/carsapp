DROP TABLE IF EXISTS vehicles; 
CREATE TABLE vehicles(
		      patents INT NOT NULL,
		      mark VARCHAR(56),
			  model VARCHAR(56),
			  id_user INT(11),
			  CONSTRAINT pk_vehicle PRIMARY KEY (patents),
		      CONSTRAINT fk_vehicle_user FOREIGN KEY (id_user) REFERENCES users(id_users));