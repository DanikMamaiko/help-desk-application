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
