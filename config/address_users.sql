drop table if exists users_address;
CREATE TABLE users_address(
id_user INT(11),
id_addresss INT(11),
CONSTRAINT fk_address FOREIGN KEY (id_addresss) REFERENCES address (id_address) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT fk_users FOREIGN KEY (id_user) REFERENCES users (id_users) ON DELETE CASCADE ON UPDATE CASCADE);