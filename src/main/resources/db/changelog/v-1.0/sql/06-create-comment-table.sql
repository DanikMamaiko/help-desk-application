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