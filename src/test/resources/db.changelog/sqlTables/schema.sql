CREATE TABLE IF NOT EXISTS user (
	id BIGINT NOT NULL AUTO_INCREMENT,
	first_name varchar(30),
	last_name varchar(30),
	role_id ENUM('EMPLOYEE', 'MANAGER', 'ENGINEER') NOT NULL,
	email varchar(50),
	password varchar(30),
	PRIMARY KEY (id)
);

CREATE TABLE category (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name varchar(20),
  PRIMARY KEY (id)
);

CREATE TABLE ticket (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name varchar(50),
	description varchar(100),
	created_on DATE,
	desired_resolution_date DATE,
	assignee_id BIGINT,
	owner_id BIGINT,
	state_id ENUM('DRAFT', 'NEW', 'APPROVED', 'DECLINED', 'INPROGRESS', 'DONE', 'CANCELED') NOT NULL,
    category_id BIGINT,
    urgency_id ENUM('CRITICAL', 'HIGH', 'AVERAGE', 'LOW') NOT NULL,
	approver_id BIGINT,
	PRIMARY KEY (id),
	FOREIGN KEY (assignee_id) REFERENCES user(id) ON DELETE SET NULL,
	FOREIGN KEY (owner_id) REFERENCES user(id) ON DELETE SET NULL,
	FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE SET NULL,
	FOREIGN KEY (approver_id) REFERENCES user(id) ON DELETE SET NULL
);

CREATE TABLE attachment (
	id BIGINT NOT NULL AUTO_INCREMENT,
	blobb varchar(50),
	ticket_id BIGINT,
	name varchar(50),
	PRIMARY KEY (id),
	FOREIGN KEY (ticket_id) REFERENCES ticket(id) ON DELETE SET NULL
);

CREATE TABLE history (
	id BIGINT NOT NULL AUTO_INCREMENT,
	ticket_id BIGINT,
	date DATE,
	action varchar(50),
	user_id BIGINT,
	description varchar(100),
	PRIMARY KEY (id),
	FOREIGN KEY (ticket_id) REFERENCES ticket(id) ON DELETE SET NULL,
	FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE SET NULL
);

CREATE TABLE comment (
	id BIGINT NOT NULL AUTO_INCREMENT,
	user_id BIGINT,
	text varchar(100),
	date DATE,
	ticket_id BIGINT,
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE SET NULL,
	FOREIGN KEY (ticket_id) REFERENCES ticket(id) ON DELETE SET NULL
);

CREATE TABLE feedback (
	id BIGINT NOT NULL AUTO_INCREMENT,
	user_id BIGINT,
	rate varchar(5),
	date DATE,
	text varchar(100),
	ticket_id BIGINT,
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE SET NULL,
	FOREIGN KEY (ticket_id) REFERENCES ticket(id) ON DELETE SET NULL
);

