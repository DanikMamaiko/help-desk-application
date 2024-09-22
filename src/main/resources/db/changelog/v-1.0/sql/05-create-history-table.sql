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