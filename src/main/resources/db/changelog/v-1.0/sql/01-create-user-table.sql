CREATE TABLE user (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      first_name varchar(30),
                      last_name varchar(30),
                      role_id ENUM('EMPLOYEE', 'MANAGER', 'ENGINEER') NOT NULL,
                      email varchar(50),
                      password varchar(30),
                      PRIMARY KEY (id)
);