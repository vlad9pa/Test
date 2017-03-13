# TaskTest
This repository was created for the test task
# SQL
    CREATE TABLE users(
        id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
        username VARCHAR(100) NOT NULL UNIQUE,
        password VARCHAR(100) NOT NULL,
        fullname VARCHAR(164)
    );
    CREATE TABLE contacts(
        id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
        first_name VARCHAR(100) NOT NULL,
        last_name VARCHAR(100) NOT NULL,
        middle_name VARCHAR(100) NOT NULL,
        email VARCHAR(100) NOT NULL,
        mobile_number VARCHAR(100) NOT NULL,
        home_phone_number VARCHAR(100),
        user_id BIGINT,
        FOREIGN KEY (user_id) REFERENCES users(id)
    );
    CREATE TABLE user_roles(
        user_id BIGINT,
        roles VARCHAR(100),
        FOREIGN KEY (user_id) REFERENCES users(id)
    );
# JSON
To set file storage path, pls edit path in **JsonRepository.class**
```javascript
public static final String PATH_TO_MAIN_DIR = "UR/PATH/TO/STORAGE";
```

# Profiles
* json - use json file storage
* sql - use mysql db