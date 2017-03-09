CREATE TABLE users(
	id BIGINT NOT NULL AUTO_INCREMENT,
	username VARCHAR(100) NOT NULL UNIQUE,
	password VARCHAR(100) NOT NULL,
	fullname VARCHAR(164), 
	PRIMARY KEY( id )
);
CREATE TABLE contacts(
	id BIGINT NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	second_name VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	mobile_number VARCHAR(100) NOT NULL,
	home_phone_number VARCHAR(100),
	PRIMARY KEY (id)
);
CREATE TABLE user_roles(
	user_id BIGINT,
	roles VARCHAR(100),
	FOREIGN KEY (user_id) REFERENCES users(id)
	ON DELETE CASCADE
);
CREATE TABLE phone_book(
	user_id BIGINT NOT NULL,
	contact_id BIGINT NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users(id),
	FOREIGN KEY (contact_id) REFERENCES contacts(id)
);
