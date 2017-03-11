CREATE TABLE users(
	id VARCHAR(100) NOT NULL,
	username VARCHAR(100) NOT NULL UNIQUE,
	password VARCHAR(100) NOT NULL,
	fullname VARCHAR(164)
);
CREATE TABLE contacts(
	id VARCHAR(100) NOT NULL,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	second_name VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	mobile_number VARCHAR(100) NOT NULL,
	home_phone_number VARCHAR(100),
	user_id VARCHAR(100)
);
CREATE TABLE user_roles(
	user_id VARCHAR(100),
	roles VARCHAR(100)
);

