CREATE TABLE attachment (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            blobb varchar(50),
                            ticket_id BIGINT,
                            name varchar(50),
                            PRIMARY KEY (id),
                            FOREIGN KEY (ticket_id) REFERENCES ticket(id) ON DELETE SET NULL
);