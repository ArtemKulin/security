CREATE TABLE IF NOT EXISTS spring_crud.users (
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(45) NOT NULL,
lastname VARCHAR(45) NOT NULL,
age INT NOT NULL,
email VARCHAR(45) NOT NULL,
PRIMARY KEY (id)
);